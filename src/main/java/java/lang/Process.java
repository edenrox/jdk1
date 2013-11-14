package java.lang;

import java.io.OutputStream;
import java.io.InputStream;

public abstract class Process {

	public abstract OutputStream getOutputStream();
	public abstract InputStream getInputStream();
	public abstract InputStream getErrorStream();
	public abstract int waitFor() throws InterruptedException;
	public abstract int exitValue() throws IllegalThreadStateException;
	public abstract void destroy();
}