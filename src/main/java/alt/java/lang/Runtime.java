package alt.java.lang;

import java.io.InputStream;
import java.io.OutputStream;

import alt.java.io.IOException;

public class Runtime {
	private static Runtime instance;
	
	public static Runtime getRuntime() {
		if (instance == null) {
			instance = new Runtime();
		}
		return instance;
	}
	
	public void exit(int status) 
			throws SecurityException {
		SecurityManager secman = System.getSecurityManager();
		if (secman != null) {
			secman.checkExit(status);
		}
		exit(0);
	}
	
	private native void exit0(int status);
		// exit(status);
	
	public Process exec(String command)
			throws IOException, SecurityException {
		return null;
	}
	public Process exec(String command, String envp[])
			throws IOException, SecurityException {
		return null;
	}
	
	public Process exec(String cmdarray[])
			throws IOException, SecurityException {
		return null;
	}
	
	public long totalMemory() {
		return 100;
	}
	
	public long freeMemory() {
		return 50;
	}
	
	public void gc() {
		
	}
	
	public void runFinalization() {
		
	}
	
	public void traceInstructions(boolean on) {
		
	}
	
	public void traceMethodCalls(boolean on) {
		
	}
	
	public void load(String filename)
			throws SecurityException, UnsatisfiedLinkError {
		
	}
	
	public void loadLibrary(String libname)
			throws SecurityException, UnsatisfiedLinkError {
	
	}

	public InputStream getLocalizedInputStream(InputStream in) {
		return in;
	}
	
	public OutputStream getLocalizedOutputStream(OutputStream out) {
		return out;
	}
	
}
