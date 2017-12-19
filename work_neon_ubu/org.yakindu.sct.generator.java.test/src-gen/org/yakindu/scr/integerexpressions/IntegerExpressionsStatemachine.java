package org.yakindu.scr.integerexpressions;

public class IntegerExpressionsStatemachine implements IIntegerExpressionsStatemachine {

	protected class SCInterfaceImpl implements SCInterface {
	
		private boolean e1;
		
		public void raiseE1() {
			e1 = true;
		}
		
		private long myInt1;
		
		public long getMyInt1() {
			return myInt1;
		}
		
		public void setMyInt1(long value) {
			this.myInt1 = value;
		}
		
		private long myInt2;
		
		public long getMyInt2() {
			return myInt2;
		}
		
		public void setMyInt2(long value) {
			this.myInt2 = value;
		}
		
		private boolean less;
		
		public boolean getLess() {
			return less;
		}
		
		public void setLess(boolean value) {
			this.less = value;
		}
		
		private boolean greater;
		
		public boolean getGreater() {
			return greater;
		}
		
		public void setGreater(boolean value) {
			this.greater = value;
		}
		
		private boolean equalOrLess;
		
		public boolean getEqualOrLess() {
			return equalOrLess;
		}
		
		public void setEqualOrLess(boolean value) {
			this.equalOrLess = value;
		}
		
		private boolean equalOrGreater;
		
		public boolean getEqualOrGreater() {
			return equalOrGreater;
		}
		
		public void setEqualOrGreater(boolean value) {
			this.equalOrGreater = value;
		}
		
		private boolean equal;
		
		public boolean getEqual() {
			return equal;
		}
		
		public void setEqual(boolean value) {
			this.equal = value;
		}
		
		private boolean notEqual;
		
		public boolean getNotEqual() {
			return notEqual;
		}
		
		public void setNotEqual(boolean value) {
			this.notEqual = value;
		}
		
		private long plus;
		
		public long getPlus() {
			return plus;
		}
		
		public void setPlus(long value) {
			this.plus = value;
		}
		
		private long minus;
		
		public long getMinus() {
			return minus;
		}
		
		public void setMinus(long value) {
			this.minus = value;
		}
		
		private long multiply;
		
		public long getMultiply() {
			return multiply;
		}
		
		public void setMultiply(long value) {
			this.multiply = value;
		}
		
		private long division;
		
		public long getDivision() {
			return division;
		}
		
		public void setDivision(long value) {
			this.division = value;
		}
		
		private long modulo;
		
		public long getModulo() {
			return modulo;
		}
		
		public void setModulo(long value) {
			this.modulo = value;
		}
		
		private long negat;
		
		public long getNegat() {
			return negat;
		}
		
		public void setNegat(long value) {
			this.negat = value;
		}
		
		private boolean complement;
		
		public boolean getComplement() {
			return complement;
		}
		
		public void setComplement(boolean value) {
			this.complement = value;
		}
		
		private long multiAssign;
		
		public long getMultiAssign() {
			return multiAssign;
		}
		
		public void setMultiAssign(long value) {
			this.multiAssign = value;
		}
		
		private long divAssign;
		
		public long getDivAssign() {
			return divAssign;
		}
		
		public void setDivAssign(long value) {
			this.divAssign = value;
		}
		
		private long plusAssign;
		
		public long getPlusAssign() {
			return plusAssign;
		}
		
		public void setPlusAssign(long value) {
			this.plusAssign = value;
		}
		
		private long minusAssign;
		
		public long getMinusAssign() {
			return minusAssign;
		}
		
		public void setMinusAssign(long value) {
			this.minusAssign = value;
		}
		
		private long moduloAssign;
		
		public long getModuloAssign() {
			return moduloAssign;
		}
		
		public void setModuloAssign(long value) {
			this.moduloAssign = value;
		}
		
		protected void clearEvents() {
			e1 = false;
		}
	}
	
	protected SCInterfaceImpl sCInterface;
	
	private boolean initialized = false;
	
	public enum State {
		main_region_StateA,
		main_region_StateB,
		$NullState$
	};
	
	private final State[] stateVector = new State[1];
	
	private int nextStateIndex;
	
	public IntegerExpressionsStatemachine() {
		sCInterface = new SCInterfaceImpl();
	}
	
