package org.yakindu.scr.statechartkeywords;

import java.util.List;
import org.yakindu.scr.IStatemachine;
import org.yakindu.scr.ITimerCallback;

public interface IStatechartKeywordsStatemachine extends ITimerCallback,IStatemachine {

	public interface SCIIf {
	
		public boolean isRaisedOperationCallback();
		
		public boolean isRaisedListeners();
		
		public long getTimer();
		
		public void setTimer(long value);
		
		public long getIsActive();
		
		public void setIsActive(long value);
		
		public long getInit();
		
		public void setInit(long value);
		
		public long getEnter();
		
		public void setEnter(long value);
		
		public long getRunCycle();
		
		public void setRunCycle(long value);
		
	public List<SCIIfListener> getListeners();
		public void setSCIIfOperationCallback(SCIIfOperationCallback operationCallback);
	
	}
	
	public interface SCIIfListener {
	
		public void onOperationCallbackRaised();
		public void onListenersRaised();
		}
	
	public interface SCIIfOperationCallback {
	
		public void myOperation();
		
	}
	
	public SCIIf getSCIIf();
	
	public interface InternalOperationCallback {
	
		public void myOperation();
		
	}
	
	public void setInternalOperationCallback(InternalOperationCallback operationCallback);
	
}
