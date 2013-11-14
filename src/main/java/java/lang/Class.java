package java.lang;

public class Class {
	
	private boolean isInterface;
	private String name;
	private ClassLoader loader;

	public String toString() {
		return (isInterface() ? "interface " : "class ") + getName();
	}
	
	public String getName() {
		return name;
	}
	
	public String getSimpleName() {
		String name = getSimpleName();
		int pos = name.lastIndexOf(".");
		if (pos > 0) {
			name = name.substring(pos+1);
		}
		return name;
	}
	
	public boolean isInterface() {
		return isInterface;
	}
	
	public native Class getSuperclass();
		// JNIEnv->GetSuperclass(env, thisObj);
	
	public Class[] getInterfaces() {
		return null;
	}
	
	public Object newInstance()
		throws InstantiationException, IllegalAccessException {
		
		return newInstance0();
	}
	
	public native Object newInstance0();
		// jclass clazz = thisObj;
		// jmethodID methodID = JNIEnv->GetMethodID(env, clazz, "init", "V");
		// return JNIEnv->NewObject(env, clazz, methodID);
	
	public ClassLoader getClassLoader() {
		return loader;
	}
	
	public static Class forName(String className) 
			throws ClassNotFoundException {
		Object o = new Object();
		return o.getClass().getClassLoader().loadClass(className, true);
	}
}