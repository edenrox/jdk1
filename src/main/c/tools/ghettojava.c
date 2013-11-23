#include <stdio.h>
#include <string.h>
#include <jni.h>

//#define DEBUG
#define GHETTOJAVA_VERSION "0.1"
#define STRING_CLASS "java/lang/String"

#ifdef DEBUG
#define DEBUG_PRINT(...) do { fprintf( stderr, __VA_ARGS__); } while (0)
#else
#define DEBUG_PRINT(...) do {} while (0)
#endif

// "ghettojava" - executable for runing java applications in the Ghetto Java VM

void runClassWithArgs(char *className, int argc, char *argv[]) {
	const char *methodName = "main";
	const char *methodSignature = "([Ljava/lang/String;)V";

	DEBUG_PRINT("Verbose Debug Mode Enabled");
	DEBUG_PRINT("1. Starting JVM\n");

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
		fprintf(stderr, "Error starting JVM\n");
		return;
	} else {
		DEBUG_PRINT(" - JVM Created\n");
	}

	DEBUG_PRINT("2. Looking up class\n");

	// find the class
	jclass mainClassObj = (*env)->FindClass(env, className);
	if (!mainClassObj) {
		fprintf(stderr, "Error, could not find class: %s\n", className);
		return;
	} else {
		DEBUG_PRINT(" - Class Found: %s\n", className);
	}

	DEBUG_PRINT("3. Looking for a main method\n");

	// Find the main(String[] args) method
	jmethodID mainMethodID = (*env)->GetStaticMethodID(env, mainClassObj, methodName, methodSignature);
	if (mainMethodID == NULL) {
		fprintf(stderr, "Error, could not find main method for class.\n");
		fprintf(stderr, "Attempting to find: public void main(String[])\n");
		return;
	} else {
		DEBUG_PRINT(" - Main method found!\n");
	}

	DEBUG_PRINT("4. Creating String[] from arguments\n");

	// Build a java string array of the arguments
	jclass stringClassObj = (*env)->FindClass(env, STRING_CLASS);
	if (stringClassObj == NULL) {
		fprintf(stderr, " - Error could not find class: %s\n", STRING_CLASS);
		return;
	} else {
		DEBUG_PRINT(" - String class found\n");
	}

	// Create an object array to hold the arguments
	jobjectArray argsArray = (*env)->NewObjectArray(env, argc, stringClassObj, 0);

	// Set the elements of the array to be java.lang.String objects
	for(int i = 0; i < argc; i++) {
		jstring argString = (*env)->NewStringUTF(env, argv[i]);
		(*env)->SetObjectArrayElement(env, argsArray, i, argString);
	}

	// run the object's "main" method
	DEBUG_PRINT("5. Running main(String[]) method\n");
	(*env)->CallStaticVoidMethod(env, mainClassObj, mainMethodID, argsArray);

	// check if an exception was thrown while running the main method
	if ((*env)->ExceptionCheck(env)) {
		(*env)->ExceptionDescribe(env);
	}

	DEBUG_PRINT("7. Destroying JVM\n");
	(*jvm)->DestroyJavaVM(jvm);
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
