package java.lang;

public class Class {

	public String toString() {
		return (isInterface() ? "interface " : "class ") + getName();
	}
	
	public native String getName();
		// Implementation dependent
	
	public String getSimpleName() {
		String name = getSimpleName();
		int pos = name.lastIndexOf(".");
		if (pos > 0) {
			name = name.substring(pos+1);
		}
		return name;
	}
	
	public native boolean isInterface();
		// Implementation dependent
	
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
		return null;
	}
}