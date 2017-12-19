package org.yakindu.scr.entryreactionaction;

public class EntryReactionActionStatemachine implements IEntryReactionActionStatemachine {

	protected class SCInterfaceImpl implements SCInterface {
	
		private boolean b;
		
		public void raiseB() {
			b = true;
		}
		
		private boolean d;
		
		public void raiseD() {
			d = true;
		}
		
		private boolean enteredR1;
		
		public boolean getEnteredR1() {
			return enteredR1;
		}
		
		public void setEnteredR1(boolean value) {
			this.enteredR1 = value;
		}
		
		private boolean enteredR2;
		
		public boolean getEnteredR2() {
			return enteredR2;
		}
		
		public void setEnteredR2(boolean value) {
			this.enteredR2 = value;
		}
		
		private boolean enteredBdefault;
		
		public boolean getEnteredBdefault() {
			return enteredBdefault;
		}
		
		public void setEnteredBdefault(boolean value) {
			this.enteredBdefault = value;
		}
		
		private boolean enteredBother;
		
		public boolean getEnteredBother() {
			return enteredBother;
		}
		
		public void setEnteredBother(boolean value) {
			this.enteredBother = value;
		}
		
		protected void clearEvents() {
			b = false;
			d = false;
		}
	}
	
	protected SCInterfaceImpl sCInterface;
	
	private boolean initialized = false;
	
	public enum State {
		entryReactionAction_r2_B,
		entryReactionAction_r2_B_r_BA,
		entryReactionAction_r2_B_r_BB,
		entryReactionAction_r2_D,
		entryReactionAction_r1_A,
		$NullState$
	};
	
	private State[] historyVector = new State[1];
	private final State[] stateVector = new State[2];
	
	private int nextStateIndex;
	
	public EntryReactionActionStatemachine() {
		sCInterface = new SCInterfaceImpl();
	}
	
	public void init() {
		this.initialized = true;
		for (int i = 0; i < 2; i++) {
			stateVector[i] = State.$NullState$;
		}
		for (int i = 0; i < 1; i++) {
			historyVector[i] = State.$NullState$;
		}
		clearEvents();
		clearOutEvents();
		sCInterface.setEnteredR1(false);
		
		sCInterface.setEnteredR2(false);
		
		sCInterface.setEnteredBdefault(false);
		
		sCInterface.setEnteredBother(false);
	}
	
	public void enter() {
		if (!initialized) {
			throw new IllegalStateException(
					"The state machine needs to be initialized first by calling the init() function.");
		}
		enterSequence_EntryReactionAction_r2_default();
		enterSequence_EntryReactionAction_r1_default();
	}
	
	public void exit() {
		exitSequence_EntryReactionAction_r2();
		exitSequence_EntryReactionAction_r1();
	}
	
