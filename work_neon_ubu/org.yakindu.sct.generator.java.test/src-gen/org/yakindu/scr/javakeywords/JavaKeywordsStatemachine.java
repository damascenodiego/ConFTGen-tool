package org.yakindu.scr.javakeywords;

public class JavaKeywordsStatemachine implements IJavaKeywordsStatemachine {

	protected class SCInterfaceImpl implements SCInterface {
	
		private boolean whileEvent;
		
		public void raiseWhile() {
			whileEvent = true;
		}
		
		private boolean ev;
		
		public void raiseEv() {
			ev = true;
		}
		
		private boolean abstractVariable;
		
		public boolean getAbstract() {
			return abstractVariable;
		}
		
		public void setAbstract(boolean value) {
			this.abstractVariable = value;
		}
		
		private boolean assertVariable;
		
		public boolean getAssert() {
			return assertVariable;
		}
		
		public void setAssert(boolean value) {
			this.assertVariable = value;
		}
		
		private boolean booleanVariable;
		
		public boolean getBoolean() {
			return booleanVariable;
		}
		
		public void setBoolean(boolean value) {
			this.booleanVariable = value;
		}
		
		private boolean breakVariable;
		
		public boolean getBreak() {
			return breakVariable;
		}
		
		public void setBreak(boolean value) {
			this.breakVariable = value;
		}
		
		private boolean byteVariable;
		
		public boolean getByte() {
			return byteVariable;
		}
		
		public void setByte(boolean value) {
			this.byteVariable = value;
		}
		
		private boolean caseVariable;
		
		public boolean getCase() {
			return caseVariable;
		}
		
		public void setCase(boolean value) {
			this.caseVariable = value;
		}
		
		private boolean catchVariable;
		
		public boolean getCatch() {
			return catchVariable;
		}
		
		public void setCatch(boolean value) {
			this.catchVariable = value;
		}
		
		private boolean charVariable;
		
		public boolean getChar() {
			return charVariable;
		}
		
		public void setChar(boolean value) {
			this.charVariable = value;
		}
		
		private boolean classVariable;
		
		public boolean getClassVariable() {
			return classVariable;
		}
		
		public void setClass(boolean value) {
			this.classVariable = value;
		}
		
		private boolean continueVariable;
		
		public boolean getContinue() {
			return continueVariable;
		}
		
		public void setContinue(boolean value) {
			this.continueVariable = value;
		}
		
		private boolean doVariable;
		
		public boolean getDo() {
			return doVariable;
		}
		
		public void setDo(boolean value) {
			this.doVariable = value;
		}
		
		private boolean doubleVariable;
		
		public boolean getDouble() {
			return doubleVariable;
		}
		
		public void setDouble(boolean value) {
			this.doubleVariable = value;
		}
		
		private boolean enumVariable;
		
		public boolean getEnum() {
			return enumVariable;
		}
		
		public void setEnum(boolean value) {
			this.enumVariable = value;
		}
		
		private boolean extendsVariable;
		
		public boolean getExtends() {
			return extendsVariable;
		}
		
		public void setExtends(boolean value) {
			this.extendsVariable = value;
		}
		
		private boolean finalVariable;
		
		public boolean getFinal() {
			return finalVariable;
		}
		
		public void setFinal(boolean value) {
			this.finalVariable = value;
		}
		
		private boolean finallyVariable;
		
		public boolean getFinally() {
			return finallyVariable;
		}
		
		public void setFinally(boolean value) {
			this.finallyVariable = value;
		}
		
		private boolean floatVariable;
		
		public boolean getFloat() {
			return floatVariable;
		}
		
		public void setFloat(boolean value) {
			this.floatVariable = value;
		}
		
		private boolean forVariable;
		
		public boolean getFor() {
			return forVariable;
		}
		
		public void setFor(boolean value) {
			this.forVariable = value;
		}
		
		private boolean gotoVariable;
		
		public boolean getGoto() {
			return gotoVariable;
		}
		
		public void setGoto(boolean value) {
			this.gotoVariable = value;
		}
		
		private boolean ifVariable;
		
