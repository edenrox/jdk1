package alt.java.lang;

public class Object2 {

	
	
	public String2 toString2() {
		return String2.fromString(getClass().getName() + "@" + Integer.toHexString(hashCode()));
	}
	
	public boolean equals(Object obj) {
		return (this == obj);
	}
	
	
			
	protected Object clone() {
		String msg = getClass().getName() + " does not support cloning";
		//throw new CloneNotSupportedException(msg);
		
		return null;
	}
	
	protected void finalize() throws Throwable {
		// default implementation does nothing
	}
	
	public final native Class2 getClass2();
	public native int hashCode();
	public native final void notify2();
	public native final void notifyAll2();
	public native final void wait2(long timeout);

	public final void wait2()
			throws InterruptedException {
		wait2(0);
	}
}