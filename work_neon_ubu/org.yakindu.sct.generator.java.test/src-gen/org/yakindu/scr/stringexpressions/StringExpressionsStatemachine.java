package org.yakindu.scr.stringexpressions;

public class StringExpressionsStatemachine implements IStringExpressionsStatemachine {

	protected class SCInterfaceImpl implements SCInterface {
	
		private boolean e;
		
		public void raiseE() {
			e = true;
		}
		
		private String stringA;
		
		public String getStringA() {
			return stringA;
		}
		
		public void setStringA(String value) {
			this.stringA = value;
		}
		
		private String stringA2;
		
		public String getStringA2() {
			return stringA2;
		}
		
		public void setStringA2(String value) {
			this.stringA2 = value;
		}
		
		private String stringB;
		
		public String getStringB() {
			return stringB;
		}
		
		public void setStringB(String value) {
			this.stringB = value;
		}
		
		private String quotedStringX;
		
		public String getQuotedStringX() {
			return quotedStringX;
		}
		
		public void setQuotedStringX(String value) {
			this.quotedStringX = value;
		}
		
		private String quotedStringY;
		
		public String getQuotedStringY() {
			return quotedStringY;
		}
		
		public void setQuotedStringY(String value) {
			this.quotedStringY = value;
		}
		
		private boolean stringVarEqual;
		
		public boolean getStringVarEqual() {
			return stringVarEqual;
		}
		
		public void setStringVarEqual(boolean value) {
			this.stringVarEqual = value;
		}
		
		private boolean stringVarNotEqual;
		
		public boolean getStringVarNotEqual() {
			return stringVarNotEqual;
		}
		
		public void setStringVarNotEqual(boolean value) {
			this.stringVarNotEqual = value;
		}
		
		private boolean guardStringNotEqual;
		
		public boolean getGuardStringNotEqual() {
			return guardStringNotEqual;
		}
		
		public void setGuardStringNotEqual(boolean value) {
			this.guardStringNotEqual = value;
		}
		
		private boolean guardStringEqual;
		
		public boolean getGuardStringEqual() {
			return guardStringEqual;
		}
		
		public void setGuardStringEqual(boolean value) {
			this.guardStringEqual = value;
		}
		
		protected void clearEvents() {
			e = false;
		}
	}
	
	protected SCInterfaceImpl sCInterface;
	
	private boolean initialized = false;
	
	public enum State {
		main_region_AssignmentChecked,
		main_region_Failed,
		main_region_VarToVarCompareSucceeded,
		main_region_VarToConstCompareSucceeded,
		main_region_ConstToVarCompareSucceeded,
		main_region_ConstToConstCompareSucceeded,
		$NullState$
	};
	
	private final State[] stateVector = new State[1];
	
	private int nextStateIndex;
	
	public StringExpressionsStatemachine() {
		sCInterface = new SCInterfaceImpl();
	}
	
