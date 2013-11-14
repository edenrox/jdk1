package java.lang;

import java.util.Vector;

public class ThreadGroup {

	private ThreadGroup parent;
	private String name;
	private int maxPriority;
	private Vector threads;
	private Vector threadGroups;
	private boolean isDestroyed;
	private boolean isDaemon;

	public ThreadGroup(String name)
			throws SecurityException {
		init(Thread.currentThread().getThreadGroup(), name);
	}
	
	public ThreadGroup(ThreadGroup parent, String name)
			throws SecurityException {
		init(parent, name);
	}
	
	private final void init(ThreadGroup parent, String name)
			throws SecurityException, NullPointerException, IllegalThreadStateException {
		if (parent == null) {
			throw new NullPointerException("parent threadgroup cannot be null");
		}
		if (parent.isDestroyed) {
			throw new IllegalThreadStateException("parent threadgroup has been destroyed");
		}
		
		parent.checkAccess();
		this.parent = parent;
		this.name = name;
		this.threads = new Vector();
		this.threadGroups = new Vector();
		this.isDestroyed = false;
	}
	
	public final String getName() {
		return name;
	}
	
	public final ThreadGroup getParent() {
		return parent;
	}
	
	public final int getMaxPriority() {
		return maxPriority;
	}
	
	public final void setMaxPriority(int newMaxPriority)
			throws SecurityException, IllegalArgumentException {
		checkAccess();
		
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
		maxPriority = newMaxPriority;
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
	
	public final boolean isDaemon() {
		return isDaemon;
	}
	
	public final void setDaemon(boolean value)
			throws SecurityException {
		checkAccess();
		isDaemon = value;
	}
	
	public final int threadsCount() {
		return threads.size();
	}
	
	public int allThreadsCount() {
		int num = threads.size();
		for(int i = 0; i < threadGroups.size(); i++) {
			ThreadGroup child = (ThreadGroup) threadGroups.elementAt(i);
			num += child.allThreadsCount();
		}
		return num;
	}
	
	public int groupsCount() {
		return threadGroups.size();
	}
	
	public int allGroupsCount() {
		int num = threadGroups.size();
		for(int i = 0; i < threadGroups.size(); i++) {
			ThreadGroup child = (ThreadGroup) threadGroups.elementAt(i);
			num += child.allGroupsCount();
		}
		return num;
	}
	
	public Thread[] threads() {
		Thread[] rv = new Thread[threads.size()];
		threads.copyInto(rv);
		return rv;
	}
	
	public Thread[] allThreads() {
		Thread[] rv = new Thread[allThreadsCount()];
		allThreads0(rv, 0);
		return rv;
	}
	
	private int allThreads0(Thread[] dst, int offset) {
		for(int i = 0; i < threads.size(); i++) {
			dst[i + offset] = (Thread) threads.elementAt(i);
		}
		offset += threads.size();
		for(int i = 0; i < threadGroups.size(); i++) {
			ThreadGroup group = (ThreadGroup) threadGroups.elementAt(i);
			offset += group.allThreads0(dst, offset);
		}
		return offset;
	}
	
	public ThreadGroup[] groups() {
		ThreadGroup[] rv = new ThreadGroup[threadGroups.size()];
		threadGroups.copyInto(rv);
		return rv;
	}
	
	public ThreadGroup[] allGroups() {
		
	}
	
	public void list() {
		list0(0);
	}
	
	private String indentSpace(int level) {
		StringBuffer sb = new StringBuffer(level * 4);
		for(int i = 0; i < level * 4; i++) {
			sb.append(' ');
		}
		return sb.toString();
	}
	
	private void list0(int level) {
		String indent = indentSpace(level);
		String childIndent = indentSpace(level+1);
		
		System.out.print(indent);
		System.out.println(this.toString());
		for(int i = 0; i < threads.size(); i++) {
			Thread thread = (Thread) threads.elementAt(i);
			System.out.print(childIndent);
			System.out.print(thread.toString());
		}
		for(int i = 0; i < threadGroups.size(); i++) {
			ThreadGroup child = (ThreadGroup) threadGroups.elementAt(i);
			child.list0(level + 1);
		}
	}
	
	public void uncaughtException(Thread t, Throwable e) {
		if (parent == null) {
			if (!(e instanceof ThreadDeath)) {
				System.err.println("Uncaught exception on thread: " + t.toString());
				e.printStackTrace(System.err);
			}
		} else {
			parent.uncaughtException(t, e);
		}
	}
	
	protected final void checkAccess() {
		SecurityManager secman = System.getSecurityManager();
		if (secman != null) {
			secman.checkAccess(this);
		}
	}
	
	public final void stop()
			throws SecurityException {
		checkAccess();
		for(int i = 0; i < threads.size(); i++) {
			Thread thread = (Thread) threads.elementAt(i);
			thread.stop();
		}
		for (int i = 0; i < threadGroups.size(); i++) {
			ThreadGroup threadGroup = (ThreadGroup) threadGroups.elementAt(i);
			threadGroup.stop();
		}
	}
	
	public final void suspend()
			throws SecurityException {
		checkAccess();
		for(int i = 0; i < threads.size(); i++) {
			Thread thread = (Thread) threads.elementAt(i);
			thread.suspend();
		}
		for (int i = 0; i < threadGroups.size(); i++) {
			ThreadGroup threadGroup = (ThreadGroup) threadGroups.elementAt(i);
			threadGroup.suspend();
		}
	}
	
	public final void resume()
			throws SecurityException {
		checkAccess();
		for(int i = 0; i < threads.size(); i++) {
			Thread thread = (Thread) threads.elementAt(i);
			thread.resume();
		}
		for (int i = 0; i < threadGroups.size(); i++) {
			ThreadGroup threadGroup = (ThreadGroup) threadGroups.elementAt(i);
			threadGroup.resume();
		}
	}
	
	public final void destroy()
			throws SecurityException, IllegalThreadStateException {
		checkAccess();
		
		for(int i = 0; i < threads.size(); i++) {
			Thread thread = (Thread) threads.elementAt(i);
			thread.destroy();
		}
		for (int i = 0; i < threadGroups.size(); i++) {
			ThreadGroup threadGroup = (ThreadGroup) threadGroups.elementAt(i);
			threadGroup.destroy();
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