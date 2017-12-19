package org.yakindu.scr.runnabletest;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Runnable wrapper of RunnableTestStatemachine. This wrapper provides a
 * thread-safe, runnable instance of the state machine. The wrapper implements
 * the {@link Runnable} interface and can be started in a thread by the client
 * code. The run method then starts the main event processing loop for this
 * state machine.
 *
 * Please report bugs and issues...
 */

public class RunnableTestStatemachineRunnable extends SynchronizedRunnableTestStatemachine implements Runnable {
	
	/**
	 * The events are queued using a blocking queue without capacity
	 * restriction. This queue holds Runnable instances that process the events.
	 */
	protected BlockingQueue<Runnable> eventQueue = new LinkedBlockingQueue<Runnable>();
	
	/**
	 * Interface object for SCInterface
	 */		
	protected SCInterface sCInterface = new SynchronizedSCInterface() {
		public void raiseEv_in(final long value) {
			
			eventQueue.add( new Runnable() {
				
				@Override
				public void run() {
					synchronized (statemachine) {
						statemachine.getSCInterface().raiseEv_in(value);
						statemachine.runCycle();
					}
				}
			});
		}
		
	};
	
	public void timeElapsed(final int eventID) {
		eventQueue.add(new Runnable() {
	
			@Override
			public void run() {
				synchronized (statemachine) {
					statemachine.timeElapsed(eventID);
					statemachine.runCycle();
				}
			}
		});
	}
	
	/**
	 * This method will start the main execution loop for the state machine.
	 * First it will init and enter the state machine implicitly and then will
	 * start processing events from the event queue until the thread is
	 * interrupted.
	 */
	@Override
	public void run() {
	
		boolean terminate = false;
		
		while(!(terminate || Thread.currentThread().isInterrupted())) {
	
			try {
				
				Runnable eventProcessor = eventQueue.take();
				eventProcessor.run();
				
			} catch (InterruptedException e) {
				terminate = true;
			}
		}
	}
}
