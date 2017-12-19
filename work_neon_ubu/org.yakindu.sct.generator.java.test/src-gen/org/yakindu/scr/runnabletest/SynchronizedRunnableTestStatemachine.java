package org.yakindu.scr.runnabletest;
import java.util.List;

import org.yakindu.scr.ITimer;
import org.yakindu.scr.ITimerCallback;
import org.yakindu.scr.runnabletest.RunnableTestStatemachine.State;

/**
 * Runnable wrapper of RunnableTestStatemachine. This wrapper provides a thread-safe
 * instance of the state machine.
 * 
 * Please report bugs and issues...
 */

public class SynchronizedRunnableTestStatemachine implements IRunnableTestStatemachine {
	
	/**
	 * The core state machine is simply wrapped and the event processing will be
	 * delegated to that state machine instance. This instance will be created
	 * implicitly.
	 */
	protected RunnableTestStatemachine statemachine = new RunnableTestStatemachine();
	
	/**
	 * Interface object for SCInterface
	 */		
	protected class SynchronizedSCInterface implements SCInterface {
		
		public List<SCInterfaceListener> getListeners() {
			synchronized(statemachine) {
				return statemachine.getSCInterface().getListeners();
			}
		}
		
		public void setSCInterfaceOperationCallback(SCInterfaceOperationCallback operationCallback) {
			synchronized(statemachine) {
				statemachine.getSCInterface().setSCInterfaceOperationCallback(operationCallback);
			}
		}
		
		public boolean isRaisedEv_out() {
			synchronized(statemachine) {
				return statemachine.getSCInterface().isRaisedEv_out();
			}
		}
		
		public long getEv_outValue() {
			synchronized(statemachine) {
				return statemachine.getSCInterface().getEv_outValue();
			}
		}
		public void raiseEv_in(final long value) {
			
			synchronized (statemachine) {
				statemachine.getSCInterface().raiseEv_in(value);
				statemachine.runCycle();
			}
		}
		
		public long getMyVar() {
			synchronized(statemachine) {
				return statemachine.getSCInterface().getMyVar();
			}
		}
		
		public void setMyVar(final long value) {
			synchronized(statemachine) {
				statemachine.getSCInterface().setMyVar(value);
			}
		}
		
		public long getAfterCalls() {
			synchronized(statemachine) {
				return statemachine.getSCInterface().getAfterCalls();
			}
		}
		
		public void setAfterCalls(final long value) {
			synchronized(statemachine) {
				statemachine.getSCInterface().setAfterCalls(value);
			}
		}
		
		public long getCycles() {
			synchronized(statemachine) {
				return statemachine.getSCInterface().getCycles();
			}
		}
		
		public void setCycles(final long value) {
			synchronized(statemachine) {
				statemachine.getSCInterface().setCycles(value);
			}
		}
		
		public long getS2_entered() {
			synchronized(statemachine) {
				return statemachine.getSCInterface().getS2_entered();
			}
		}
		
		public void setS2_entered(final long value) {
			synchronized(statemachine) {
				statemachine.getSCInterface().setS2_entered(value);
			}
		}
		
	};
	
	protected SCInterface sCInterface;
	
	public SynchronizedRunnableTestStatemachine() {
		sCInterface = new SynchronizedSCInterface();
	}
	
	public synchronized SCInterface getSCInterface() {
		return sCInterface;
	}
	/*================ TIME EVENT HANDLING ================
	
	/** An external timer instance is required. */
	protected ITimer externalTimer;
	
	/** Internally we use a timer proxy that queues time events together with other input events. */
	protected ITimer timerProxy = new ITimer() {
		/** Simply delegate to external timer with a modified callback. */
		@Override
		public void setTimer(ITimerCallback callback, int eventID, long time,
				boolean isPeriodic) {
			externalTimer.setTimer(SynchronizedRunnableTestStatemachine.this, eventID, time, isPeriodic);
		}
		
		@Override
		public void unsetTimer(ITimerCallback callback, int eventID) {
			externalTimer.unsetTimer(SynchronizedRunnableTestStatemachine.this, eventID);
		}
	};
	
	/**
	 * Set the {@link ITimer} for the state machine. It must be set externally
	 * on a timed state machine before a run cycle can be correct executed.
	 * 
	 * @param timer
	 */
	public void setTimer(ITimer timer) {
		synchronized(statemachine) {
			this.externalTimer = timer;
			/* the wrapped state machine uses timer proxy as timer */
			statemachine.setTimer(timerProxy);
		}
	}
	
	/**
	* Returns the currently used timer.
	* 
	* @return {@link ITimer}
	*/
	public ITimer getTimer() {
		return externalTimer;
	}
	
	public void timeElapsed(int eventID) {
		synchronized (statemachine) {
			statemachine.timeElapsed(eventID);
		}
	}
	
	/**
	 * init() will be delegated thread-safely to the wrapped state machine.
	 */
	public void init() {
		synchronized(statemachine) {
			statemachine.init();
		}
	}
	
	/**
	 * enter() will be delegated thread-safely to the wrapped state machine.
	 */
	public void enter() {
		synchronized(statemachine) {
			statemachine.enter();
		}
	}
	
	/**
	 * exit() will be delegated thread-safely to the wrapped state machine.
	 */
	public void exit() {
		synchronized(statemachine) {
			statemachine.exit();
		}
	}
	
	/**
	 * isActive() will be delegated thread-safely to the wrapped state machine.
	 */
	public boolean isActive() {
		synchronized(statemachine) {
			return statemachine.isActive();
		}
	}
	
	/**
	 * isFinal() will be delegated thread-safely to the wrapped state machine.
	 */
	public boolean isFinal() {
		synchronized(statemachine) {
			return statemachine.isFinal();
		}
	}
	
	/**
	 * isStateActive() will be delegated thread-safely to the wrapped state machine.
	 */
	public boolean isStateActive(State state) {
		synchronized(statemachine) {
			return statemachine.isStateActive(state);
		}
	}
	
	/**
	 * runCycle() will be delegated thread-safely to the wrapped state machine.
	 */ 
	@Override
	public void runCycle() {
		synchronized (statemachine) {
			statemachine.runCycle();
		}
	}
}
