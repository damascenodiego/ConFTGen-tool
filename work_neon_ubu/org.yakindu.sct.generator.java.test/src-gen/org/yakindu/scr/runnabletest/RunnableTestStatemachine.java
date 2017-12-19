package org.yakindu.scr.runnabletest;
import java.util.LinkedList;
import java.util.List;
import org.yakindu.scr.ITimer;

public class RunnableTestStatemachine implements IRunnableTestStatemachine {

	protected class SCInterfaceImpl implements SCInterface {
	
		private List<SCInterfaceListener> listeners = new LinkedList<SCInterfaceListener>();
		
		public List<SCInterfaceListener> getListeners() {
			return listeners;
		}
		private SCInterfaceOperationCallback operationCallback;
		
		public void setSCInterfaceOperationCallback(
				SCInterfaceOperationCallback operationCallback) {
			this.operationCallback = operationCallback;
		}
		private boolean ev_out;
		
		private long ev_outValue;
		
		public boolean isRaisedEv_out() {
			return ev_out;
		}
		
		protected void raiseEv_out(long value) {
			ev_out = true;
			ev_outValue = value;
			for (SCInterfaceListener listener : listeners) {
				listener.onEv_outRaised(value);
			}
		}
		
		public long getEv_outValue() {
			if (! ev_out ) 
				throw new IllegalStateException("Illegal event value access. Event Ev_out is not raised!");
			return ev_outValue;
		}
		
		private boolean ev_in;
		
		private long ev_inValue;
		
		public void raiseEv_in(long value) {
			ev_in = true;
			ev_inValue = value;
		}
		
		protected long getEv_inValue() {
			if (! ev_in ) 
				throw new IllegalStateException("Illegal event value access. Event Ev_in is not raised!");
			return ev_inValue;
		}
		
		private long myVar;
		
		public long getMyVar() {
			return myVar;
		}
		
		public void setMyVar(long value) {
			this.myVar = value;
		}
		
		private long afterCalls;
		
		public long getAfterCalls() {
			return afterCalls;
		}
		
		public void setAfterCalls(long value) {
			this.afterCalls = value;
		}
		
		private long cycles;
		
		public long getCycles() {
			return cycles;
		}
		
		public void setCycles(long value) {
			this.cycles = value;
		}
		
		private long s2_entered;
		
		public long getS2_entered() {
			return s2_entered;
		}
		
		public void setS2_entered(long value) {
			this.s2_entered = value;
		}
		
		protected void clearEvents() {
			ev_in = false;
		}
		protected void clearOutEvents() {
		
		ev_out = false;
		}
		
	}
	
	protected SCInterfaceImpl sCInterface;
	
	private boolean initialized = false;
	
	public enum State {
		main_region__final_,
		main_region_Composite_s1_s2,
		main_region_Composite_s1_s2_inner_region_s1,
		main_region_Composite_s1_s2_inner_region_s2,
		$NullState$
	};
	
	private final State[] stateVector = new State[1];
	
	private int nextStateIndex;
	
	private ITimer timer;
	
