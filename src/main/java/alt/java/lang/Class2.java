package alt.java.lang;

import java.lang.ClassNotFoundException;

public class Class2 {
	
	private boolean isInterface;
	private String2 name;
	private ClassLoader loader;

	public String2 toString2() {
		return String2.fromString((isInterface() ? "interface " : "class ") + getName());
	}
	
	public String2 getName() {
		return name;
	}
	
	public String2 getSimpleName() {
		String2 name = getName();
		int pos = name.lastIndexOf(String2.fromString("."));
		if (pos > 0) {
			name = name.substring(pos+1);
		}
		return name;
	}
	
	public native boolean isInterface();
	public native Class getSuperclass();
		// JNIEnv->GetSuperclass(env, thisObj);
	
	public native Class[] getInterfaces();	
	public native Object newInstance() throws InstantiationException, IllegalAccessException;

		// jclass clazz = thisObj;
		// jmethodID methodID = JNIEnv->GetMethodID(env, clazz, "init", "V");
		// return JNIEnv->NewObject(env, clazz, methodID);
	
	public ClassLoader getClassLoader() {
		return loader;
	}
	
	public static Class forName(String className) 
			throws ClassNotFoundException {
		Object o = new Object();
		return o.getClass().getClassLoader().loadClass(className);
	}
}