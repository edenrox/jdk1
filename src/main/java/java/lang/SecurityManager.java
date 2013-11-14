package java.lang;

public abstract class SecurityManager {

	protected boolean inCheck = false;
	
	protected SecurityManager()
			throws SecurityException {
			
	}
	
	public boolean getInCheck() {
		return inCheck;
	}
	
	public abstract void checkCreateClassLoader() throws SecurityException;
	public abstract void checkAccess(Thread t) throws SecurityException;
	public abstract void checkAccess(ThreadGroup g) throws SecurityException;
	public abstract void checkExit(int status) throws SecurityException;
	public abstract void checkExec(String cmd) throws SecurityException;
	public abstract void checkPropertiesAccess() throws SecurityException;
	public abstract void checkPropertyAccess() throws SecurityException;
	public abstract void checkLink(String libname) throws SecurityException;
	public abstract void checkRead(int fd) throws SecurityException;
	public abstract void checkRead(String file) throws SecurityException;
	public abstract void checkWrite(int fd) throws SecurityException;
	public abstract void checkWrite(String file) throws SecurityException;
	public abstract void checkDelete(String file) throws SecurityException;
	public abstract void checkConnect(String host, int port) throws SecurityException;
	public abstract void checkListen(int port) throws SecurityException;
	public abstract void checkAccept(String host, int port) throws SecurityException;
	public abstract void checkSetFactory() throws SecurityException;
	public abstract void checkTopLevelWindow() throws SecurityException;
	public abstract void checkPackageAccess(String packageName) throws SecurityException;
	public abstract void checkPackageDefinition(String packageName) throws SecurityException;
}