	private final boolean[] timeEvents = new boolean[3];
	public RunnableTestStatemachine() {
		sCInterface = new SCInterfaceImpl();
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
		sCInterface.setMyVar(0);
		
		sCInterface.setAfterCalls(0);
		
		sCInterface.setCycles(0);
		
		sCInterface.setS2_entered(0);
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
	* @see IStatemachine#isFinal()
	*/
	public boolean isFinal() {
		return (stateVector[0] == State.main_region__final_);
	}
	/**
	* This method resets the incoming events (time events included).
	*/
	protected void clearEvents() {
		sCInterface.clearEvents();
		for (int i=0; i<timeEvents.length; i++) {
			timeEvents[i] = false;
		}
	}
	
	/**
	* This method resets the outgoing events.
	*/
	protected void clearOutEvents() {
		sCInterface.clearOutEvents();
	}
	
	/**
	* Returns true if the given state is currently active otherwise false.
	*/
	public boolean isStateActive(State state) {
	
		switch (state) {
		case main_region__final_:
			return stateVector[0] == State.main_region__final_;
		case main_region_Composite_s1_s2:
			return stateVector[0].ordinal() >= State.
					main_region_Composite_s1_s2.ordinal()&& stateVector[0].ordinal() <= State.main_region_Composite_s1_s2_inner_region_s2.ordinal();
		case main_region_Composite_s1_s2_inner_region_s1:
			return stateVector[0] == State.main_region_Composite_s1_s2_inner_region_s1;
		case main_region_Composite_s1_s2_inner_region_s2:
			return stateVector[0] == State.main_region_Composite_s1_s2_inner_region_s2;
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
	
	public SCInterface getSCInterface() {
		return sCInterface;
	}
	
	public boolean isRaisedEv_out() {
		return sCInterface.isRaisedEv_out();
	}
	
	public long getEv_outValue() {
		return sCInterface.getEv_outValue();
	}
	
	public void raiseEv_in(long value) {
		sCInterface.raiseEv_in(value);
	}
	
	public long getMyVar() {
		return sCInterface.getMyVar();
	}
	
	public void setMyVar(long value) {
		sCInterface.setMyVar(value);
	}
	
	public long getAfterCalls() {
		return sCInterface.getAfterCalls();
	}
	
	public void setAfterCalls(long value) {
		sCInterface.setAfterCalls(value);
	}
	
	public long getCycles() {
		return sCInterface.getCycles();
	}
	
	public void setCycles(long value) {
		sCInterface.setCycles(value);
	}
	
	public long getS2_entered() {
		return sCInterface.getS2_entered();
	}
	
	public void setS2_entered(long value) {
		sCInterface.setS2_entered(value);
	}
	
	private boolean check__lr0() {
		return timeEvents[2];
	}
	
	private boolean check__lr1() {
		return true;
	}
	
	private boolean check_main_region_Composite_s1_s2_tr0_tr0() {
		return timeEvents[0];
	}
	
	private boolean check_main_region_Composite_s1_s2_inner_region_s1_tr0_tr0() {
		return sCInterface.ev_in;
	}
	
	private boolean check_main_region_Composite_s1_s2_inner_region_s1_tr1_tr1() {
		return timeEvents[1];
	}
	
	private boolean check_main_region_Composite_s1_s2_inner_region_s2_tr0_tr0() {
		return sCInterface.ev_in;
	}
	
	private void effect__lr0() {
		sCInterface.operationCallback.displayTime();
	}
	
	private void effect__lr1() {
		sCInterface.setCycles(sCInterface.getCycles() + 1);
	}
	
	private void effect_main_region_Composite_s1_s2_tr0() {
		exitSequence_main_region_Composite_s1_s2();
		enterSequence_main_region__final__default();
	}
	
	private void effect_main_region_Composite_s1_s2_inner_region_s1_tr0() {
		exitSequence_main_region_Composite_s1_s2_inner_region_s1();
		enterSequence_main_region_Composite_s1_s2_inner_region_s2_default();
	}
	
	private void effect_main_region_Composite_s1_s2_inner_region_s1_tr1() {
		exitSequence_main_region_Composite_s1_s2_inner_region_s1();
		sCInterface.setAfterCalls(sCInterface.getAfterCalls() + 1);
		
		enterSequence_main_region_Composite_s1_s2_inner_region_s1_default();
	}
	
	private void effect_main_region_Composite_s1_s2_inner_region_s2_tr0() {
		exitSequence_main_region_Composite_s1_s2_inner_region_s2();
		enterSequence_main_region_Composite_s1_s2_inner_region_s1_default();
	}
	
	/* Entry action for statechart 'RunnableTest'. */
	private void entryAction() {
		timer.setTimer(this, 2, 1*1000, true);
	}
	
	/* Entry action for state 'Composite_s1_s2'. */
	private void entryAction_main_region_Composite_s1_s2() {
		timer.setTimer(this, 0, 10*1000, false);
	}
	
	/* Entry action for state 's1'. */
	private void entryAction_main_region_Composite_s1_s2_inner_region_s1() {
		timer.setTimer(this, 1, 500, false);
		
		sCInterface.raiseEv_out(2);
	}
	
	/* Entry action for state 's2'. */
	private void entryAction_main_region_Composite_s1_s2_inner_region_s2() {
		sCInterface.setS2_entered(sCInterface.getS2_entered() + 1);
	}
	
	/* Exit action for state 'RunnableTest'. */
	private void exitAction() {
		timer.unsetTimer(this, 2);
	}
	
	/* Exit action for state 'Composite_s1_s2'. */
	private void exitAction_main_region_Composite_s1_s2() {
		timer.unsetTimer(this, 0);
	}
	
	/* Exit action for state 's1'. */
	private void exitAction_main_region_Composite_s1_s2_inner_region_s1() {
		timer.unsetTimer(this, 1);
	}
	
	/* Default enter sequence for state null */
	private void enterSequence_main_region__final__default() {
		nextStateIndex = 0;
		stateVector[0] = State.main_region__final_;
	}
	
	/* 'default' enter sequence for state Composite_s1_s2 */
	private void enterSequence_main_region_Composite_s1_s2_default() {
		entryAction_main_region_Composite_s1_s2();
		enterSequence_main_region_Composite_s1_s2_inner_region_default();
	}
	
	/* 'default' enter sequence for state s1 */
	private void enterSequence_main_region_Composite_s1_s2_inner_region_s1_default() {
		entryAction_main_region_Composite_s1_s2_inner_region_s1();
		nextStateIndex = 0;
		stateVector[0] = State.main_region_Composite_s1_s2_inner_region_s1;
	}
	
	/* 'default' enter sequence for state s2 */
	private void enterSequence_main_region_Composite_s1_s2_inner_region_s2_default() {
		entryAction_main_region_Composite_s1_s2_inner_region_s2();
		nextStateIndex = 0;
		stateVector[0] = State.main_region_Composite_s1_s2_inner_region_s2;
	}
	
	/* 'default' enter sequence for region main region */
	private void enterSequence_main_region_default() {
		react_main_region__entry_Default();
	}
	
	/* 'default' enter sequence for region inner region */
	private void enterSequence_main_region_Composite_s1_s2_inner_region_default() {
		react_main_region_Composite_s1_s2_inner_region__entry_Default();
	}
	
	/* Default exit sequence for final state. */
	private void exitSequence_main_region__final_() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state Composite_s1_s2 */
	private void exitSequence_main_region_Composite_s1_s2() {
		exitSequence_main_region_Composite_s1_s2_inner_region();
		exitAction_main_region_Composite_s1_s2();
	}
	
	/* Default exit sequence for state s1 */
	private void exitSequence_main_region_Composite_s1_s2_inner_region_s1() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
		
		exitAction_main_region_Composite_s1_s2_inner_region_s1();
	}
	
	/* Default exit sequence for state s2 */
	private void exitSequence_main_region_Composite_s1_s2_inner_region_s2() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for region main region */
	private void exitSequence_main_region() {
		switch (stateVector[0]) {
		case main_region__final_:
			exitSequence_main_region__final_();
			break;
		case main_region_Composite_s1_s2_inner_region_s1:
			exitSequence_main_region_Composite_s1_s2_inner_region_s1();
			exitAction_main_region_Composite_s1_s2();
			break;
		case main_region_Composite_s1_s2_inner_region_s2:
			exitSequence_main_region_Composite_s1_s2_inner_region_s2();
			exitAction_main_region_Composite_s1_s2();
			break;
		default:
			break;
		}
	}
	
	/* Default exit sequence for region inner region */
	private void exitSequence_main_region_Composite_s1_s2_inner_region() {
		switch (stateVector[0]) {
		case main_region_Composite_s1_s2_inner_region_s1:
			exitSequence_main_region_Composite_s1_s2_inner_region_s1();
			break;
		case main_region_Composite_s1_s2_inner_region_s2:
			exitSequence_main_region_Composite_s1_s2_inner_region_s2();
			break;
		default:
			break;
		}
	}
	
	/* The reactions of state null. */
	private void react_main_region__final_() {
		if (check__lr0()) {
			effect__lr0();
		}
		effect__lr1();
	}
	
	/* The reactions of state s1. */
	private void react_main_region_Composite_s1_s2_inner_region_s1() {
		if (check__lr0()) {
			effect__lr0();
		}
		effect__lr1();
		if (check_main_region_Composite_s1_s2_tr0_tr0()) {
			effect_main_region_Composite_s1_s2_tr0();
		} else {
			if (check_main_region_Composite_s1_s2_inner_region_s1_tr0_tr0()) {
				effect_main_region_Composite_s1_s2_inner_region_s1_tr0();
			} else {
				if (check_main_region_Composite_s1_s2_inner_region_s1_tr1_tr1()) {
					effect_main_region_Composite_s1_s2_inner_region_s1_tr1();
				}
			}
		}
	}
	
	/* The reactions of state s2. */
	private void react_main_region_Composite_s1_s2_inner_region_s2() {
		if (check__lr0()) {
			effect__lr0();
		}
		effect__lr1();
		if (check_main_region_Composite_s1_s2_tr0_tr0()) {
			effect_main_region_Composite_s1_s2_tr0();
		} else {
			if (check_main_region_Composite_s1_s2_inner_region_s2_tr0_tr0()) {
				effect_main_region_Composite_s1_s2_inner_region_s2_tr0();
			}
		}
	}
	
	/* Default react sequence for initial entry  */
	private void react_main_region__entry_Default() {
		enterSequence_main_region_Composite_s1_s2_default();
	}
	
	/* Default react sequence for initial entry  */
	private void react_main_region_Composite_s1_s2_inner_region__entry_Default() {
		enterSequence_main_region_Composite_s1_s2_inner_region_s1_default();
	}
	
	public void runCycle() {
		if (!initialized)
			throw new IllegalStateException(
					"The state machine needs to be initialized first by calling the init() function.");
		clearOutEvents();
		for (nextStateIndex = 0; nextStateIndex < stateVector.length; nextStateIndex++) {
			switch (stateVector[nextStateIndex]) {
			case main_region__final_:
				react_main_region__final_();
				break;
			case main_region_Composite_s1_s2_inner_region_s1:
				react_main_region_Composite_s1_s2_inner_region_s1();
				break;
			case main_region_Composite_s1_s2_inner_region_s2:
				react_main_region_Composite_s1_s2_inner_region_s2();
				break;
			default:
				// $NullState$
			}
		}
		clearEvents();
	}
}
