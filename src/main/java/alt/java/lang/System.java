package alt.java.lang;

import java.io.InputStream;
import java.io.PrintStream;

import alt.java.lang.ArrayStoreException;
import alt.java.lang.IndexOutOfBoundsException;
import alt.java.util.Properties;

public class System {

	public static InputStream in;
	public static PrintStream out;
	public static PrintStream err;

	private static SecurityManager securityManager;
	private static Properties properties;
	
	public static SecurityManager getSecurityManager() {
		return securityManager;
	}
	
	public static void setSecurityManager(SecurityManager s)
			throws SecurityException {
		if (securityManager != null) {
			throw new SecurityException("Cannot change non-null security manager");
		}
		securityManager = s;
	}
	
	public static native long currentTimeMillis();
	
	public static Properties getProperties()
			throws SecurityException {
		if (securityManager != null) {
			securityManager.checkPropertiesAccess();
		}
		return properties;
	}
	
	public static void setProperties(Properties props)
			throws SecurityException {
		if (securityManager != null) {
			securityManager.checkPropertiesAccess();
		}
		properties = props;
	}
	
	public static String getProperty(String key)
			throws SecurityException {
		return getProperty(key, null);
	}
	public static String getProperty(String key, String defaultValue)
			throws SecurityException {
		if (securityManager != null) {
			securityManager.checkPropertyAccess();
		}
		return getProperties().getProperty(key, defaultValue);
	}
	
	public static void exit(int status)
			throws SecurityException {
		Runtime.getRuntime().exit(status);
	}

	public static void gc() {
		Runtime.getRuntime().gc();
	}
	
	public static void runFinalization() {
		Runtime.getRuntime().runFinalization();
	}
	
	public static void load(String filename) 
			throws SecurityException, UnsatisfiedLinkError {
		Runtime.getRuntime().load(filename);
	}
	
	public static void loadLibrary(String libname)
			throws SecurityException, UnsatisfiedLinkError {
		Runtime.getRuntime().loadLibrary(libname);
	}
	
	public static native void arraycopy(Object src, int srcOffset, Object dst, int dstOffset, int length) 
			throws NullPointerException, ArrayStoreException, IndexOutOfBoundsException;
}