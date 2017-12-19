package org.yakindu.scr.logicalor;

public class LogicalOrStatemachine implements ILogicalOrStatemachine {

	protected class SCInterfaceImpl implements SCInterface {
	
		private long x;
		
		public long getX() {
			return x;
		}
		
		public void setX(long value) {
			this.x = value;
		}
		
		protected long assignX(long value) {
			return this.x = value;
		}
		
		private boolean b;
		
		public boolean getB() {
			return b;
		}
		
		public void setB(boolean value) {
			this.b = value;
		}
		
	}
	
	protected SCInterfaceImpl sCInterface;
	
	private boolean initialized = false;
	
	public enum State {
		main_region_A,
		$NullState$
	};
	
	private final State[] stateVector = new State[1];
	
	private int nextStateIndex;
	
	public LogicalOrStatemachine() {
		sCInterface = new SCInterfaceImpl();
	}
	
	public void init() {
		this.initialized = true;
		for (int i = 0; i < 1; i++) {
			stateVector[i] = State.$NullState$;
		}
		clearEvents();
		clearOutEvents();
		sCInterface.setX(1);
		
		sCInterface.setB(false);
	}
	
	public void enter() {
		if (!initialized) {
			throw new IllegalStateException(
					"The state machine needs to be initialized first by calling the init() function.");
		}
		enterSequence_main_region_default();
	}
	
	public void exit() {
		exitSequence_main_region();
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
	}
	
	/**
	* This method resets the outgoing events.
	*/
	protected void clearOutEvents() {
	}
	
	/**
	* Returns true if the given state is currently active otherwise false.
	*/
	public boolean isStateActive(State state) {
	
		switch (state) {
		case main_region_A:
			return stateVector[0] == State.main_region_A;
		default:
			return false;
		}
	}
	
	public SCInterface getSCInterface() {
		return sCInterface;
	}
	
	public long getX() {
		return sCInterface.getX();
	}
	
	public void setX(long value) {
		sCInterface.setX(value);
	}
	
	public boolean getB() {
		return sCInterface.getB();
	}
	
	public void setB(boolean value) {
		sCInterface.setB(value);
	}
	
	private boolean check_main_region_A_tr0_tr0() {
		return sCInterface.getX()==1;
	}
	
	private void effect_main_region_A_tr0() {
		exitSequence_main_region_A();
		sCInterface.setB(((sCInterface.assignX(sCInterface.getX() + 1))!=2 || (sCInterface.assignX(sCInterface.getX() * 2))==4));
		
		enterSequence_main_region_A_default();
	}
	
	/* 'default' enter sequence for state A */
	private void enterSequence_main_region_A_default() {
		nextStateIndex = 0;
		stateVector[0] = State.main_region_A;
	}
	
	/* 'default' enter sequence for region main region */
	private void enterSequence_main_region_default() {
		react_main_region__entry_Default();
	}
	
	/* Default exit sequence for state A */
	private void exitSequence_main_region_A() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for region main region */
	private void exitSequence_main_region() {
		switch (stateVector[0]) {
		case main_region_A:
			exitSequence_main_region_A();
			break;
		default:
			break;
		}
	}
	
	/* The reactions of state A. */
	private void react_main_region_A() {
		if (check_main_region_A_tr0_tr0()) {
			effect_main_region_A_tr0();
		}
	}
	
	/* Default react sequence for initial entry  */
	private void react_main_region__entry_Default() {
		enterSequence_main_region_A_default();
	}
	
	public void runCycle() {
		if (!initialized)
			throw new IllegalStateException(
					"The state machine needs to be initialized first by calling the init() function.");
		clearOutEvents();
		for (nextStateIndex = 0; nextStateIndex < stateVector.length; nextStateIndex++) {
			switch (stateVector[nextStateIndex]) {
			case main_region_A:
				react_main_region_A();
				break;
			default:
				// $NullState$
			}
		}
		clearEvents();
	}
}
