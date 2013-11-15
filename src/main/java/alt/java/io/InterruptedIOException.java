package alt.java.io;

import java.io.IOException;

public class InterruptedIOException extends IOException {
	
	public InterruptedIOException() {
		super();
	}
	
	public InterruptedIOException(String message) {
		super(message);
	}
}
