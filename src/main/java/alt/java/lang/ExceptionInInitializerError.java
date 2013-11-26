package alt.java.lang;

public class ExceptionInInitializerError 
		extends RuntimeException {
	
	private Throwable thrown;

	public ExceptionInInitializerError() {
		super();
	}
	
	public ExceptionInInitializerError(String message) {
		super(message);
	}
	
	public ExceptionInInitializerError(Throwable thrown) {
		this.thrown = thrown;
	}
	
	public Throwable getException() {
		return thrown;
	}
}
