jdk1
====

Purpose
-------
I want to better understand how the JDK works

Explanation
-----------
This project is a partial implementation of JDK 1.0 class path.

I am following the specification for Java 1.0 found here:
* http://msdn.microsoft.com/en-us/library/aa286478(v=vs.60).aspx

Method
------
I am hoping to use a JVM implementation to test this, but plan to write all the
native functions required to get my JDK 1.0 fully running.

Progress
--------
I have started working on things and a lot remains to do still, but
I believe the collection classes Hashtable and Vector are ready for
testing.

Also, I have some good work done on the String class.  StringBuffer
is not started, but should be easy.

I have ideas about how to implement the native parts of Object, System,
and Class.

I haven't even looked at ClassLoader.

License
-------
This work is based on the Java 1.0 Specification, but is a clean room
implementation (aka, I didn't copy it from OpenJDK, GCJ, etc).  I'd like
to find an appropriate license for this, but for now:

1. I own this code, so if you want to copy it, or use it, you'll need to contact me for a license
1. I do NOT own the specification, Oracle/Sun/IBM do, talk to them
1. None of the code is GPL'd or copied, this is a clean room implementation