	/**
	 * @see IStatemachine#isActive()
	 */
	public boolean isActive() {
		return stateVector[0] != State.$NullState$||stateVector[1] != State.$NullState$;
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
		case entryReactionAction_r2_B:
			return stateVector[0].ordinal() >= State.
					entryReactionAction_r2_B.ordinal()&& stateVector[0].ordinal() <= State.entryReactionAction_r2_B_r_BB.ordinal();
		case entryReactionAction_r2_B_r_BA:
			return stateVector[0] == State.entryReactionAction_r2_B_r_BA;
		case entryReactionAction_r2_B_r_BB:
			return stateVector[0] == State.entryReactionAction_r2_B_r_BB;
		case entryReactionAction_r2_D:
			return stateVector[0] == State.entryReactionAction_r2_D;
		case entryReactionAction_r1_A:
			return stateVector[1] == State.entryReactionAction_r1_A;
		default:
			return false;
		}
	}
	
	public SCInterface getSCInterface() {
		return sCInterface;
	}
	
	public void raiseB() {
		sCInterface.raiseB();
	}
	
	public void raiseD() {
		sCInterface.raiseD();
	}
	
	public boolean getEnteredR1() {
		return sCInterface.getEnteredR1();
	}
	
	public void setEnteredR1(boolean value) {
		sCInterface.setEnteredR1(value);
	}
	
	public boolean getEnteredR2() {
		return sCInterface.getEnteredR2();
	}
	
	public void setEnteredR2(boolean value) {
		sCInterface.setEnteredR2(value);
	}
	
	public boolean getEnteredBdefault() {
		return sCInterface.getEnteredBdefault();
	}
	
	public void setEnteredBdefault(boolean value) {
		sCInterface.setEnteredBdefault(value);
	}
	
	public boolean getEnteredBother() {
		return sCInterface.getEnteredBother();
	}
	
	public void setEnteredBother(boolean value) {
		sCInterface.setEnteredBother(value);
	}
	
	private boolean check_EntryReactionAction_r2_B_tr0_tr0() {
		return sCInterface.d;
	}
	
	private boolean check_EntryReactionAction_r2_B_r_BA_tr0_tr0() {
		return sCInterface.b;
	}
	
	private boolean check_EntryReactionAction_r2_B_r_BB_tr0_tr0() {
		return sCInterface.b;
	}
	
	private boolean check_EntryReactionAction_r2_D_tr0_tr0() {
		return sCInterface.b;
	}
	
	private boolean check_EntryReactionAction_r2_D_tr1_tr1() {
		return sCInterface.d;
	}
	
	private void effect_EntryReactionAction_r2_B_tr0() {
		exitSequence_EntryReactionAction_r2_B();
		enterSequence_EntryReactionAction_r2_D_default();
	}
	
	private void effect_EntryReactionAction_r2_B_r_BA_tr0() {
		exitSequence_EntryReactionAction_r2_B_r_BA();
		enterSequence_EntryReactionAction_r2_B_r_BB_default();
	}
	
	private void effect_EntryReactionAction_r2_B_r_BB_tr0() {
		exitSequence_EntryReactionAction_r2_B_r_BB();
		enterSequence_EntryReactionAction_r2_B_r_BA_default();
	}
	
	private void effect_EntryReactionAction_r2_D_tr0() {
		exitSequence_EntryReactionAction_r2_D();
		enterSequence_EntryReactionAction_r2_B_other();
	}
	
	private void effect_EntryReactionAction_r2_D_tr1() {
		exitSequence_EntryReactionAction_r2_D();
		enterSequence_EntryReactionAction_r2_B_default();
	}
	
	/* 'default' enter sequence for state B */
	private void enterSequence_EntryReactionAction_r2_B_default() {
		enterSequence_EntryReactionAction_r2_B_r_default();
	}
	
	/* 'other' enter sequence for state B */
	private void enterSequence_EntryReactionAction_r2_B_other() {
		enterSequence_EntryReactionAction_r2_B_r_other();
	}
	
	/* 'default' enter sequence for state BA */
	private void enterSequence_EntryReactionAction_r2_B_r_BA_default() {
		nextStateIndex = 0;
		stateVector[0] = State.entryReactionAction_r2_B_r_BA;
		
		historyVector[0] = stateVector[0];
	}
	
	/* 'default' enter sequence for state BB */
	private void enterSequence_EntryReactionAction_r2_B_r_BB_default() {
		nextStateIndex = 0;
		stateVector[0] = State.entryReactionAction_r2_B_r_BB;
		
		historyVector[0] = stateVector[0];
	}
	
	/* 'default' enter sequence for state D */
	private void enterSequence_EntryReactionAction_r2_D_default() {
		nextStateIndex = 0;
		stateVector[0] = State.entryReactionAction_r2_D;
	}
	
	/* 'default' enter sequence for state A */
	private void enterSequence_EntryReactionAction_r1_A_default() {
		nextStateIndex = 1;
		stateVector[1] = State.entryReactionAction_r1_A;
	}
	
	/* 'default' enter sequence for region r2 */
	private void enterSequence_EntryReactionAction_r2_default() {
		react_EntryReactionAction_r2_default();
	}
	
	/* 'default' enter sequence for region r */
	private void enterSequence_EntryReactionAction_r2_B_r_default() {
		react_EntryReactionAction_r2_B_r_default();
	}
	
	/* 'other' enter sequence for region r */
	private void enterSequence_EntryReactionAction_r2_B_r_other() {
		react_EntryReactionAction_r2_B_r_other();
	}
	
	/* shallow enterSequence with history in child r */
	private void shallowEnterSequence_EntryReactionAction_r2_B_r() {
		switch (historyVector[0]) {
		case entryReactionAction_r2_B_r_BA:
			enterSequence_EntryReactionAction_r2_B_r_BA_default();
			break;
		case entryReactionAction_r2_B_r_BB:
			enterSequence_EntryReactionAction_r2_B_r_BB_default();
			break;
		default:
			break;
		}
	}
	
	/* 'default' enter sequence for region r1 */
	private void enterSequence_EntryReactionAction_r1_default() {
		react_EntryReactionAction_r1__entry_Default();
	}
	
	/* Default exit sequence for state B */
	private void exitSequence_EntryReactionAction_r2_B() {
		exitSequence_EntryReactionAction_r2_B_r();
	}
	
	/* Default exit sequence for state BA */
	private void exitSequence_EntryReactionAction_r2_B_r_BA() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state BB */
	private void exitSequence_EntryReactionAction_r2_B_r_BB() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state D */
	private void exitSequence_EntryReactionAction_r2_D() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state A */
	private void exitSequence_EntryReactionAction_r1_A() {
		nextStateIndex = 1;
		stateVector[1] = State.$NullState$;
	}
	
	/* Default exit sequence for region r2 */
	private void exitSequence_EntryReactionAction_r2() {
		switch (stateVector[0]) {
		case entryReactionAction_r2_B_r_BA:
			exitSequence_EntryReactionAction_r2_B_r_BA();
			break;
		case entryReactionAction_r2_B_r_BB:
			exitSequence_EntryReactionAction_r2_B_r_BB();
			break;
		case entryReactionAction_r2_D:
			exitSequence_EntryReactionAction_r2_D();
			break;
		default:
			break;
		}
	}
	
	/* Default exit sequence for region r */
	private void exitSequence_EntryReactionAction_r2_B_r() {
		switch (stateVector[0]) {
		case entryReactionAction_r2_B_r_BA:
			exitSequence_EntryReactionAction_r2_B_r_BA();
			break;
		case entryReactionAction_r2_B_r_BB:
			exitSequence_EntryReactionAction_r2_B_r_BB();
			break;
		default:
			break;
		}
	}
	
	/* Default exit sequence for region r1 */
	private void exitSequence_EntryReactionAction_r1() {
		switch (stateVector[1]) {
		case entryReactionAction_r1_A:
			exitSequence_EntryReactionAction_r1_A();
			break;
		default:
			break;
		}
	}
	
	/* The reactions of state BA. */
	private void react_EntryReactionAction_r2_B_r_BA() {
		if (check_EntryReactionAction_r2_B_tr0_tr0()) {
			effect_EntryReactionAction_r2_B_tr0();
		} else {
			if (check_EntryReactionAction_r2_B_r_BA_tr0_tr0()) {
				effect_EntryReactionAction_r2_B_r_BA_tr0();
			}
		}
	}
	
	/* The reactions of state BB. */
	private void react_EntryReactionAction_r2_B_r_BB() {
		if (check_EntryReactionAction_r2_B_tr0_tr0()) {
			effect_EntryReactionAction_r2_B_tr0();
		} else {
			if (check_EntryReactionAction_r2_B_r_BB_tr0_tr0()) {
				effect_EntryReactionAction_r2_B_r_BB_tr0();
			}
		}
	}
	
	/* The reactions of state D. */
	private void react_EntryReactionAction_r2_D() {
		if (check_EntryReactionAction_r2_D_tr0_tr0()) {
			effect_EntryReactionAction_r2_D_tr0();
		} else {
			if (check_EntryReactionAction_r2_D_tr1_tr1()) {
				effect_EntryReactionAction_r2_D_tr1();
			}
		}
	}
	
	/* The reactions of state A. */
	private void react_EntryReactionAction_r1_A() {
	}
	
	/* Default react sequence for initial entry default */
	private void react_EntryReactionAction_r2_default() {
		sCInterface.setEnteredR2(true);
		
		enterSequence_EntryReactionAction_r2_B_default();
	}
	
	/* Default react sequence for shallow history entry default */
	private void react_EntryReactionAction_r2_B_r_default() {
		/* Enter the region with shallow history */
		if (historyVector[0] != State.$NullState$) {
			shallowEnterSequence_EntryReactionAction_r2_B_r();
		} else {
			sCInterface.setEnteredBdefault(true);
			
			enterSequence_EntryReactionAction_r2_B_r_BA_default();
		}
	}
	
	/* Default react sequence for initial entry other */
	private void react_EntryReactionAction_r2_B_r_other() {
		sCInterface.setEnteredBother(true);
		
		enterSequence_EntryReactionAction_r2_B_r_BB_default();
	}
	
	/* Default react sequence for initial entry  */
	private void react_EntryReactionAction_r1__entry_Default() {
		sCInterface.setEnteredR1(true);
		
		enterSequence_EntryReactionAction_r1_A_default();
	}
	
	public void runCycle() {
		if (!initialized)
			throw new IllegalStateException(
					"The state machine needs to be initialized first by calling the init() function.");
		clearOutEvents();
		for (nextStateIndex = 0; nextStateIndex < stateVector.length; nextStateIndex++) {
			switch (stateVector[nextStateIndex]) {
			case entryReactionAction_r2_B_r_BA:
				react_EntryReactionAction_r2_B_r_BA();
				break;
			case entryReactionAction_r2_B_r_BB:
				react_EntryReactionAction_r2_B_r_BB();
				break;
			case entryReactionAction_r2_D:
				react_EntryReactionAction_r2_D();
				break;
			case entryReactionAction_r1_A:
				react_EntryReactionAction_r1_A();
				break;
			default:
				// $NullState$
			}
		}
		clearEvents();
	}
}
