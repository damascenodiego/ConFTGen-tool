/**
 * Copyright (c) 2016 committers of YAKINDU and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * 	committers of YAKINDU - initial API and implementation
 * 
 */
package org.yakindu.sct.examples.wizard.service.git;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jgit.api.FetchCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.RepositoryNotFoundException;
import org.eclipse.jgit.transport.FetchResult;
import org.eclipse.jgit.transport.TrackingRefUpdate;
import org.eclipse.ui.dialogs.IOverwriteQuery;
import org.eclipse.ui.wizards.datatransfer.FileSystemStructureProvider;
import org.eclipse.ui.wizards.datatransfer.ImportOperation;
import org.yakindu.sct.examples.wizard.ExampleActivator;
import org.yakindu.sct.examples.wizard.preferences.ExamplesPreferenceConstants;
import org.yakindu.sct.examples.wizard.service.ExampleData;
import org.yakindu.sct.examples.wizard.service.IExampleService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * 
 * @author t00manysecretss
 * @author andreas muelder - let GitRepositoryExampleService implement
 *         {@link IExampleService} interface
 * 
 */
@Singleton
public class GitRepositoryExampleService implements IExampleService {

	private static final String GIT_METADATA_FOLDER = ".git";

	private static final String METADATA_JSON = "metadata.json";

	@Inject
	private IExampleDataReader reader;

	protected java.nio.file.Path getStorageLocation() {
		return java.nio.file.Paths.get(ExampleActivator.getDefault().getPreferenceStore()
				.getString(ExamplesPreferenceConstants.STORAGE_LOCATION));
	}

	protected java.nio.file.Path getGitMetadataLocation() {
		return java.nio.file.Paths.get(ExampleActivator.getDefault().getPreferenceStore()
				.getString(ExamplesPreferenceConstants.STORAGE_LOCATION), GIT_METADATA_FOLDER);
	}

	@Override
	public boolean exists() {
		return Files.exists(getGitMetadataLocation());
	}

	@Override
	public IStatus fetchAllExamples(IProgressMonitor monitor) {
		if (!exists()) {
			java.nio.file.Path storageLocation = getStorageLocation();
			try {
				Files.createDirectories(storageLocation);
			} catch (IOException e1) {
				return new Status(IStatus.ERROR, ExampleActivator.PLUGIN_ID,
						"Unable to create folder " + storageLocation.getFileName());
			}
			return cloneRepository(monitor);
		} else {
			return updateRepository(monitor);
		}
	}

	protected IStatus updateRepository(IProgressMonitor monitor) {
		String repoURL = getPreference(ExamplesPreferenceConstants.REMOTE_LOCATION);
		try {
			java.nio.file.Path storageLocation = getStorageLocation();
			PullResult result = Git.open(storageLocation.toFile()).pull()
					.setProgressMonitor(new EclipseGitProgressTransformer(monitor)).call();
			if (!result.isSuccessful()) {
				return new Status(IStatus.ERROR, ExampleActivator.PLUGIN_ID,
						"Unable to update repository " + repoURL + "!");
			}
		} catch (GitAPIException | IOException e) {
			return new Status(IStatus.ERROR, ExampleActivator.PLUGIN_ID,
					"Unable to update repository " + repoURL + "!");
		}
		return Status.OK_STATUS;
	}
	
	protected String getPreference(String constant){
		return ExampleActivator.getDefault().getPreferenceStore().getString(constant);
	}
	
