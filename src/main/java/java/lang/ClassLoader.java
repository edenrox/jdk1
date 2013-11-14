package java.lang;

public abstract class ClassLoader {

	protected ClassLoader() throws SecurityException {
		SecurityManager secman = System.getSecurityManager();
		if (secman != null) {
			secman.checkCreateClassLoader();
		}
	}
	
	protected abstract Class loadClass(String name, boolean resolve)
			throws ClassNotFoundException;
	
	protected final Class defineClass(byte data[], int offset, int length) 
			throws NullPointerException, IndexOutOfBoundsException, ClassFormatException {
		if (data == null) {
			throw new NullPointerException("data cannot be null");
		}
		if (offset < 0) {
			throw new IndexOutOfBoundsException("offset cannot be negative");
		}
		if (length < 0) {
			throw new IndexOutOfBoundsException("length cannot be negative");
		}
		if (offset+length > data.length) {
			throw new IndexOutOfBoundsException("offset + length must be less than array length");
		}
		
		
	}
	
	protected final void resolveClass(Class c) 
			throws NullPointerException {
		if (c == null) {
			throw new NullPointerException("class cannot be null");
		}
		
	}
	
	protected final Class findSystemClass(String name)
			throws ClassNotFoundException {
		
	}
	
}
