#include <stdio.h>
#include <string.h>
#include <jni.h>

#define GHETTOJAVA_VERSION "0.1"
#define STRING_CLASS "java.lang.String"

// "ghettojava" - executable for runing java applications in the Ghetto Java VM

void runClassWithArgs(char *className, int argc, char *argv[]) {
	const char *methodName = "main";
	const char *methodSignature = "([Ljava/lang/String;)V";

	printf("1. Starting JVM\n");

	// initialize the virtual machine
	JavaVM *jvm;
	JNIEnv *env;

	// Set java arguments
	JavaVMOption options[3];
	options[0].optionString = "-Djava.compiler=NONE";
	options[1].optionString = "-Djava.class.path=.";
	options[2].optionString = "-verbose:jni";

	JavaVMInitArgs vmargs;
	vmargs.version = JNI_VERSION_1_6;
	vmargs.nOptions = 3;
	vmargs.options = options;
	vmargs.ignoreUnrecognized = JNI_FALSE;

	int result = 0;
	result = JNI_CreateJavaVM(&jvm, (void **)&env, &vmargs);
	if (result != JNI_OK) {
		printf(" - Error starting JVM\n");
		return;
	} else {
		printf(" - JVM Created\n");
	}

	printf("2. Looking up class\n");

	// find the class
	jclass mainClassObj = (*env)->FindClass(env, className);
	if (!mainClassObj) {
		printf(" - Error, could not find class: %s\n", className);
		return;
	} else {
		printf(" - Class Found: %s\n", className);
	}

	printf("3. Looking for a main method\n");

	// Find the main(String[] args) method
	jmethodID mainMethodID = (*env)->GetStaticMethodID(env, mainClassObj, methodName, methodSignature);
	if (mainMethodID == NULL) {
		printf("Error, could not find main method for class.\n");
		printf("Attempting to find: public void main(String[])\n");
		return;
	} else {
		printf(" - Main method found!\n");
	}

	printf("4. Creating String[] from arguments\n");

	// Build a java string array of the arguments
	jclass stringClassObj = (*env)->FindClass(env, STRING_CLASS);
	if (stringClassObj == NULL) {
		printf(" - Error could not find class: %s\n", STRING_CLASS);
		return;
	} else {
		printf(" - String class found\n");
	}

	// Create an object array to hold the arguments
	jobjectArray argsArray = (*env)->NewObjectArray(env, argc, stringClassObj, 0);

	// Set the elements of the array to be java.lang.String objects
	for(int i = 0; i < argc; i++) {
		jstring argString = (*env)->NewStringUTF(env, argv[i]);
		(*env)->SetObjectArrayElement(env, argsArray, i, argString);
	}

	printf("5. Running main(String[]) method\n");

	// run the object's "main" method
	(*env)->CallStaticVoidMethod(env, mainClassObj, mainMethodID, argsArray);

	printf("6. Exiting\n");
}

void showVersion() {
	printf("ghettojava version \"%s\"\n", GHETTOJAVA_VERSION);
	printf("Hopkins ghettojava with Oracle JavaVM\n");
}

void showUsage() {
	printf("Usage: ghettojava class [args...]\n");
	printf("   or  ghettojava -version\n");
}

int main(int argc, char *argv[]) {
	if (argc == 1) {
		showUsage();
		return 0;
	} else if (argc == 2) {
		if (strcmp("-version", argv[1]) == 0) {
			showVersion();
			return 0;
		}
	}
	char *className = argv[1];
	runClassWithArgs(className, argc - 2, argv + 2);
}
