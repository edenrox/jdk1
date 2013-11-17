package alt.java.lang;

public class Object2 {

	public final Class2 getClass2() {
		return null;
	}
	
	public final String toString() {
		return toString2().toString();
	}
	
	public String2 toString2() {
		return String2.fromString(getClass().getName() + "@" + Integer.toHexString(hashCode()));
	}
	
	public boolean equals(Object obj) {
		return (this == obj);
	}
	
	public int hashCode() {
		return super.hashCode();
	}
			
	protected Object2 clone2()
		throws CloneNotSupportedException {
		
		String msg = getClass().getName() + " does not support cloning";
		throw new CloneNotSupportedException(msg);
	}
	
	protected void finalize()
		throws Throwable {
		// default implementation does nothing
	}
	
	public final void notifyAll2() {
		notifyAll();
	}
	
	public void wait2(long timeout)
			throws InterruptedException {
		try {
			wait(timeout);
		} catch (java.lang.InterruptedException ex) {
			throw new InterruptedException();
		}
	}

	public final void wait2()
			throws InterruptedException {
		wait2(0);
	}
}