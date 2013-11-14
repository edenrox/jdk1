package java.lang;

public class Throwable {

	protected String message;
	protected Throwable cause = this;
	protected StackTraceElement[] stackTrace;

	public Throwable() {
		this.message = null;
		fillInStackTrace();
	}
	
	public Throwable(String message) {
		this.message = message;
		fillInStackTrace();
	}
	
	public String toString() {
		return getClass().getName() + ":" + getMessage();
	}
	
	public String getMessage() {
		if (message == null) {
			return "null";
		} else {
			return message;
		}
	}
	
	public native Throwable fillInStackTrace();
	
	public void printStackTrace() {
		printStackTrace(System.err);
	}
	
	public void printStackTrace(java.io.PrintStream s) {
		s.println(getClass().getName());
		if (stackTrace != null) {
			for(int i = 0; i < stackTrace.length; i++) {
				s.print("\tat ");
				s.println(stackTrace[i]);
			}
		}
	}
}