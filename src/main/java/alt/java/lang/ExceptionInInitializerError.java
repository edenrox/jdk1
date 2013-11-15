package alt.java.lang;

import java.lang.RuntimeException;
import java.lang.String;
import java.lang.Throwable;

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
