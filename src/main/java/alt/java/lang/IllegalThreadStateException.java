package alt.java.lang;

import java.lang.String;

public class IllegalThreadStateException extends IllegalArgumentException {
	public IllegalThreadStateException() {
		super();
	}
	public IllegalThreadStateException(String message) {
		super(message);
	}
}