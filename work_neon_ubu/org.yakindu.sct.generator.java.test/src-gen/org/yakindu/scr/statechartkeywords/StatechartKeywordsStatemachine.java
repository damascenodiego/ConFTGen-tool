package org.yakindu.scr.statechartkeywords;
import java.util.LinkedList;
import java.util.List;
import org.yakindu.scr.ITimer;

public class StatechartKeywordsStatemachine implements IStatechartKeywordsStatemachine {

	protected class SCIIfImpl implements SCIIf {
	
		private List<SCIIfListener> listeners = new LinkedList<SCIIfListener>();
		
		public List<SCIIfListener> getListeners() {
			return listeners;
		}
		private SCIIfOperationCallback operationCallback;
		
		public void setSCIIfOperationCallback(
				SCIIfOperationCallback operationCallback) {
			this.operationCallback = operationCallback;
		}
		private boolean operationCallbackEvent;
		
		public boolean isRaisedOperationCallback() {
			return operationCallbackEvent;
		}
		
		protected void raiseOperationCallback() {
			operationCallbackEvent = true;
			for (SCIIfListener listener : listeners) {
				listener.onOperationCallbackRaised();
			}
		}
		
		private boolean listenersEvent;
		
		public boolean isRaisedListeners() {
			return listenersEvent;
		}
		
		protected void raiseListeners() {
			listenersEvent = true;
			for (SCIIfListener listener : listeners) {
				listener.onListenersRaised();
			}
		}
		
		private long timer;
		
		public long getTimer() {
			return timer;
		}
		
		public void setTimer(long value) {
			this.timer = value;
		}
		
		private long isActive;
		
		public long getIsActive() {
			return isActive;
		}
		
		public void setIsActive(long value) {
			this.isActive = value;
		}
		
		private long init;
		
		public long getInit() {
			return init;
		}
		
		public void setInit(long value) {
			this.init = value;
		}
		
		private long enter;
		
		public long getEnter() {
			return enter;
		}
		
		public void setEnter(long value) {
			this.enter = value;
		}
		
		private long runCycle;
		
		public long getRunCycle() {
			return runCycle;
		}
		
		public void setRunCycle(long value) {
			this.runCycle = value;
		}
		
		protected void clearEvents() {
		}
		protected void clearOutEvents() {
		
		operationCallbackEvent = false;
		listenersEvent = false;
		}
		
	}
	
	protected SCIIfImpl sCIIf;
	
	private boolean initialized = false;
	
	public enum State {
		main_region_Timer,
		$NullState$
	};
	
	private final State[] stateVector = new State[1];
	
	private int nextStateIndex;
	
	private ITimer timer;
	
	private final boolean[] timeEvents = new boolean[1];
	private boolean operationCallbackEvent;
	
	private boolean listeners;
	
	private long timerVariable;
	
	protected void setTimerVariable(long value) {
		timerVariable = value;
	}
	
	protected long getTimerVariable() {
		return timerVariable;
	}
	
	private long isActive;
	
	protected void setIsActive(long value) {
		isActive = value;
	}
	
	protected long getIsActive() {
		return isActive;
	}
	
	private long init;
	
	protected void setInit(long value) {
		init = value;
	}
	
	protected long getInit() {
		return init;
	}
	
	private long enter;
	
	protected void setEnter(long value) {
		enter = value;
	}
	
	protected long getEnter() {
		return enter;
	}
	
	private long runCycle;
	
	protected void setRunCycle(long value) {
		runCycle = value;
	}
	
	protected long getRunCycle() {
		return runCycle;
	}
	
	private long sCIIfVariable;
	
	protected void setSCIIfVariable(long value) {
		sCIIfVariable = value;
	}
	
	protected long getSCIIfVariable() {
		return sCIIfVariable;
	}
	
	private InternalOperationCallback operationCallback;
	public StatechartKeywordsStatemachine() {
		sCIIf = new SCIIfImpl();
	}
	
	public void init() {
		this.initialized = true;
		if (timer == null) {
			throw new IllegalStateException("timer not set.");
		}
		for (int i = 0; i < 1; i++) {
			stateVector[i] = State.$NullState$;
		}
		clearEvents();
		clearOutEvents();
		sCIIf.setTimer(0);
		
		sCIIf.setIsActive(0);
		
		sCIIf.setInit(0);
		
		sCIIf.setEnter(0);
		
		sCIIf.setRunCycle(0);
		
		setTimerVariable(0);
		
		setIsActive(0);
		
		setInit(0);
		
		setEnter(0);
		
		setRunCycle(0);
		
		setSCIIfVariable(0);
	}
	
