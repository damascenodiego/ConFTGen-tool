package org.yakindu.scr.runnabletest;

import java.util.List;
import org.yakindu.scr.IStatemachine;
import org.yakindu.scr.ITimerCallback;

public interface IRunnableTestStatemachine extends ITimerCallback,IStatemachine {

	public interface SCInterface {
	
		public boolean isRaisedEv_out();
		
		public long getEv_outValue();
		
		public void raiseEv_in(long value);
		
		public long getMyVar();
		
		public void setMyVar(long value);
		
		public long getAfterCalls();
		
		public void setAfterCalls(long value);
		
		public long getCycles();
		
		public void setCycles(long value);
		
		public long getS2_entered();
		
		public void setS2_entered(long value);
		
	public List<SCInterfaceListener> getListeners();
		public void setSCInterfaceOperationCallback(SCInterfaceOperationCallback operationCallback);
	
	}
	
	public interface SCInterfaceListener {
	
		public void onEv_outRaised(long value);
		}
	
	public interface SCInterfaceOperationCallback {
	
		public void displayTime();
		
	}
	
	public SCInterface getSCInterface();
	
}
