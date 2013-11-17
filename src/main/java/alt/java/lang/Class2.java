package alt.java.lang;

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
	
	public static Class2 forName(String className) 
			throws ClassNotFoundException {
		alt.java.lang.Object2 o = new alt.java.lang.Object2();
		//return (Class2) o.getClass().getClassLoader().loadClass(className, true);
		return null;
	}
}