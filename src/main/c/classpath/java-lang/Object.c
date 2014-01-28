#include "Object.h"

// public Class java.lang.Object.getClass()
JNIEXPORT jobject JNICALL Java_alt_java_lang_Object2_getClass2
  (JNIEnv *env, jobject thisObj) {

	// Ask JNI what class this object is
	return (*env)->GetObjectClass(env, thisObj);
}

JNIEXPORT jint JNICALL Java_alt_java_lang_Object2_hashCode
  (JNIEnv *env, jobject thisObj) {
	// Cast the object pointer to an int
	// This only works if the garbage collector never moves objects (it does though)
	return (jint) thisObj;
}