		public boolean getIf() {
			return ifVariable;
		}
		
		public void setIf(boolean value) {
			this.ifVariable = value;
		}
		
		private boolean implementsVariable;
		
		public boolean getImplements() {
			return implementsVariable;
		}
		
		public void setImplements(boolean value) {
			this.implementsVariable = value;
		}
		
		private boolean instanceofVariable;
		
		public boolean getInstanceof() {
			return instanceofVariable;
		}
		
		public void setInstanceof(boolean value) {
			this.instanceofVariable = value;
		}
		
		private boolean intVariable;
		
		public boolean getInt() {
			return intVariable;
		}
		
		public void setInt(boolean value) {
			this.intVariable = value;
		}
		
		private boolean longVariable;
		
		public boolean getLong() {
			return longVariable;
		}
		
		public void setLong(boolean value) {
			this.longVariable = value;
		}
		
		private boolean nativeVariable;
		
		public boolean getNative() {
			return nativeVariable;
		}
		
		public void setNative(boolean value) {
			this.nativeVariable = value;
		}
		
		private boolean newVariable;
		
		public boolean getNew() {
			return newVariable;
		}
		
		public void setNew(boolean value) {
			this.newVariable = value;
		}
		
		private boolean packageVariable;
		
		public boolean getPackage() {
			return packageVariable;
		}
		
		public void setPackage(boolean value) {
			this.packageVariable = value;
		}
		
		private boolean privateVariable;
		
		public boolean getPrivate() {
			return privateVariable;
		}
		
		public void setPrivate(boolean value) {
			this.privateVariable = value;
		}
		
		private boolean protectedVariable;
		
		public boolean getProtected() {
			return protectedVariable;
		}
		
		public void setProtected(boolean value) {
			this.protectedVariable = value;
		}
		
		private boolean publicVariable;
		
		public boolean getPublic() {
			return publicVariable;
		}
		
		public void setPublic(boolean value) {
			this.publicVariable = value;
		}
		
		private boolean returnVariable;
		
		public boolean getReturn() {
			return returnVariable;
		}
		
		public void setReturn(boolean value) {
			this.returnVariable = value;
		}
		
		private boolean shortVariable;
		
		public boolean getShort() {
			return shortVariable;
		}
		
		public void setShort(boolean value) {
			this.shortVariable = value;
		}
		
		private boolean staticVariable;
		
		public boolean getStatic() {
			return staticVariable;
		}
		
		public void setStatic(boolean value) {
			this.staticVariable = value;
		}
		
		private boolean strictfpVariable;
		
		public boolean getStrictfp() {
			return strictfpVariable;
		}
		
		public void setStrictfp(boolean value) {
			this.strictfpVariable = value;
		}
		
		private boolean superVariable;
		
		public boolean getSuper() {
			return superVariable;
		}
		
		public void setSuper(boolean value) {
			this.superVariable = value;
		}
		
		private boolean switchVariable;
		
		public boolean getSwitch() {
			return switchVariable;
		}
		
		public void setSwitch(boolean value) {
			this.switchVariable = value;
		}
		
		private boolean synchronizedVariable;
		
		public boolean getSynchronized() {
			return synchronizedVariable;
		}
		
		public void setSynchronized(boolean value) {
			this.synchronizedVariable = value;
		}
		
		private boolean thisVariable;
		
		public boolean getThis() {
			return thisVariable;
		}
		
		public void setThis(boolean value) {
			this.thisVariable = value;
		}
		
		private boolean throwVariable;
		
		public boolean getThrow() {
			return throwVariable;
		}
		
		public void setThrow(boolean value) {
			this.throwVariable = value;
		}
		
		private boolean throwsVariable;
		
		public boolean getThrows() {
			return throwsVariable;
		}
		
		public void setThrows(boolean value) {
			this.throwsVariable = value;
		}
		
		private boolean transientVariable;
		
		public boolean getTransient() {
			return transientVariable;
		}
		
		public void setTransient(boolean value) {
			this.transientVariable = value;
		}
		
		private boolean tryVariable;
		