	public void init() {
		this.initialized = true;
		for (int i = 0; i < 1; i++) {
			stateVector[i] = State.$NullState$;
		}
		clearEvents();
		clearOutEvents();
		sCInterface.setMyInt1(0);
		
		sCInterface.setMyInt2(0);
		
		sCInterface.setLess(false);
		
		sCInterface.setGreater(false);
		
		sCInterface.setEqualOrLess(false);
		
		sCInterface.setEqualOrGreater(false);
		
		sCInterface.setEqual(false);
		
		sCInterface.setNotEqual(false);
		
		sCInterface.setPlus(0);
		
		sCInterface.setMinus(0);
		
		sCInterface.setMultiply(0);
		
		sCInterface.setDivision(0);
		
		sCInterface.setModulo(0);
		
		sCInterface.setNegat(0);
		
		sCInterface.setComplement(false);
		
		sCInterface.setMultiAssign(2);
		
		sCInterface.setDivAssign(20);
		
		sCInterface.setPlusAssign(2);
		
		sCInterface.setMinusAssign(2);
		
		sCInterface.setModuloAssign(20);
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
		case main_region_StateA:
			return stateVector[0] == State.main_region_StateA;
		case main_region_StateB:
			return stateVector[0] == State.main_region_StateB;
		default:
			return false;
		}
	}
	
	public SCInterface getSCInterface() {
		return sCInterface;
	}
	
	public void raiseE1() {
		sCInterface.raiseE1();
	}
	
	public long getMyInt1() {
		return sCInterface.getMyInt1();
	}
	
	public void setMyInt1(long value) {
		sCInterface.setMyInt1(value);
	}
	
	public long getMyInt2() {
		return sCInterface.getMyInt2();
	}
	
	public void setMyInt2(long value) {
		sCInterface.setMyInt2(value);
	}
	
	public boolean getLess() {
		return sCInterface.getLess();
	}
	
	public void setLess(boolean value) {
		sCInterface.setLess(value);
	}
	
	public boolean getGreater() {
		return sCInterface.getGreater();
	}
	
	public void setGreater(boolean value) {
		sCInterface.setGreater(value);
	}
	
	public boolean getEqualOrLess() {
		return sCInterface.getEqualOrLess();
	}
	
	public void setEqualOrLess(boolean value) {
		sCInterface.setEqualOrLess(value);
	}
	
	public boolean getEqualOrGreater() {
		return sCInterface.getEqualOrGreater();
	}
	
	public void setEqualOrGreater(boolean value) {
		sCInterface.setEqualOrGreater(value);
	}
	
	public boolean getEqual() {
		return sCInterface.getEqual();
	}
	
	public void setEqual(boolean value) {
		sCInterface.setEqual(value);
	}
	
	public boolean getNotEqual() {
		return sCInterface.getNotEqual();
	}
	
	public void setNotEqual(boolean value) {
		sCInterface.setNotEqual(value);
	}
	
	public long getPlus() {
		return sCInterface.getPlus();
	}
	
	public void setPlus(long value) {
		sCInterface.setPlus(value);
	}
	
	public long getMinus() {
		return sCInterface.getMinus();
	}
	
	public void setMinus(long value) {
		sCInterface.setMinus(value);
	}
	
	public long getMultiply() {
		return sCInterface.getMultiply();
	}
	
	public void setMultiply(long value) {
		sCInterface.setMultiply(value);
	}
	
	public long getDivision() {
		return sCInterface.getDivision();
	}
	
	public void setDivision(long value) {
		sCInterface.setDivision(value);
	}
	
	public long getModulo() {
		return sCInterface.getModulo();
	}
	
	public void setModulo(long value) {
		sCInterface.setModulo(value);
	}
	
	public long getNegat() {
		return sCInterface.getNegat();
	}
	
	public void setNegat(long value) {
		sCInterface.setNegat(value);
	}
	
	public boolean getComplement() {
		return sCInterface.getComplement();
	}
	
	public void setComplement(boolean value) {
		sCInterface.setComplement(value);
	}
	
	public long getMultiAssign() {
		return sCInterface.getMultiAssign();
	}
	
	public void setMultiAssign(long value) {
		sCInterface.setMultiAssign(value);
	}
	
	public long getDivAssign() {
		return sCInterface.getDivAssign();
	}
	
	public void setDivAssign(long value) {
		sCInterface.setDivAssign(value);
	}
	
	public long getPlusAssign() {
		return sCInterface.getPlusAssign();
	}
	
	public void setPlusAssign(long value) {
		sCInterface.setPlusAssign(value);
	}
	
	public long getMinusAssign() {
		return sCInterface.getMinusAssign();
	}
	
	public void setMinusAssign(long value) {
		sCInterface.setMinusAssign(value);
	}
	
	public long getModuloAssign() {
		return sCInterface.getModuloAssign();
	}
	
	public void setModuloAssign(long value) {
		sCInterface.setModuloAssign(value);
	}
	
	private boolean check_main_region_StateA_tr0_tr0() {
		return sCInterface.e1;
	}
	
	private void effect_main_region_StateA_tr0() {
		exitSequence_main_region_StateA();
		enterSequence_main_region_StateB_default();
	}
	
	/* Entry action for state 'StateA'. */
	private void entryAction_main_region_StateA() {
		sCInterface.setMyInt1(10);
		
		sCInterface.setMyInt2(5);
	}
	
	/* Entry action for state 'StateB'. */
	private void entryAction_main_region_StateB() {
		sCInterface.setLess((sCInterface.myInt1<sCInterface.myInt2));
		
		sCInterface.setGreater((sCInterface.myInt1>sCInterface.myInt2));
		
		sCInterface.setEqualOrLess((sCInterface.myInt1<=sCInterface.myInt2));
		
		sCInterface.setEqualOrGreater((sCInterface.myInt1>=sCInterface.myInt2));
		
		sCInterface.setEqual((sCInterface.myInt1==sCInterface.myInt2));
		
		sCInterface.setNotEqual((sCInterface.myInt1!=sCInterface.myInt2));
		
		sCInterface.setPlus(sCInterface.myInt1+sCInterface.myInt2);
		
		sCInterface.setMinus(sCInterface.myInt1-sCInterface.myInt2);
		
		sCInterface.setMultiply(sCInterface.myInt1*sCInterface.myInt2);
		
		sCInterface.setDivision(sCInterface.myInt1/sCInterface.myInt2);
		
		sCInterface.setModulo(sCInterface.myInt1%sCInterface.myInt2);
		
		sCInterface.setNegat(-sCInterface.myInt1);
		
		sCInterface.setMultiAssign(sCInterface.getMultiAssign() * sCInterface.myInt1);
		
		sCInterface.setDivAssign(sCInterface.getDivAssign() / sCInterface.myInt1);
		
		sCInterface.setPlusAssign(sCInterface.getPlusAssign() + sCInterface.myInt1);
		
		sCInterface.setMinusAssign(sCInterface.getMinusAssign() - sCInterface.myInt1);
		
		sCInterface.setModuloAssign(sCInterface.getModuloAssign() % sCInterface.myInt1);
	}
	
	/* 'default' enter sequence for state StateA */
	private void enterSequence_main_region_StateA_default() {
		entryAction_main_region_StateA();
		nextStateIndex = 0;
		stateVector[0] = State.main_region_StateA;
	}
	
	/* 'default' enter sequence for state StateB */
	private void enterSequence_main_region_StateB_default() {
		entryAction_main_region_StateB();
		nextStateIndex = 0;
		stateVector[0] = State.main_region_StateB;
	}
	
	/* 'default' enter sequence for region main region */
	private void enterSequence_main_region_default() {
		react_main_region__entry_Default();
	}
	
	/* Default exit sequence for state StateA */
	private void exitSequence_main_region_StateA() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state StateB */
	private void exitSequence_main_region_StateB() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for region main region */
	private void exitSequence_main_region() {
		switch (stateVector[0]) {
		case main_region_StateA:
			exitSequence_main_region_StateA();
			break;
		case main_region_StateB:
			exitSequence_main_region_StateB();
			break;
		default:
			break;
		}
	}
	
	/* The reactions of state StateA. */
	private void react_main_region_StateA() {
		if (check_main_region_StateA_tr0_tr0()) {
			effect_main_region_StateA_tr0();
		}
	}
	
	/* The reactions of state StateB. */
	private void react_main_region_StateB() {
	}
	
	/* Default react sequence for initial entry  */
	private void react_main_region__entry_Default() {
		enterSequence_main_region_StateA_default();
	}
	
	public void runCycle() {
		if (!initialized)
			throw new IllegalStateException(
					"The state machine needs to be initialized first by calling the init() function.");
		clearOutEvents();
		for (nextStateIndex = 0; nextStateIndex < stateVector.length; nextStateIndex++) {
			switch (stateVector[nextStateIndex]) {
			case main_region_StateA:
				react_main_region_StateA();
				break;
			case main_region_StateB:
				react_main_region_StateB();
				break;
			default:
				// $NullState$
			}
		}
		clearEvents();
	}
}
