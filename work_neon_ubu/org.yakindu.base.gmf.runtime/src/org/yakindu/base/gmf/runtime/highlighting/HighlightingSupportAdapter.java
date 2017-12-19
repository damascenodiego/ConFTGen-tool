/**
 * Copyright (c) 2011 committers of YAKINDU and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * 	committers of YAKINDU - initial API and implementation
 * 
 */
package org.yakindu.base.gmf.runtime.highlighting;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IPrimaryEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderedNodeFigure;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.yakindu.base.gmf.runtime.util.EditPartUtils;

/**
 * 
 * @author Alexander Nyssen
 * @author Andreas Muelder
 * @author Axel Terfloth
 * 
 */
public class HighlightingSupportAdapter implements IHighlightingSupport {

	private class ColorMemento {
		private final Color foregroundColor;
		private final Color backgroundColor;
		private final IFigure figure;

		protected ColorMemento(IFigure figure) {
			this.figure = figure;
			this.foregroundColor = figure.getForegroundColor();
			this.backgroundColor = figure.getBackgroundColor();
		}

		protected void restore() {
			figure.setForegroundColor(foregroundColor);
			figure.setBackgroundColor(backgroundColor);
		}
	}

	private final Map<IFigure, ColorMemento> figureStates = new HashMap<IFigure, ColorMemento>();
	private boolean locked = false;
	private final IDiagramWorkbenchPart diagramWorkbenchPart;
	private Map<EObject, IGraphicalEditPart> object2editPart = new HashMap<EObject, IGraphicalEditPart>();

	public HighlightingSupportAdapter(IDiagramWorkbenchPart diagramWorkbenchPart) {
		this.diagramWorkbenchPart = diagramWorkbenchPart;
	}

	private IGraphicalEditPart getEditPartForSemanticElement(EObject semanticElement) {
		IGraphicalEditPart result = object2editPart.get(semanticElement);
		if (result != null)
			return result;
		result = EditPartUtils.findEditPartForSemanticElement(
				diagramWorkbenchPart.getDiagramGraphicalViewer().getRootEditPart(), semanticElement);
		object2editPart.put(semanticElement, result);
		return result;
	}

	private IFigure getTargetFigure(IGraphicalEditPart editPart) {
		IFigure figure = editPart.getFigure();
		if (figure instanceof BorderedNodeFigure) {
			figure = (IFigure) figure.getChildren().get(0);
		}
		if (figure instanceof DefaultSizeNodeFigure) {
			figure = (IFigure) figure.getChildren().get(0);
		}
		return figure;
	}

	public synchronized void lockEditor() {
		if (locked) {
			throw new IllegalStateException("Editor already locked!");
		}
		List<Action> singletonList = new ArrayList<>();
		singletonList.add(new Action() {
			@Override
			public void execute(IHighlightingSupport hs) {
				lockEditorInternal();
			}
		});
		executeSync(singletonList);
	}

	private void lockEditorInternal() {
		setSanityCheckEnablementState(false);
		for (Object editPart : diagramWorkbenchPart.getDiagramGraphicalViewer().getEditPartRegistry().values()) {
			if (editPart instanceof IPrimaryEditPart) {
				IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) editPart;
				IFigure figure = getTargetFigure(graphicalEditPart);
				figureStates.put(figure, new ColorMemento(figure));
			}
		}
		locked = true;
	}

	private void setSanityCheckEnablementState(boolean state) {
		try {
			Method enableMethod = DiagramDocumentEditor.class.getDeclaredMethod("enableSanityChecking",
					new Class[] { boolean.class });
			enableMethod.setAccessible(true);
			enableMethod.invoke(diagramWorkbenchPart, new Object[] { state });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void releaseEditor() {
		if (!locked) {
			throw new IllegalStateException("Editor not locked!");
		}
		List<Action> singletonList = new ArrayList<>();
		singletonList.add(new Action() {
			@Override
			public void execute(IHighlightingSupport hs) {
				releaseInternal();
			}
		});
		executeSync(singletonList);
	}

	protected void releaseInternal() {
		// restore all elements still being highlighted
		for (ColorMemento figureState : figureStates.values()) {
			figureState.restore();
		}
		figureStates.clear();
		diagramWorkbenchPart.getDiagramEditPart().enableEditMode();
		setSanityCheckEnablementState(true);
		object2editPart.clear();
		locked = false;
	}

	public void highlight(List<? extends EObject> semanticElements, HighlightingParameters parameters) {
		synchronized (semanticElements) {
			for (EObject semanticElement : semanticElements) {
				IGraphicalEditPart editPartForSemanticElement = getEditPartForSemanticElement(semanticElement);
				if (editPartForSemanticElement != null) {
					IFigure figure = getTargetFigure(editPartForSemanticElement);
					if (parameters != null) {
						figure.setForegroundColor(parameters.foregroundFadingColor);
						figure.setBackgroundColor(parameters.backgroundFadingColor);
						figure.invalidate();
					} else {
						ColorMemento memento = figureStates.get(figure);
						if (memento != null)
							memento.restore();
					}
				}
			}
		}
	}

	public boolean isLocked() {
		return locked;
	}

	public void executeAsync(final List<Action> actions) {
		if (actions != null) {

			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					for (Action a : actions) {
						a.execute(HighlightingSupportAdapter.this);
					}
				}
			});
		}
	}
	protected void executeSync(final List<Action> actions) {
		if (actions != null) {

			Display.getDefault().syncExec(new Runnable() {
				public void run() {
					for (Action a : actions) {
						a.execute(HighlightingSupportAdapter.this);
					}
				}
			});
		}
	}
}