		public boolean getTry() {
			return tryVariable;
		}
		
		public void setTry(boolean value) {
			this.tryVariable = value;
		}
		
		private boolean voidVariable;
		
		public boolean getVoid() {
			return voidVariable;
		}
		
		public void setVoid(boolean value) {
			this.voidVariable = value;
		}
		
		private boolean volatileVariable;
		
		public boolean getVolatile() {
			return volatileVariable;
		}
		
		public void setVolatile(boolean value) {
			this.volatileVariable = value;
		}
		
		protected void clearEvents() {
			whileEvent = false;
			ev = false;
		}
	}
	
	protected SCInterfaceImpl sCInterface;
	
	private boolean initialized = false;
	
	public enum State {
		goto_abstract,
		goto_boolean,
		goto_void,
		goto_void_volatile_transient,
		goto_void_volatile_transient_throw_false,
		goto_void_volatile_state,
		$NullState$
	};
	
	private State[] historyVector = new State[2];
	private final State[] stateVector = new State[1];
	
	private int nextStateIndex;
	
	public JavaKeywordsStatemachine() {
		sCInterface = new SCInterfaceImpl();
	}
	
	public void init() {
		this.initialized = true;
		for (int i = 0; i < 1; i++) {
			stateVector[i] = State.$NullState$;
		}
		for (int i = 0; i < 2; i++) {
			historyVector[i] = State.$NullState$;
		}
		clearEvents();
		clearOutEvents();
		sCInterface.setAbstract(false);
		
		sCInterface.setAssert(false);
		
		sCInterface.setBoolean(false);
		
		sCInterface.setBreak(false);
		
		sCInterface.setByte(false);
		
		sCInterface.setCase(false);
		
		sCInterface.setCatch(false);
		
		sCInterface.setChar(false);
		
		sCInterface.setClass(false);
		
		sCInterface.setContinue(false);
		
		sCInterface.setDo(false);
		
		sCInterface.setDouble(false);
		
		sCInterface.setEnum(false);
		
		sCInterface.setExtends(false);
		
		sCInterface.setFinal(false);
		
		sCInterface.setFinally(false);
		
		sCInterface.setFloat(false);
		
		sCInterface.setFor(false);
		
		sCInterface.setGoto(false);
		
		sCInterface.setIf(false);
		
		sCInterface.setImplements(false);
		
		sCInterface.setInstanceof(false);
		
		sCInterface.setInt(false);
		
		sCInterface.setLong(false);
		
		sCInterface.setNative(false);
		
		sCInterface.setNew(false);
		
		sCInterface.setPackage(false);
		
		sCInterface.setPrivate(false);
		
		sCInterface.setProtected(false);
		
		sCInterface.setPublic(false);
		
		sCInterface.setReturn(false);
		
		sCInterface.setShort(false);
		
		sCInterface.setStatic(false);
		
		sCInterface.setStrictfp(false);
		
		sCInterface.setSuper(false);
		
		sCInterface.setSwitch(false);
		
		sCInterface.setSynchronized(false);
		
		sCInterface.setThis(false);
		
		sCInterface.setThrow(false);
		
		sCInterface.setThrows(false);
		
		sCInterface.setTransient(false);
		
		sCInterface.setTry(false);
		
		sCInterface.setVoid(false);
		
		sCInterface.setVolatile(false);
	}
	
	public void enter() {
		if (!initialized) {
			throw new IllegalStateException(
					"The state machine needs to be initialized first by calling the init() function.");
		}
		enterSequence_goto_default();
	}
	
