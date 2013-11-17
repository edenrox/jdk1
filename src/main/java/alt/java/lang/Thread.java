package alt.java.lang;

import alt.java.lang.Exception;
import alt.java.lang.IllegalArgumentException;
import alt.java.lang.IllegalThreadStateException;

public class Thread implements Runnable {

	public static final int MIN_PRIORITY = 1;
	public static final int MAX_PRIORITY = 10;
	public static final int NORM_PRIORITY = 5;
	
	protected ThreadGroup threadGroup;
	protected String name;
	protected int priority;
	protected Runnable target;
	protected boolean isDaemon;
	
	public Thread() 
			throws SecurityException, IllegalThreadStateException {
		init(null, null, null);
	}
	public Thread(String name) 
			throws SecurityException, IllegalThreadStateException {
		init(null, null, name);
	}
	public Thread(Runnable target) 
			throws SecurityException, IllegalThreadStateException {
		init(null, target, null);
	}
	
	public Thread(Runnable target, String name) {
		init(null, target, name);
	}
	
	
	public Thread(ThreadGroup group, Runnable target) {
		init(group, target, null);
	}
	
	public Thread(ThreadGroup group, String name) {
		init(group, null, name);
	}
	
	public Thread(ThreadGroup group, Runnable target, String name) {
		init(group, target, name);
	}
		
	private final void init(ThreadGroup group, Runnable target, String name) 
			throws SecurityException, IllegalThreadStateException {
		
		if (group == null) {
			group = getDefaultThreadGroup();
		}
		if (name == null) {
			name = getDefaultThreadName(group);
		}
		
		this.threadGroup = group;
		if (group != null) {
			group.addThread(this);
		}
		
		this.target = target;
		this.name = name;
		this.priority = NORM_PRIORITY;
		this.isDaemon = false;
	}
	
	private final ThreadGroup getDefaultThreadGroup() {
		return null;
	}
	
	private final String getDefaultThreadName(ThreadGroup g) {
		return null;
	}
	
	public ThreadGroup getThreadGroup() {
		return threadGroup;
	}
	
	
	public void start() {
		run0();
	}
	
	private void run0() {
		try {
			run();
		} catch(Throwable ex) {
			threadGroup.uncaughtException(this, ex);
		}
	}
	
	public void run() {
		if (this.target != null) {
			this.target.run();
		}
	}
	
	public final void stop(Throwable thr) 
			throws SecurityException, NullPointerException {
		checkAccess();
		if (thr == null) {
			throw new NullPointerException("Cannot throw null");
		}
	
		// TODO: implement this
	}
	
	public final void stop() 
			throws SecurityException {
		stop(new ThreadDeath());
	}
	
	public final void resume()
			throws SecurityException {
		checkAccess();
		
		// TODO: implement this
		
	}
	
	public final void suspend() {
		
	}
	
	public final void destroy() 
		throws IllegalThreadStateException {
		
	}
	
	public void interrupt() {
	
	}
	
	public boolean isInterrupted() {
		return false;
	}
	
	public final String getName() {
		return name;
	}
	
	public final void setName(String name) 
			throws SecurityException {
		checkAccess();
		this.name = name;
	}
	
	public final int getPriority() {
		return priority;
	}
	
	public final void setPriority(int newPriority) 
			throws SecurityException, IllegalArgumentException {
		checkAccess();
		if (newPriority < MIN_PRIORITY) {
			throw new IllegalArgumentException("priority is less than minimum");
		}
		if (newPriority > MAX_PRIORITY) {
			throw new IllegalArgumentException("priority exceeds maximum");
		}
		this.priority = Math.min(newPriority, threadGroup.getMaxPriority());
	}
	
	public final boolean isDaemon() {
		return isDaemon;
	}
	
	public final void setDaemon(boolean on)
			throws SecurityException, IllegalThreadStateException {
		checkAccess();
		if (isAlive()) {
			throw new IllegalThreadStateException("Cannot make daemon while alive");
		}
		isDaemon = on;
	}
	
	public final boolean isAlive() {
		return false;
	}
	
	public int countStackFrames() {
		return 5;
	}
	
	public void join() throws InterruptedException {
		
	}
	
	public void join(long millis) throws InterruptedException {
		join(millis, 0);
	}
	
	public void join(long millis, int nanos) throws InterruptedException {
		if (millis < 0) {
			throw new IllegalArgumentException("millis cannot be negative");
		}
		if ((nanos < 0) || (nanos > 999999)) {
			throw new IllegalArgumentException("nanos must be in range 0 - 999999");
		}
		
	}
	
	
	protected void checkAccess() {
		SecurityManager secman = System.getSecurityManager();
		if (secman != null) {
			secman.checkAccess(this);
		}
	}
	
	public static boolean interrupted() {
		return Thread.currentThread().isInterrupted();
	}
	
	public static Thread currentThread() {
		return null;
	}


	public static void dumpStack() {
		new Exception("Stack trace").printStackTrace();
	}
	
	public static void yield() {
		
	}
	
	public static void sleep(long millis)
			throws InterruptedException {
		
	}
	
	public static void sleep(long millis, int nanos)
			throws InterruptedException {
		
	}
}