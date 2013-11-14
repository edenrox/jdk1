package java.lang;

public final class StackTraceElement {

	public static final String NATIVE_METHOD_NAME = "(Native Method)";
	public static final String UNKNOWN_FILE_NAME = "(Unknown Source)";

	private String declaringClass;
	private String methodName;
	private String fileName;
	private int lineNumber;
	
	public StackTraceElement(String declaringClass, String methodName, String fileName, int lineNumber)
			throws NullPointerException {
		if (declaringClass == null) {
			throw new NullPointerException("Declaring class is null");
		}
		if (methodName == null) {
			throw new NullPointerException("Method name is null");
		}
		this.declaringClass = declaringClass;
		this.methodName = methodName;
		this.fileName = fileName;
		this.lineNumber = lineNumber;
	}
	
	public String getDeclaringClass() {
		return declaringClass;
	}
	
	public String getMethodName() {
		return methodName;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public int getLineNumber() {
		return lineNumber;
	}
	
	public boolean isNativeMethod() {
		return (lineNumber == -2);
	}
	
	public String toString() {
		String rv = declaringClass + ".";
		if (isNativeMethod()) {
			rv += NATIVE_METHOD_NAME;
		} else {
			rv += methodName;
			if (fileName == null) {
				rv += UNKNOWN_FILE_NAME;
			} else {
				rv += "(" + fileName;
				if (lineNumber >= 0) {
					rv += ":" + lineNumber;
				}
			}
		}
		return rv;
	}
}