	public void init() {
		this.initialized = true;
		for (int i = 0; i < 1; i++) {
			stateVector[i] = State.$NullState$;
		}
		clearEvents();
		clearOutEvents();
		sCInterface.setStringA("A");
		
		sCInterface.setStringA2("A");
		
		sCInterface.setStringB("B");
		
		sCInterface.setQuotedStringX("\"X\"");
		
		sCInterface.setQuotedStringY("\"Y\"");
		
		sCInterface.setStringVarEqual(false);
		
		sCInterface.setStringVarNotEqual(false);
		
		sCInterface.setGuardStringNotEqual(false);
		
		sCInterface.setGuardStringEqual(false);
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
		case main_region_AssignmentChecked:
			return stateVector[0] == State.main_region_AssignmentChecked;
		case main_region_Failed:
			return stateVector[0] == State.main_region_Failed;
		case main_region_VarToVarCompareSucceeded:
			return stateVector[0] == State.main_region_VarToVarCompareSucceeded;
		case main_region_VarToConstCompareSucceeded:
			return stateVector[0] == State.main_region_VarToConstCompareSucceeded;
		case main_region_ConstToVarCompareSucceeded:
			return stateVector[0] == State.main_region_ConstToVarCompareSucceeded;
		case main_region_ConstToConstCompareSucceeded:
			return stateVector[0] == State.main_region_ConstToConstCompareSucceeded;
		default:
			return false;
		}
	}
	
	public SCInterface getSCInterface() {
		return sCInterface;
	}
	
	public void raiseE() {
		sCInterface.raiseE();
	}
	
	public String getStringA() {
		return sCInterface.getStringA();
	}
	
	public void setStringA(String value) {
		sCInterface.setStringA(value);
	}
	
	public String getStringA2() {
		return sCInterface.getStringA2();
	}
	
	public void setStringA2(String value) {
		sCInterface.setStringA2(value);
	}
	
	public String getStringB() {
		return sCInterface.getStringB();
	}
	
	public void setStringB(String value) {
		sCInterface.setStringB(value);
	}
	
	public String getQuotedStringX() {
		return sCInterface.getQuotedStringX();
	}
	
	public void setQuotedStringX(String value) {
		sCInterface.setQuotedStringX(value);
	}
	
	public String getQuotedStringY() {
		return sCInterface.getQuotedStringY();
	}
	
	public void setQuotedStringY(String value) {
		sCInterface.setQuotedStringY(value);
	}
	
	public boolean getStringVarEqual() {
		return sCInterface.getStringVarEqual();
	}
	
	public void setStringVarEqual(boolean value) {
		sCInterface.setStringVarEqual(value);
	}
	
	public boolean getStringVarNotEqual() {
		return sCInterface.getStringVarNotEqual();
	}
	
	public void setStringVarNotEqual(boolean value) {
		sCInterface.setStringVarNotEqual(value);
	}
	
	public boolean getGuardStringNotEqual() {
		return sCInterface.getGuardStringNotEqual();
	}
	
	public void setGuardStringNotEqual(boolean value) {
		sCInterface.setGuardStringNotEqual(value);
	}
	
	public boolean getGuardStringEqual() {
		return sCInterface.getGuardStringEqual();
	}
	
	public void setGuardStringEqual(boolean value) {
		sCInterface.setGuardStringEqual(value);
	}
	
	private boolean check_main_region_AssignmentChecked_tr0_tr0() {
		return sCInterface.e;
	}
	
	private boolean check_main_region_VarToVarCompareSucceeded_tr0_tr0() {
		return sCInterface.e;
	}
	
	private boolean check_main_region_VarToConstCompareSucceeded_tr0_tr0() {
		return sCInterface.e;
	}
	
	private boolean check_main_region_ConstToVarCompareSucceeded_tr0_tr0() {
		return sCInterface.e;
	}
	
	private boolean check_main_region__choice_0_tr1_tr1() {
		return sCInterface.e;
	}
	
	private boolean check_main_region__choice_0_tr0() {
		return true;
	}
	
	private boolean check_main_region__choice_1_tr1_tr1() {
		return (sCInterface.e) && ((sCInterface.getStringA()== null?sCInterface.getStringA2() ==null :sCInterface.getStringA().equals(sCInterface.getStringA2())));
	}
	
	private boolean check_main_region__choice_1_tr0() {
		return true;
	}
	
	private boolean check_main_region__choice_2_tr1_tr1() {
		return (sCInterface.e) && ((sCInterface.getStringA()== null?sCInterface.getStringB() !=null : !sCInterface.getStringA().equals(sCInterface.getStringB())));
	}
	
	private boolean check_main_region__choice_2_tr0() {
		return true;
	}
	
	private boolean check_main_region__choice_3_tr0_tr0() {
		return sCInterface.e;
	}
	
	private boolean check_main_region__choice_3_tr1() {
		return true;
	}
	
	private boolean check_main_region__choice_4_tr1_tr1() {
		return (sCInterface.e) && ((sCInterface.getStringA()== null?"A" ==null :sCInterface.getStringA().equals("A")));
	}
	
	private boolean check_main_region__choice_4_tr0() {
		return true;
	}
	
	private boolean check_main_region__choice_5_tr1_tr1() {
		return (sCInterface.e) && ((sCInterface.getStringA()== null?"B" !=null : !sCInterface.getStringA().equals("B")));
	}
	
	private boolean check_main_region__choice_5_tr0() {
		return true;
	}
	
	private boolean check_main_region__choice_6_tr0() {
		return true;
	}
	
	private boolean check_main_region__choice_7_tr0_tr0() {
		return sCInterface.e;
	}
	
	private boolean check_main_region__choice_7_tr1() {
		return true;
	}
	
	private boolean check_main_region__choice_8_tr1_tr1() {
		return (sCInterface.e) && (("A"== null?sCInterface.getStringA() ==null :"A".equals(sCInterface.getStringA())));
	}
	
	private boolean check_main_region__choice_8_tr0() {
		return true;
	}
	
	private boolean check_main_region__choice_9_tr1_tr1() {
		return (sCInterface.e) && (("A"== null?sCInterface.getStringB() !=null : !"A".equals(sCInterface.getStringB())));
	}
	
	private boolean check_main_region__choice_9_tr0() {
		return true;
	}
	
	private boolean check_main_region__choice_10_tr0() {
		return true;
	}
	
	private boolean check_main_region__choice_11_tr0_tr0() {
		return sCInterface.e;
	}
	
	private boolean check_main_region__choice_11_tr1() {
		return true;
	}
	
	private boolean check_main_region__choice_12_tr1_tr1() {
		return (sCInterface.e) && (("A"== null?"A" ==null :"A".equals("A")));
	}
	
	private boolean check_main_region__choice_12_tr0() {
		return true;
	}
	
	private boolean check_main_region__choice_13_tr1_tr1() {
		return (sCInterface.e) && (("A"== null?"B" !=null : !"A".equals("B")));
	}
	
	private boolean check_main_region__choice_13_tr0() {
		return true;
	}
	
	private boolean check_main_region__choice_14_tr0() {
		return true;
	}
	
	private void effect_main_region_AssignmentChecked_tr0() {
		exitSequence_main_region_AssignmentChecked();
		sCInterface.setStringVarEqual((sCInterface.stringA== null?sCInterface.stringA2 ==null :sCInterface.stringA.equals(sCInterface.stringA2)));
		
		react_main_region__choice_0();
	}
	
	private void effect_main_region_VarToVarCompareSucceeded_tr0() {
		exitSequence_main_region_VarToVarCompareSucceeded();
		sCInterface.setStringVarEqual((sCInterface.stringA== null?"A" ==null :sCInterface.stringA.equals("A")));
		
		react_main_region__choice_3();
	}
	
	private void effect_main_region_VarToConstCompareSucceeded_tr0() {
		exitSequence_main_region_VarToConstCompareSucceeded();
		sCInterface.setStringVarEqual(("A"== null?sCInterface.stringA ==null :"A".equals(sCInterface.stringA)));
		
		react_main_region__choice_7();
	}
	
	private void effect_main_region_ConstToVarCompareSucceeded_tr0() {
		exitSequence_main_region_ConstToVarCompareSucceeded();
		sCInterface.setStringVarEqual(("A"== null?"A" ==null :"A".equals("A")));
		
		react_main_region__choice_11();
	}
	
	private void effect_main_region__choice_0_tr1() {
		sCInterface.setStringVarNotEqual((sCInterface.stringA== null?sCInterface.stringB !=null : !sCInterface.stringA.equals(sCInterface.stringB)));
		
		react_main_region__choice_1();
	}
	
	private void effect_main_region__choice_0_tr0() {
		enterSequence_main_region_Failed_default();
	}
	
	private void effect_main_region__choice_1_tr1() {
		sCInterface.setGuardStringEqual((sCInterface.stringA== null?sCInterface.stringA2 ==null :sCInterface.stringA.equals(sCInterface.stringA2)));
		
		react_main_region__choice_2();
	}
	
	private void effect_main_region__choice_1_tr0() {
		enterSequence_main_region_Failed_default();
	}
	
	private void effect_main_region__choice_2_tr1() {
		sCInterface.setGuardStringNotEqual((sCInterface.stringA== null?sCInterface.stringB !=null : !sCInterface.stringA.equals(sCInterface.stringB)));
		
		enterSequence_main_region_VarToVarCompareSucceeded_default();
	}
	
	private void effect_main_region__choice_2_tr0() {
		enterSequence_main_region_Failed_default();
	}
	
	private void effect_main_region__choice_3_tr0() {
		sCInterface.setStringVarNotEqual((sCInterface.stringA== null?"B" !=null : !sCInterface.stringA.equals("B")));
		
		react_main_region__choice_4();
	}
	
	private void effect_main_region__choice_3_tr1() {
		react_main_region__choice_6();
	}
	
	private void effect_main_region__choice_4_tr1() {
		sCInterface.setGuardStringEqual((sCInterface.stringA== null?"A" ==null :sCInterface.stringA.equals("A")));
		
		react_main_region__choice_5();
	}
	
	private void effect_main_region__choice_4_tr0() {
		react_main_region__choice_6();
	}
	
	private void effect_main_region__choice_5_tr1() {
		sCInterface.setGuardStringNotEqual((sCInterface.stringA== null?"B" !=null : !sCInterface.stringA.equals("B")));
		
		enterSequence_main_region_VarToConstCompareSucceeded_default();
	}
	
	private void effect_main_region__choice_5_tr0() {
		react_main_region__choice_6();
	}
	
	private void effect_main_region__choice_6_tr0() {
		enterSequence_main_region_Failed_default();
	}
	
	private void effect_main_region__choice_7_tr0() {
		sCInterface.setStringVarNotEqual(("A"== null?sCInterface.stringB !=null : !"A".equals(sCInterface.stringB)));
		
		react_main_region__choice_8();
	}
	
	private void effect_main_region__choice_7_tr1() {
		react_main_region__choice_10();
	}
	
	private void effect_main_region__choice_8_tr1() {
		sCInterface.setGuardStringEqual(("A"== null?sCInterface.stringA ==null :"A".equals(sCInterface.stringA)));
		
		react_main_region__choice_9();
	}
	
	private void effect_main_region__choice_8_tr0() {
		react_main_region__choice_10();
	}
	
	private void effect_main_region__choice_9_tr1() {
		sCInterface.setGuardStringNotEqual(("A"== null?sCInterface.stringB !=null : !"A".equals(sCInterface.stringB)));
		
		enterSequence_main_region_ConstToVarCompareSucceeded_default();
	}
	
	private void effect_main_region__choice_9_tr0() {
		react_main_region__choice_10();
	}
	
	private void effect_main_region__choice_10_tr0() {
		react_main_region__choice_6();
	}
	
	private void effect_main_region__choice_11_tr0() {
		sCInterface.setStringVarNotEqual(("A"== null?"B" !=null : !"A".equals("B")));
		
		react_main_region__choice_12();
	}
	
	private void effect_main_region__choice_11_tr1() {
		react_main_region__choice_14();
	}
	
	private void effect_main_region__choice_12_tr1() {
		sCInterface.setGuardStringEqual(("A"== null?"A" ==null :"A".equals("A")));
		
		react_main_region__choice_13();
	}
	
	private void effect_main_region__choice_12_tr0() {
		react_main_region__choice_14();
	}
	
	private void effect_main_region__choice_13_tr1() {
		sCInterface.setGuardStringNotEqual(("A"== null?"B" !=null : !"A".equals("B")));
		
		enterSequence_main_region_ConstToConstCompareSucceeded_default();
	}
	
	private void effect_main_region__choice_13_tr0() {
		react_main_region__choice_14();
	}
	
	private void effect_main_region__choice_14_tr0() {
		react_main_region__choice_10();
	}
	
	/* Entry action for state 'AssignmentChecked'. */
	private void entryAction_main_region_AssignmentChecked() {
		sCInterface.setStringVarNotEqual( !(sCInterface.stringA== null?sCInterface.stringB ==null :sCInterface.stringA.equals(sCInterface.stringB)));
		
		sCInterface.setStringVarEqual( !(sCInterface.stringA== null?sCInterface.stringA2 !=null : !sCInterface.stringA.equals(sCInterface.stringA2)));
	}
	
	/* 'default' enter sequence for state AssignmentChecked */
	private void enterSequence_main_region_AssignmentChecked_default() {
		entryAction_main_region_AssignmentChecked();
		nextStateIndex = 0;
		stateVector[0] = State.main_region_AssignmentChecked;
	}
	
	/* 'default' enter sequence for state Failed */
	private void enterSequence_main_region_Failed_default() {
		nextStateIndex = 0;
		stateVector[0] = State.main_region_Failed;
	}
	
	/* 'default' enter sequence for state VarToVarCompareSucceeded */
	private void enterSequence_main_region_VarToVarCompareSucceeded_default() {
		nextStateIndex = 0;
		stateVector[0] = State.main_region_VarToVarCompareSucceeded;
	}
	
	/* 'default' enter sequence for state VarToConstCompareSucceeded */
	private void enterSequence_main_region_VarToConstCompareSucceeded_default() {
		nextStateIndex = 0;
		stateVector[0] = State.main_region_VarToConstCompareSucceeded;
	}
	
	/* 'default' enter sequence for state ConstToVarCompareSucceeded */
	private void enterSequence_main_region_ConstToVarCompareSucceeded_default() {
		nextStateIndex = 0;
		stateVector[0] = State.main_region_ConstToVarCompareSucceeded;
	}
	
	/* 'default' enter sequence for state ConstToConstCompareSucceeded */
	private void enterSequence_main_region_ConstToConstCompareSucceeded_default() {
		nextStateIndex = 0;
		stateVector[0] = State.main_region_ConstToConstCompareSucceeded;
	}
	
	/* 'default' enter sequence for region main region */
	private void enterSequence_main_region_default() {
		react_main_region__entry_Default();
	}
	
	/* Default exit sequence for state AssignmentChecked */
	private void exitSequence_main_region_AssignmentChecked() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state Failed */
	private void exitSequence_main_region_Failed() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state VarToVarCompareSucceeded */
	private void exitSequence_main_region_VarToVarCompareSucceeded() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state VarToConstCompareSucceeded */
	private void exitSequence_main_region_VarToConstCompareSucceeded() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state ConstToVarCompareSucceeded */
	private void exitSequence_main_region_ConstToVarCompareSucceeded() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state ConstToConstCompareSucceeded */
	private void exitSequence_main_region_ConstToConstCompareSucceeded() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for region main region */
	private void exitSequence_main_region() {
		switch (stateVector[0]) {
		case main_region_AssignmentChecked:
			exitSequence_main_region_AssignmentChecked();
			break;
		case main_region_Failed:
			exitSequence_main_region_Failed();
			break;
		case main_region_VarToVarCompareSucceeded:
			exitSequence_main_region_VarToVarCompareSucceeded();
			break;
		case main_region_VarToConstCompareSucceeded:
			exitSequence_main_region_VarToConstCompareSucceeded();
			break;
		case main_region_ConstToVarCompareSucceeded:
			exitSequence_main_region_ConstToVarCompareSucceeded();
			break;
		case main_region_ConstToConstCompareSucceeded:
			exitSequence_main_region_ConstToConstCompareSucceeded();
			break;
		default:
			break;
		}
	}
	
	/* The reactions of state AssignmentChecked. */
	private void react_main_region_AssignmentChecked() {
		if (check_main_region_AssignmentChecked_tr0_tr0()) {
			effect_main_region_AssignmentChecked_tr0();
		}
	}
	
	/* The reactions of state Failed. */
	private void react_main_region_Failed() {
	}
	
	/* The reactions of state VarToVarCompareSucceeded. */
	private void react_main_region_VarToVarCompareSucceeded() {
		if (check_main_region_VarToVarCompareSucceeded_tr0_tr0()) {
			effect_main_region_VarToVarCompareSucceeded_tr0();
		}
	}
	
	/* The reactions of state VarToConstCompareSucceeded. */
	private void react_main_region_VarToConstCompareSucceeded() {
		if (check_main_region_VarToConstCompareSucceeded_tr0_tr0()) {
			effect_main_region_VarToConstCompareSucceeded_tr0();
		}
	}
	
	/* The reactions of state ConstToVarCompareSucceeded. */
	private void react_main_region_ConstToVarCompareSucceeded() {
		if (check_main_region_ConstToVarCompareSucceeded_tr0_tr0()) {
			effect_main_region_ConstToVarCompareSucceeded_tr0();
		}
	}
	
	/* The reactions of state ConstToConstCompareSucceeded. */
	private void react_main_region_ConstToConstCompareSucceeded() {
	}
	
	/* The reactions of state null. */
	private void react_main_region__choice_0() {
		if (check_main_region__choice_0_tr1_tr1()) {
			effect_main_region__choice_0_tr1();
		} else {
			effect_main_region__choice_0_tr0();
		}
	}
	
	/* The reactions of state null. */
	private void react_main_region__choice_1() {
		if (check_main_region__choice_1_tr1_tr1()) {
			effect_main_region__choice_1_tr1();
		} else {
			effect_main_region__choice_1_tr0();
		}
	}
	
	/* The reactions of state null. */
	private void react_main_region__choice_2() {
		if (check_main_region__choice_2_tr1_tr1()) {
			effect_main_region__choice_2_tr1();
		} else {
			effect_main_region__choice_2_tr0();
		}
	}
	
	/* The reactions of state null. */
	private void react_main_region__choice_3() {
		if (check_main_region__choice_3_tr0_tr0()) {
			effect_main_region__choice_3_tr0();
		} else {
			effect_main_region__choice_3_tr1();
		}
	}
	
	/* The reactions of state null. */
	private void react_main_region__choice_4() {
		if (check_main_region__choice_4_tr1_tr1()) {
			effect_main_region__choice_4_tr1();
		} else {
			effect_main_region__choice_4_tr0();
		}
	}
	
	/* The reactions of state null. */
	private void react_main_region__choice_5() {
		if (check_main_region__choice_5_tr1_tr1()) {
			effect_main_region__choice_5_tr1();
		} else {
			effect_main_region__choice_5_tr0();
		}
	}
	
	/* The reactions of state null. */
	private void react_main_region__choice_6() {
		effect_main_region__choice_6_tr0();
	}
	
	/* The reactions of state null. */
	private void react_main_region__choice_7() {
		if (check_main_region__choice_7_tr0_tr0()) {
			effect_main_region__choice_7_tr0();
		} else {
			effect_main_region__choice_7_tr1();
		}
	}
	
	/* The reactions of state null. */
	private void react_main_region__choice_8() {
		if (check_main_region__choice_8_tr1_tr1()) {
			effect_main_region__choice_8_tr1();
		} else {
			effect_main_region__choice_8_tr0();
		}
	}
	
	/* The reactions of state null. */
	private void react_main_region__choice_9() {
		if (check_main_region__choice_9_tr1_tr1()) {
			effect_main_region__choice_9_tr1();
		} else {
			effect_main_region__choice_9_tr0();
		}
	}
	
	/* The reactions of state null. */
	private void react_main_region__choice_10() {
		effect_main_region__choice_10_tr0();
	}
	
	/* The reactions of state null. */
	private void react_main_region__choice_11() {
		if (check_main_region__choice_11_tr0_tr0()) {
			effect_main_region__choice_11_tr0();
		} else {
			effect_main_region__choice_11_tr1();
		}
	}
	
	/* The reactions of state null. */
	private void react_main_region__choice_12() {
		if (check_main_region__choice_12_tr1_tr1()) {
			effect_main_region__choice_12_tr1();
		} else {
			effect_main_region__choice_12_tr0();
		}
	}
	
	/* The reactions of state null. */
	private void react_main_region__choice_13() {
		if (check_main_region__choice_13_tr1_tr1()) {
			effect_main_region__choice_13_tr1();
		} else {
			effect_main_region__choice_13_tr0();
		}
	}
	
	/* The reactions of state null. */
	private void react_main_region__choice_14() {
		effect_main_region__choice_14_tr0();
	}
	
	/* Default react sequence for initial entry  */
	private void react_main_region__entry_Default() {
		enterSequence_main_region_AssignmentChecked_default();
	}
	
	public void runCycle() {
		if (!initialized)
			throw new IllegalStateException(
					"The state machine needs to be initialized first by calling the init() function.");
		clearOutEvents();
		for (nextStateIndex = 0; nextStateIndex < stateVector.length; nextStateIndex++) {
			switch (stateVector[nextStateIndex]) {
			case main_region_AssignmentChecked:
				react_main_region_AssignmentChecked();
				break;
			case main_region_Failed:
				react_main_region_Failed();
				break;
			case main_region_VarToVarCompareSucceeded:
				react_main_region_VarToVarCompareSucceeded();
				break;
			case main_region_VarToConstCompareSucceeded:
				react_main_region_VarToConstCompareSucceeded();
				break;
			case main_region_ConstToVarCompareSucceeded:
				react_main_region_ConstToVarCompareSucceeded();
				break;
			case main_region_ConstToConstCompareSucceeded:
				react_main_region_ConstToConstCompareSucceeded();
				break;
			default:
				// $NullState$
			}
		}
		clearEvents();
	}
}
