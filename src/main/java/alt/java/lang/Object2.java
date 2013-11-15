package alt.java.lang;

public class Object2 {

	public final native Class2 getClass2();
		// JNIEnv->GetObjectClass(env, thisObj);
	
	public String toString() {
		return getClass().getName() + "@" + Integer.toHexString(hashCode());
	}
	
	public boolean equals(Object obj) {
		return (this == obj);
	}
	
	public native int hashCode();
		// 
			
	protected Object clone2()
		throws CloneNotSupportedException {
		
		String msg = getClass().getName() + " does not support cloning";
		throw new CloneNotSupportedException(msg);
	}
	
	protected void finalize()
		throws Throwable {
		
		// default implementation does nothing
	}
	
	/*
	
	public final native void notifyAll();
	
	public final native void wait(long timeout)
			throws InterruptedException;

	public final void wait()
			throws InterruptedException {
		wait(0);
	}
		*/
}