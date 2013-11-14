package java.lang;

import java.util.Vector;

public class ThreadGroup {

	protected ThreadGroup parent;
	protected String name;
	protected int maxPriority;
	protected Vector threads;
	protected Vector threadGroups;
	

	public ThreadGroup(String name)
			throws SecurityException {
		init(Thread.currentThread().getThreadGroup(), name);
	}
	
	public ThreadGroup(ThreadGroup parent, String name)
			throws SecurityException {
		init(parent, name);
	}
	
	protected final void init(ThreadGroup parent, String name)
			throws SecurityException {
		this.parent = parent;
		this.name = name;
		this.threads = new Vector();
		this.threadGroups = new Vector();
	}
	
	public final String getName() {
		return this.name;
	}
	
	public final ThreadGroup getParent() {
		return this.parent;
	}
	
	public final int getMaxPriority() {
		return this.maxPriority;
	}
	
	public final void setMaxPriority(int newMaxPriority)
			throws SecurityException, IllegalArgumentException {
		this.checkAccess();
		
		if (newMaxPriority < Thread.MIN_PRIORITY) {
			throw new IllegalArgumentException("Thread group maximum priority is less than minimum");
		}
		if (newMaxPriority > Thread.MAX_PRIORITY) {
			throw new IllegalArgumentException("Thread group maximum priority is less than minimum");
		}
		if (parent != null) {
			if (newMaxPriority > parent.getMaxPriority()) {
				newMaxPriority = parent.getMaxPriority();
			}
		}
		this.maxPriority = newMaxPriority;
	}
	
	public final boolean parentOf(ThreadGroup g) {
		if ((g == null) || (g.getParent() == null)) {
			// null or has no parent
			return false;
		} else if (g.getParent() == this) {
			// direct parent
			return true;
		} else {
			// ancestor
			return parentOf(g.getParent());
		}
	}
	
	protected void checkAccess() {
		SecurityManager secman = System.getSecurityManager();
		if (secman != null) {
			secman.checkAccess(this);
		}
	}
	
	public String toString() {
		return getClass().getName()
			+ "[name="
			+ name
			+ ",maxpri="
			+ maxPriority
			+ "]";
	}
}