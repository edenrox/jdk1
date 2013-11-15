package alt.java.lang;

import java.lang.RuntimeException;
import java.lang.String;

public class IllegalMonitorStateException extends RuntimeException {
	public IllegalMonitorStateException() {
		super();
	}
	public IllegalMonitorStateException(String message) {
		super(message);
	}
}