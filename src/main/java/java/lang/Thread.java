package java.lang;

public class Thread implements Runnable {

	public static final int MIN_PRIORITY = 1;
	public static final int MAX_PRIORITY = 10;
	public static final int NORM_PRIORITY = 5;
	
	protected ThreadGroup threadGroup;
	protected String name;
	protected Runnable target;
	
	public Thread() {
		init(null, null, name);
	}
	public Thread(String name) {
		init(null, null, name);
	}
	public Thread(Runnable target) {
		init(null, target, null);
	}
		
	protected final void init(ThreadGroup group, Runnable target, String name) 
			throws SecurityException, IllegalThreadStateException {
		this.threadGroup = group;
		this.target = target;
		this.name = name;
	}
	
	public ThreadGroup getThreadGroup() {
		return threadGroup;
	}
	
	
	public void start() {
	
	}
	
	public void run() {
		if (this.target != null) {
			this.target.run();
		}
	}
	
	public final void stop(Throwable o) {
	
	}
	
	public final void stop() {
	
	}
	
	public void interrupt() {
	
	}
	
	public boolean isInterrupted() {
		return false;
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