	public void enter() {
		if (!initialized) {
			throw new IllegalStateException(
					"The state machine needs to be initialized first by calling the init() function.");
		}
		if (timer == null) {
			throw new IllegalStateException("timer not set.");
		}
		entryAction();
		enterSequence_main_region_default();
	}
	
	public void exit() {
		exitSequence_main_region();
		exitAction();
	}
	
	/**
	 * @see IStatemachine#isActive()
	 */
	public boolean isActive() {
		return stateVector[0] != State.$NullState$;
	}
	
	/** 
	* Always returns 'false' since this state machine can never become final.
	*
	* @see IStatemachine#isFinal()
	*/
	public boolean isFinal() {
		return false;
	}
	/**
	* This method resets the incoming events (time events included).
	*/
	protected void clearEvents() {
		sCIIf.clearEvents();
		operationCallbackEvent = false;
		listeners = false;
		for (int i=0; i<timeEvents.length; i++) {
			timeEvents[i] = false;
		}
	}
	
	/**
	* This method resets the outgoing events.
	*/
	protected void clearOutEvents() {
		sCIIf.clearOutEvents();
	}
	
	/**
	* Returns true if the given state is currently active otherwise false.
	*/
	public boolean isStateActive(State state) {
	
		switch (state) {
		case main_region_Timer:
			return stateVector[0] == State.main_region_Timer;
		default:
			return false;
		}
	}
	
	/**
	* Set the {@link ITimer} for the state machine. It must be set
	* externally on a timed state machine before a run cycle can be correct
	* executed.
	* 
	* @param timer
	*/
	public void setTimer(ITimer timer) {
		this.timer = timer;
	}
	
	/**
	* Returns the currently used timer.
	* 
	* @return {@link ITimer}
	*/
	public ITimer getTimer() {
		return timer;
	}
	
	public void timeElapsed(int eventID) {
		timeEvents[eventID] = true;
	}
	
	public SCIIf getSCIIf() {
		return sCIIf;
	}
	
	private void raiseOperationCallback() {
		operationCallbackEvent = true;
	}
	
	private void raiseListeners() {
		listeners = true;
	}
	
	public void setInternalOperationCallback(
			InternalOperationCallback operationCallback) {
		this.operationCallback = operationCallback;
	}
	
	private boolean check__lr0() {
		return timeEvents[0];
	}
	
	private void effect__lr0() {
		setTimerVariable(getTimerVariable() + 1);
	}
	
	/* Entry action for statechart 'StatechartKeywords'. */
	private void entryAction() {
		timer.setTimer(this, 0, 1*1000, true);
	}
	
	/* Exit action for state 'StatechartKeywords'. */
	private void exitAction() {
		timer.unsetTimer(this, 0);
	}
	
	/* 'default' enter sequence for state Timer */
	private void enterSequence_main_region_Timer_default() {
		nextStateIndex = 0;
		stateVector[0] = State.main_region_Timer;
	}
	
	/* 'default' enter sequence for region main region */
	private void enterSequence_main_region_default() {
		react_main_region__entry_Default();
	}
	
	/* Default exit sequence for state Timer */
	private void exitSequence_main_region_Timer() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for region main region */
	private void exitSequence_main_region() {
		switch (stateVector[0]) {
		case main_region_Timer:
			exitSequence_main_region_Timer();
			break;
		default:
			break;
		}
	}
	
	/* The reactions of state Timer. */
	private void react_main_region_Timer() {
		if (check__lr0()) {
			effect__lr0();
		}
	}
	
	/* Default react sequence for initial entry  */
	private void react_main_region__entry_Default() {
		enterSequence_main_region_Timer_default();
	}
	
	public void runCycle() {
		if (!initialized)
			throw new IllegalStateException(
					"The state machine needs to be initialized first by calling the init() function.");
		clearOutEvents();
		for (nextStateIndex = 0; nextStateIndex < stateVector.length; nextStateIndex++) {
			switch (stateVector[nextStateIndex]) {
			case main_region_Timer:
				react_main_region_Timer();
				break;
			default:
				// $NullState$
			}
		}
		clearEvents();
	}
}
