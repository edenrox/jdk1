#include "System.h"
#include <sys/time.h>

// public static long currentTimeMillis();
JNIEXPORT jlong JNICALL Java_alt_java_lang_System_currentTimeMillis
		(JNIEnv *env, jclass classObj) {

	// Get a timeval struct of time since EPOCH
	struct timeval currentTime;
	gettimeofday(&timeval, NULL);

	// Convert the timeval struct to a jlong milliseconds
	jlong rv = ((jlong) currentTime.tv_sec) * 1000;
	rv += (jlong) (currentTime.tv_usec / 1000);
	return rv;
}

// public static void arraycopy(Object src, int srcOffset, Object dst, int dstOffset, int length);
JNIEXPORT void JNICALL Java_alt_java_lang_System_arraycopy
  (JNIEnv *env, jclass systemClass, jobject src, jint srcOffset, jobject dst, jint dstOffset, jint length) {


	if (dst == null) {
		jclass toThrowClass = (*env)->FindClass(env, "java.lang.NullPointerException");
		(*env)->ThrowNew(env, toThrowClass, "dst cannot be null");
		return;
	}
	if (src == null) {
		jclass toThrowClass = (*env)->FindClass(env, "java.lang.NullPointerException");
		(*env)->ThrowNew(env, toThrowClass, "src cannot be null");
		return;
	}

	// Figure out the classes of each array
	jclass srcClass = (*env)->GetObjectClass(env, src);
	jclass dstClass = (*env)->GetObjectClass(env, dst);

	if (srcClass)

	jsize srcLength = (*env)->GetArrayLength(env, src);
	jsize dstLength = (*env)->GetArrayLength(env, dst);

	if (srcOffset < 0) {
		jclass toThrowClass = (*env)->FindClass(env, "java.lang.IndexOutOfBoundsException");
		(*env)->ThrowNew(env, toThrowClass, "srcOffset cannot be negative");
		return;
	}
	if (dstOffset < 0) {
		jclass toThrowClass = (*env)->FindClass(env, "java.lang.IndexOutOfBoundsException");
		(*env)->ThrowNew(env, toThrowClass, "dstOffset cannot be negative");
		return;
	}
	if (length < 0) {
		jclass toThrowClass = (*env)->FindClass(env, "java.lang.IndexOutOfBoundsException");
		(*env)->ThrowNew(env, toThrowClass, "length cannot be negative");
		return;
	}
	if (srcOffset+length > srcLength) {
		jclass toThrowClass = (*env)->FindClass(env, "java.lang.IndexOutOfBoundsException");
		(*env)->ThrowNew(env, toThrowClass, "srcOffset+length exceeds src.length");
		return;
	}
	if (dstOffset+length > dstLength) {
		jclass toThrowClass = (*env)->FindClass(env, "java.lang.IndexOutOfBoundsException");
		(*env)->ThrowNew(env, toThrowClass, "dstOffset+length exceeds dst.length");
		return;
	}

	int elementWidth = 4;
	/*
	switch (type) {
	case "jlong":
	case "jdouble":
		elementWidth = sizeof(jlong);
		break;
	case "jchar":
	case "jshort":
		elementWidth = sizeof(jchar);
		break
	case "jbyte":
	case "jboolean":
		elementWidth = sizeof(jbyte);
		break;
	case "jint":
	case "jfloat":
	case "jobject":
	default:
		elementWidth = sizeof(jint);
		break;
	}
	*/

	memcpy(src, dst, length * elementWidth);

}
