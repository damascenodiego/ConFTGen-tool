package org.yakindu.scr.constonlyinternalscope;

public class ConstOnlyInternalScopeStatemachine implements IConstOnlyInternalScopeStatemachine {

	protected class SCInterfaceImpl implements SCInterface {
	
		private boolean e;
		
		private long eValue;
		
		public void raiseE(long value) {
			e = true;
			eValue = value;
		}
		
		protected long getEValue() {
			if (! e ) 
				throw new IllegalStateException("Illegal event value access. Event E is not raised!");
			return eValue;
		}
		
		protected void clearEvents() {
			e = false;
		}
	}
	
	protected SCInterfaceImpl sCInterface;
	
	private boolean initialized = false;
	
	public enum State {
		constOnlyInternalScope_main_region_A,
		constOnlyInternalScope_main_region_B,
		constOnlyInternalScope_main_region_C,
		$NullState$
	};
	
	private final State[] stateVector = new State[1];
	
	private int nextStateIndex;
	
	protected long getB() {
		return b;
	}
	
	protected long getC() {
		return c;
	}
	
	public ConstOnlyInternalScopeStatemachine() {
		sCInterface = new SCInterfaceImpl();
	}
	
	public void init() {
		this.initialized = true;
		for (int i = 0; i < 1; i++) {
			stateVector[i] = State.$NullState$;
		}
		clearEvents();
		clearOutEvents();
	}
	
	public void enter() {
		if (!initialized) {
			throw new IllegalStateException(
					"The state machine needs to be initialized first by calling the init() function.");
		}
		enterSequence_ConstOnlyInternalScope_main_region_default();
	}
	
	public void exit() {
		exitSequence_ConstOnlyInternalScope_main_region();
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
		sCInterface.clearEvents();
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
		case constOnlyInternalScope_main_region_A:
			return stateVector[0] == State.constOnlyInternalScope_main_region_A;
		case constOnlyInternalScope_main_region_B:
			return stateVector[0] == State.constOnlyInternalScope_main_region_B;
		case constOnlyInternalScope_main_region_C:
			return stateVector[0] == State.constOnlyInternalScope_main_region_C;
		default:
			return false;
		}
	}
	
	public SCInterface getSCInterface() {
		return sCInterface;
	}
	
	public void raiseE(long value) {
		sCInterface.raiseE(value);
	}
	
	private boolean check_ConstOnlyInternalScope_main_region_A_tr0_tr0() {
		return (sCInterface.e) && (sCInterface.getEValue()==getB());
	}
	
	private boolean check_ConstOnlyInternalScope_main_region_A_tr1_tr1() {
		return (sCInterface.e) && (sCInterface.getEValue()==getC());
	}
	
	private void effect_ConstOnlyInternalScope_main_region_A_tr0() {
		exitSequence_ConstOnlyInternalScope_main_region_A();
		enterSequence_ConstOnlyInternalScope_main_region_B_default();
	}
	
	private void effect_ConstOnlyInternalScope_main_region_A_tr1() {
		exitSequence_ConstOnlyInternalScope_main_region_A();
		enterSequence_ConstOnlyInternalScope_main_region_C_default();
	}
	
	/* 'default' enter sequence for state A */
	private void enterSequence_ConstOnlyInternalScope_main_region_A_default() {
		nextStateIndex = 0;
		stateVector[0] = State.constOnlyInternalScope_main_region_A;
	}
	
	/* 'default' enter sequence for state B */
	private void enterSequence_ConstOnlyInternalScope_main_region_B_default() {
		nextStateIndex = 0;
		stateVector[0] = State.constOnlyInternalScope_main_region_B;
	}
	
	/* 'default' enter sequence for state C */
	private void enterSequence_ConstOnlyInternalScope_main_region_C_default() {
		nextStateIndex = 0;
		stateVector[0] = State.constOnlyInternalScope_main_region_C;
	}
	
	/* 'default' enter sequence for region main region */
	private void enterSequence_ConstOnlyInternalScope_main_region_default() {
		react_ConstOnlyInternalScope_main_region__entry_Default();
	}
	
	/* Default exit sequence for state A */
	private void exitSequence_ConstOnlyInternalScope_main_region_A() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state B */
	private void exitSequence_ConstOnlyInternalScope_main_region_B() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state C */
	private void exitSequence_ConstOnlyInternalScope_main_region_C() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for region main region */
	private void exitSequence_ConstOnlyInternalScope_main_region() {
		switch (stateVector[0]) {
		case constOnlyInternalScope_main_region_A:
			exitSequence_ConstOnlyInternalScope_main_region_A();
			break;
		case constOnlyInternalScope_main_region_B:
			exitSequence_ConstOnlyInternalScope_main_region_B();
			break;
		case constOnlyInternalScope_main_region_C:
			exitSequence_ConstOnlyInternalScope_main_region_C();
			break;
		default:
			break;
		}
	}
	
	/* The reactions of state A. */
	private void react_ConstOnlyInternalScope_main_region_A() {
		if (check_ConstOnlyInternalScope_main_region_A_tr0_tr0()) {
			effect_ConstOnlyInternalScope_main_region_A_tr0();
		} else {
			if (check_ConstOnlyInternalScope_main_region_A_tr1_tr1()) {
				effect_ConstOnlyInternalScope_main_region_A_tr1();
			}
		}
	}
	
	/* The reactions of state B. */
	private void react_ConstOnlyInternalScope_main_region_B() {
	}
	
	/* The reactions of state C. */
	private void react_ConstOnlyInternalScope_main_region_C() {
	}
	
	/* Default react sequence for initial entry  */
	private void react_ConstOnlyInternalScope_main_region__entry_Default() {
		enterSequence_ConstOnlyInternalScope_main_region_A_default();
	}
	
	public void runCycle() {
		if (!initialized)
			throw new IllegalStateException(
					"The state machine needs to be initialized first by calling the init() function.");
		clearOutEvents();
		for (nextStateIndex = 0; nextStateIndex < stateVector.length; nextStateIndex++) {
			switch (stateVector[nextStateIndex]) {
			case constOnlyInternalScope_main_region_A:
				react_ConstOnlyInternalScope_main_region_A();
				break;
			case constOnlyInternalScope_main_region_B:
				react_ConstOnlyInternalScope_main_region_B();
				break;
			case constOnlyInternalScope_main_region_C:
				react_ConstOnlyInternalScope_main_region_C();
				break;
			default:
				// $NullState$
			}
		}
		clearEvents();
	}
}