	protected IStatus cloneRepository(IProgressMonitor monitor) {
		String repoURL = getPreference(ExamplesPreferenceConstants.REMOTE_LOCATION);
		String remoteBranch = getPreference(ExamplesPreferenceConstants.REMOTE_BRANCH);
		Git call = null;
		java.nio.file.Path storageLocation = getStorageLocation();
		try {
			call = Git.cloneRepository().setURI(repoURL).setDirectory(storageLocation.toFile())
					.setProgressMonitor(new EclipseGitProgressTransformer(monitor)).setBranch(remoteBranch).call();
		} catch (GitAPIException e) {
			try {
				deleteFolder(storageLocation);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			return new Status(IStatus.ERROR, ExampleActivator.PLUGIN_ID,
					"Unable to clone repository " + repoURL + "!");
		} finally {
			if (call != null)
				call.close();
			if (monitor.isCanceled()) {
				try {
					deleteFolder(storageLocation);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return Status.OK_STATUS;
	}

	@Override
	public List<ExampleData> getExamples(IProgressMonitor monitor) {
		java.nio.file.Path storageLocation = getStorageLocation();
		List<java.nio.file.Path> projects = new ArrayList<>();
		findMetaData(projects, storageLocation);
		List<ExampleData> result = reader.parse(projects);
		Collections.sort(result);
		return result;
	}

	protected void findMetaData(List<java.nio.file.Path> result, java.nio.file.Path root) {
		try (DirectoryStream<java.nio.file.Path> stream = Files.newDirectoryStream(root)) {
			for (java.nio.file.Path entry : stream) {
				if (Files.isDirectory(entry)) {
					findMetaData(result, entry);
				} else if (entry.getFileName().toString().equals(METADATA_JSON)) {
					result.add(entry);
				}
			}
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void importExample(ExampleData edata, IProgressMonitor monitor) {
		try {
			IProjectDescription original = ResourcesPlugin.getWorkspace()
					.loadProjectDescription(new Path(edata.getProjectDir().getAbsolutePath()).append("/.project"));
			IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(edata.getProjectDir().getName());

			IProjectDescription clone = ResourcesPlugin.getWorkspace()
					.newProjectDescription(original.getName());
			clone.setBuildSpec(original.getBuildSpec());
			clone.setComment(original.getComment());
			clone.setDynamicReferences(original
					.getDynamicReferences());
			clone.setNatureIds(original.getNatureIds());
			clone.setReferencedProjects(original
					.getReferencedProjects());
			if(project.exists()){
				return;
			}
			project.create(clone, monitor);
			project.open(monitor);
			
			@SuppressWarnings("unchecked")
			List<IFile> filesToImport = FileSystemStructureProvider.INSTANCE.getChildren(edata.getProjectDir());
			ImportOperation io = new ImportOperation(project.getFullPath(), edata.getProjectDir(),
					FileSystemStructureProvider.INSTANCE, new IOverwriteQuery() {

						@Override
						public String queryOverwrite(String pathString) {
							return IOverwriteQuery.ALL;
						}

					}, filesToImport);
			io.setOverwriteResources(true);
			io.setCreateContainerStructure(false);
			io.run(monitor);
			project.refreshLocal(IProject.DEPTH_INFINITE, monitor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteFolder(java.nio.file.Path path) throws IOException {
		if (!Files.exists(path))
			return;
		Files.walkFileTree(path, new SimpleFileVisitor<java.nio.file.Path>() {
			@Override
			public FileVisitResult visitFile(java.nio.file.Path file, BasicFileAttributes attrs) throws IOException {
				Files.delete(file);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult postVisitDirectory(java.nio.file.Path dir, IOException exc) throws IOException {
				Files.delete(dir);
				return FileVisitResult.CONTINUE;
			}
		});
	}

	@Override
	public boolean isUpToDate(IProgressMonitor monitor) {
		java.nio.file.Path storageLocation = getStorageLocation();
		try {
			FetchCommand fetch = Git.open(storageLocation.toFile()).fetch();
			FetchResult result = fetch.setProgressMonitor(new EclipseGitProgressTransformer(monitor)).setDryRun(true)
					.call();
			Collection<TrackingRefUpdate> trackingRefUpdates = result.getTrackingRefUpdates();
			return trackingRefUpdates.size() == 0;
		} catch (RepositoryNotFoundException ex) {
			// This is the case when the examples are imported manually
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return true;
		}
	}

}