package alt.java.lang;

import java.lang.RuntimeException;
import java.lang.String;

public class IndexOutOfBoundsException extends RuntimeException {
	public IndexOutOfBoundsException() {
		super();
	}
	public IndexOutOfBoundsException(String message) {
		super(message);
	}
}