	public void exit() {
		exitSequence_goto();
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
		case goto_abstract:
			return stateVector[0] == State.goto_abstract;
		case goto_boolean:
			return stateVector[0] == State.goto_boolean;
		case goto_void:
			return stateVector[0].ordinal() >= State.
					goto_void.ordinal()&& stateVector[0].ordinal() <= State.goto_void_volatile_state.ordinal();
		case goto_void_volatile_transient:
			return stateVector[0].ordinal() >= State.
					goto_void_volatile_transient.ordinal()&& stateVector[0].ordinal() <= State.goto_void_volatile_transient_throw_false.ordinal();
		case goto_void_volatile_transient_throw_false:
			return stateVector[0] == State.goto_void_volatile_transient_throw_false;
		case goto_void_volatile_state:
			return stateVector[0] == State.goto_void_volatile_state;
		default:
			return false;
		}
	}
	
	public SCInterface getSCInterface() {
		return sCInterface;
	}
	
	public void raiseWhile() {
		sCInterface.raiseWhile();
	}
	
	public void raiseEv() {
		sCInterface.raiseEv();
	}
	
	public boolean getAbstract() {
		return sCInterface.getAbstract();
	}
	
	public void setAbstract(boolean value) {
		sCInterface.setAbstract(value);
	}
	
	public boolean getAssert() {
		return sCInterface.getAssert();
	}
	
	public void setAssert(boolean value) {
		sCInterface.setAssert(value);
	}
	
	public boolean getBoolean() {
		return sCInterface.getBoolean();
	}
	
	public void setBoolean(boolean value) {
		sCInterface.setBoolean(value);
	}
	
	public boolean getBreak() {
		return sCInterface.getBreak();
	}
	
	public void setBreak(boolean value) {
		sCInterface.setBreak(value);
	}
	
	public boolean getByte() {
		return sCInterface.getByte();
	}
	
	public void setByte(boolean value) {
		sCInterface.setByte(value);
	}
	
	public boolean getCase() {
		return sCInterface.getCase();
	}
	
	public void setCase(boolean value) {
		sCInterface.setCase(value);
	}
	
	public boolean getCatch() {
		return sCInterface.getCatch();
	}
	
	public void setCatch(boolean value) {
		sCInterface.setCatch(value);
	}
	
	public boolean getChar() {
		return sCInterface.getChar();
	}
	
	public void setChar(boolean value) {
		sCInterface.setChar(value);
	}
	
	public boolean getClassVariable() {
		return sCInterface.getClassVariable();
	}
	
	public void setClass(boolean value) {
		sCInterface.setClass(value);
	}
	
	public boolean getContinue() {
		return sCInterface.getContinue();
	}
	
	public void setContinue(boolean value) {
		sCInterface.setContinue(value);
	}
	
	public boolean getDo() {
		return sCInterface.getDo();
	}
	
	public void setDo(boolean value) {
		sCInterface.setDo(value);
	}
	
	public boolean getDouble() {
		return sCInterface.getDouble();
	}
	
	public void setDouble(boolean value) {
		sCInterface.setDouble(value);
	}
	
	public boolean getEnum() {
		return sCInterface.getEnum();
	}
	
	public void setEnum(boolean value) {
		sCInterface.setEnum(value);
	}
	
	public boolean getExtends() {
		return sCInterface.getExtends();
	}
	
	public void setExtends(boolean value) {
		sCInterface.setExtends(value);
	}
	
	public boolean getFinal() {
		return sCInterface.getFinal();
	}
	
	public void setFinal(boolean value) {
		sCInterface.setFinal(value);
	}
	
	public boolean getFinally() {
		return sCInterface.getFinally();
	}
	
	public void setFinally(boolean value) {
		sCInterface.setFinally(value);
	}
	
	public boolean getFloat() {
		return sCInterface.getFloat();
	}
	
	public void setFloat(boolean value) {
		sCInterface.setFloat(value);
	}
	
	public boolean getFor() {
		return sCInterface.getFor();
	}
	
	public void setFor(boolean value) {
		sCInterface.setFor(value);
	}
	
	public boolean getGoto() {
		return sCInterface.getGoto();
	}
	
	public void setGoto(boolean value) {
		sCInterface.setGoto(value);
	}
	
	public boolean getIf() {
		return sCInterface.getIf();
	}
	
	public void setIf(boolean value) {
		sCInterface.setIf(value);
	}
	
	public boolean getImplements() {
		return sCInterface.getImplements();
	}
	
	public void setImplements(boolean value) {
		sCInterface.setImplements(value);
	}
	
	public boolean getInstanceof() {
		return sCInterface.getInstanceof();
	}
	
	public void setInstanceof(boolean value) {
		sCInterface.setInstanceof(value);
	}
	
	public boolean getInt() {
		return sCInterface.getInt();
	}
	
	public void setInt(boolean value) {
		sCInterface.setInt(value);
	}
	
	public boolean getLong() {
		return sCInterface.getLong();
	}
	
	public void setLong(boolean value) {
		sCInterface.setLong(value);
	}
	
	public boolean getNative() {
		return sCInterface.getNative();
	}
	
	public void setNative(boolean value) {
		sCInterface.setNative(value);
	}
	
	public boolean getNew() {
		return sCInterface.getNew();
	}
	
	public void setNew(boolean value) {
		sCInterface.setNew(value);
	}
	
	public boolean getPackage() {
		return sCInterface.getPackage();
	}
	
	public void setPackage(boolean value) {
		sCInterface.setPackage(value);
	}
	
	public boolean getPrivate() {
		return sCInterface.getPrivate();
	}
	
	public void setPrivate(boolean value) {
		sCInterface.setPrivate(value);
	}
	
	public boolean getProtected() {
		return sCInterface.getProtected();
	}
	
	public void setProtected(boolean value) {
		sCInterface.setProtected(value);
	}
	
	public boolean getPublic() {
		return sCInterface.getPublic();
	}
	
	public void setPublic(boolean value) {
		sCInterface.setPublic(value);
	}
	
	public boolean getReturn() {
		return sCInterface.getReturn();
	}
	
	public void setReturn(boolean value) {
		sCInterface.setReturn(value);
	}
	
	public boolean getShort() {
		return sCInterface.getShort();
	}
	
	public void setShort(boolean value) {
		sCInterface.setShort(value);
	}
	
	public boolean getStatic() {
		return sCInterface.getStatic();
	}
	
	public void setStatic(boolean value) {
		sCInterface.setStatic(value);
	}
	
	public boolean getStrictfp() {
		return sCInterface.getStrictfp();
	}
	
	public void setStrictfp(boolean value) {
		sCInterface.setStrictfp(value);
	}
	
	public boolean getSuper() {
		return sCInterface.getSuper();
	}
	
	public void setSuper(boolean value) {
		sCInterface.setSuper(value);
	}
	
	public boolean getSwitch() {
		return sCInterface.getSwitch();
	}
	
	public void setSwitch(boolean value) {
		sCInterface.setSwitch(value);
	}
	
	public boolean getSynchronized() {
		return sCInterface.getSynchronized();
	}
	
	public void setSynchronized(boolean value) {
		sCInterface.setSynchronized(value);
	}
	
	public boolean getThis() {
		return sCInterface.getThis();
	}
	
	public void setThis(boolean value) {
		sCInterface.setThis(value);
	}
	
	public boolean getThrow() {
		return sCInterface.getThrow();
	}
	
	public void setThrow(boolean value) {
		sCInterface.setThrow(value);
	}
	
	public boolean getThrows() {
		return sCInterface.getThrows();
	}
	
	public void setThrows(boolean value) {
		sCInterface.setThrows(value);
	}
	
	public boolean getTransient() {
		return sCInterface.getTransient();
	}
	
	public void setTransient(boolean value) {
		sCInterface.setTransient(value);
	}
	
	public boolean getTry() {
		return sCInterface.getTry();
	}
	
	public void setTry(boolean value) {
		sCInterface.setTry(value);
	}
	
	public boolean getVoid() {
		return sCInterface.getVoid();
	}
	
	public void setVoid(boolean value) {
		sCInterface.setVoid(value);
	}
	
	public boolean getVolatile() {
		return sCInterface.getVolatile();
	}
	
	public void setVolatile(boolean value) {
		sCInterface.setVolatile(value);
	}
	
	private boolean check_goto_abstract_tr0_tr0() {
		return (sCInterface.whileEvent) && (true);
	}
	
	private boolean check_goto_boolean_tr0_tr0() {
		return sCInterface.whileEvent;
	}
	
	private boolean check_goto_boolean_tr1_tr1() {
		return sCInterface.ev;
	}
	
	private boolean check_goto_void_volatile_state_tr0_tr0() {
		return sCInterface.ev;
	}
	
	private void effect_goto_abstract_tr0() {
		exitSequence_goto_abstract();
		sCInterface.setNative(false);
		
		enterSequence_goto_boolean_default();
	}
	
	private void effect_goto_boolean_tr0() {
		exitSequence_goto_boolean();
		enterSequence_goto_void_default();
	}
	
	private void effect_goto_boolean_tr1() {
		exitSequence_goto_boolean();
		enterSequence_goto_void_try();
	}
	
	private void effect_goto_void_volatile_state_tr0() {
		exitSequence_goto_void_volatile_state();
		enterSequence_goto_void_volatile_transient_this();
	}
	
	/* Entry action for state 'abstract'. */
	private void entryAction_goto_abstract() {
		sCInterface.setAbstract(true);
		
		sCInterface.setAssert(true);
		
		sCInterface.setBoolean(true);
		
		sCInterface.setBreak(true);
		
		sCInterface.setByte(true);
		
		sCInterface.setCase(true);
		
		sCInterface.setCatch(true);
		
		sCInterface.setChar(true);
		
		sCInterface.setClass(true);
		
		sCInterface.setContinue(true);
		
		sCInterface.setDo(true);
		
		sCInterface.setDouble(true);
		
		sCInterface.setEnum(true);
		
		sCInterface.setExtends(true);
		
		sCInterface.setFinal(true);
		
		sCInterface.setFinally(true);
		
		sCInterface.setFloat(true);
		
		sCInterface.setFor(true);
		
		sCInterface.setGoto(true);
		
		sCInterface.setIf(true);
		
		sCInterface.setImplements(true);
		
		sCInterface.setInstanceof(true);
		
		sCInterface.setInt(true);
		
		sCInterface.setLong(true);
		
		sCInterface.setNative(true);
		
		sCInterface.setNew(true);
		
		sCInterface.setPackage(true);
		
		sCInterface.setPrivate(true);
		
		sCInterface.setProtected(true);
		
		sCInterface.setPublic(true);
		
		sCInterface.setReturn(true);
		
		sCInterface.setShort(true);
		
		sCInterface.setStatic(true);
		
		sCInterface.setStrictfp(true);
		
		sCInterface.setSuper(true);
		
		sCInterface.setSwitch(true);
		
		sCInterface.setSynchronized(true);
		
		sCInterface.setThis(true);
		
		sCInterface.setThrow(true);
		
		sCInterface.setThrows(true);
		
		sCInterface.setTransient(true);
		
		sCInterface.setTry(true);
		
		sCInterface.setVoid(true);
		
		sCInterface.setVolatile(true);
	}
	
	/* Entry action for state 'boolean'. */
	private void entryAction_goto_boolean() {
		sCInterface.setAbstract(false);
		
		sCInterface.setAssert(false);
		
		sCInterface.setBoolean(false);
		
		sCInterface.setBreak(false);
		
		sCInterface.setByte(false);
		
		sCInterface.setCase(false);
		
		sCInterface.setCatch(false);
		
		sCInterface.setChar(false);
		
		sCInterface.setClass(false);
		
		sCInterface.setContinue(false);
		
		sCInterface.setDo(false);
		
		sCInterface.setDouble(false);
		
		sCInterface.setEnum(false);
		
		sCInterface.setExtends(false);
		
		sCInterface.setFinal(false);
		
		sCInterface.setFinally(false);
		
		sCInterface.setFloat(false);
		
		sCInterface.setFor(false);
		
		sCInterface.setGoto(false);
		
		sCInterface.setIf(false);
		
		sCInterface.setImplements(false);
		
		sCInterface.setInstanceof(false);
		
		sCInterface.setInt(false);
		
		sCInterface.setLong(false);
		
		sCInterface.setNew(false);
		
		sCInterface.setPackage(false);
		
		sCInterface.setPrivate(false);
		
		sCInterface.setProtected(false);
		
		sCInterface.setPublic(false);
		
		sCInterface.setReturn(false);
		
		sCInterface.setShort(false);
		
		sCInterface.setStatic(false);
		
		sCInterface.setStrictfp(false);
		
		sCInterface.setSuper(false);
		
		sCInterface.setSwitch(false);
		
		sCInterface.setSynchronized(false);
		
		sCInterface.setThis(false);
		
		sCInterface.setThrow(false);
		
		sCInterface.setThrows(false);
		
		sCInterface.setTransient(false);
		
		sCInterface.setTry(false);
		
		sCInterface.setVoid(false);
		
		sCInterface.setVolatile(false);
	}
	
	/* 'default' enter sequence for state abstract */
	private void enterSequence_goto_abstract_default() {
		entryAction_goto_abstract();
		nextStateIndex = 0;
		stateVector[0] = State.goto_abstract;
	}
	
	/* 'default' enter sequence for state boolean */
	private void enterSequence_goto_boolean_default() {
		entryAction_goto_boolean();
		nextStateIndex = 0;
		stateVector[0] = State.goto_boolean;
	}
	
	/* 'default' enter sequence for state void */
	private void enterSequence_goto_void_default() {
		enterSequence_goto_void_volatile_default();
	}
	
	/* 'try' enter sequence for state void */
	private void enterSequence_goto_void_try() {
		enterSequence_goto_void_volatile_try();
	}
	
	/* 'default' enter sequence for state transient */
	private void enterSequence_goto_void_volatile_transient_default() {
		enterSequence_goto_void_volatile_transient_throw_default();
		historyVector[0] = stateVector[0];
	}
	
	/* 'this' enter sequence for state transient */
	private void enterSequence_goto_void_volatile_transient_this() {
		enterSequence_goto_void_volatile_transient_throw_this();
		historyVector[0] = stateVector[0];
	}
	
	/* 'default' enter sequence for state false */
	private void enterSequence_goto_void_volatile_transient_throw_false_default() {
		nextStateIndex = 0;
		stateVector[0] = State.goto_void_volatile_transient_throw_false;
		
		historyVector[1] = stateVector[0];
	}
	
	/* 'default' enter sequence for state state */
	private void enterSequence_goto_void_volatile_state_default() {
		nextStateIndex = 0;
		stateVector[0] = State.goto_void_volatile_state;
		
		historyVector[0] = stateVector[0];
	}
	
	/* 'default' enter sequence for region goto */
	private void enterSequence_goto_default() {
		react_goto__entry_Default();
	}
	
	/* 'try' enter sequence for region volatile */
	private void enterSequence_goto_void_volatile_try() {
		react_goto_void_volatile_try();
	}
	
	/* 'default' enter sequence for region volatile */
	private void enterSequence_goto_void_volatile_default() {
		react_goto_void_volatile__entry_Default();
	}
	
	/* shallow enterSequence with history in child volatile */
	private void shallowEnterSequence_goto_void_volatile() {
		switch (historyVector[0]) {
		case goto_void_volatile_transient_throw_false:
			enterSequence_goto_void_volatile_transient_default();
			break;
		case goto_void_volatile_state:
			enterSequence_goto_void_volatile_state_default();
			break;
		default:
			break;
		}
	}
	
	/* 'this' enter sequence for region throw */
	private void enterSequence_goto_void_volatile_transient_throw_this() {
		react_goto_void_volatile_transient_throw_this();
	}
	
	/* 'default' enter sequence for region throw */
	private void enterSequence_goto_void_volatile_transient_throw_default() {
		react_goto_void_volatile_transient_throw__entry_Default();
	}
	
	/* deep enterSequence with history in child throw */
	private void deepEnterSequence_goto_void_volatile_transient_throw() {
		switch (historyVector[1]) {
		case goto_void_volatile_transient_throw_false:
			enterSequence_goto_void_volatile_transient_throw_false_default();
			break;
		default:
			break;
		}
	}
	
	/* Default exit sequence for state abstract */
	private void exitSequence_goto_abstract() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state boolean */
	private void exitSequence_goto_boolean() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state false */
	private void exitSequence_goto_void_volatile_transient_throw_false() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state state */
	private void exitSequence_goto_void_volatile_state() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for region goto */
	private void exitSequence_goto() {
		switch (stateVector[0]) {
		case goto_abstract:
			exitSequence_goto_abstract();
			break;
		case goto_boolean:
			exitSequence_goto_boolean();
			break;
		case goto_void_volatile_transient_throw_false:
			exitSequence_goto_void_volatile_transient_throw_false();
			break;
		case goto_void_volatile_state:
			exitSequence_goto_void_volatile_state();
			break;
		default:
			break;
		}
	}
	
	/* Default exit sequence for region volatile */
	private void exitSequence_goto_void_volatile() {
		switch (stateVector[0]) {
		case goto_void_volatile_transient_throw_false:
			exitSequence_goto_void_volatile_transient_throw_false();
			break;
		case goto_void_volatile_state:
			exitSequence_goto_void_volatile_state();
			break;
		default:
			break;
		}
	}
	
	/* Default exit sequence for region throw */
	private void exitSequence_goto_void_volatile_transient_throw() {
		switch (stateVector[0]) {
		case goto_void_volatile_transient_throw_false:
			exitSequence_goto_void_volatile_transient_throw_false();
			break;
		default:
			break;
		}
	}
	
	/* The reactions of state abstract. */
	private void react_goto_abstract() {
		if (check_goto_abstract_tr0_tr0()) {
			effect_goto_abstract_tr0();
		}
	}
	
	/* The reactions of state boolean. */
	private void react_goto_boolean() {
		if (check_goto_boolean_tr0_tr0()) {
			effect_goto_boolean_tr0();
		} else {
			if (check_goto_boolean_tr1_tr1()) {
				effect_goto_boolean_tr1();
			}
		}
	}
	
	/* The reactions of state false. */
	private void react_goto_void_volatile_transient_throw_false() {
	}
	
	/* The reactions of state state. */
	private void react_goto_void_volatile_state() {
		if (check_goto_void_volatile_state_tr0_tr0()) {
			effect_goto_void_volatile_state_tr0();
		}
	}
	
	/* Default react sequence for initial entry  */
	private void react_goto__entry_Default() {
		enterSequence_goto_abstract_default();
	}
	
	/* Default react sequence for shallow history entry try */
	private void react_goto_void_volatile_try() {
		/* Enter the region with shallow history */
		if (historyVector[0] != State.$NullState$) {
			shallowEnterSequence_goto_void_volatile();
		} else {
			enterSequence_goto_void_volatile_state_default();
		}
	}
	
	/* Default react sequence for deep history entry this */
	private void react_goto_void_volatile_transient_throw_this() {
		/* Enter the region with deep history */
		if (historyVector[1] != State.$NullState$) {
			deepEnterSequence_goto_void_volatile_transient_throw();
		} else {
			enterSequence_goto_void_volatile_transient_throw_false_default();
		}
	}
	
	/* Default react sequence for initial entry  */
	private void react_goto_void_volatile_transient_throw__entry_Default() {
		enterSequence_goto_void_volatile_transient_throw_false_default();
	}
	
	/* Default react sequence for initial entry  */
	private void react_goto_void_volatile__entry_Default() {
		enterSequence_goto_void_volatile_transient_default();
	}
	
	public void runCycle() {
		if (!initialized)
			throw new IllegalStateException(
					"The state machine needs to be initialized first by calling the init() function.");
		clearOutEvents();
		for (nextStateIndex = 0; nextStateIndex < stateVector.length; nextStateIndex++) {
			switch (stateVector[nextStateIndex]) {
			case goto_abstract:
				react_goto_abstract();
				break;
			case goto_boolean:
				react_goto_boolean();
				break;
			case goto_void_volatile_transient_throw_false:
				react_goto_void_volatile_transient_throw_false();
				break;
			case goto_void_volatile_state:
				react_goto_void_volatile_state();
				break;
			default:
				// $NullState$
			}
		}
		clearEvents();
	}
}
