CC      = gcc
CFLAGS  = -g -I/Library/Java/JavaVirtualMachines/jdk1.7.0_40.jdk/Contents/Home/include/ -I/Library/Java/JavaVirtualMachines/jdk1.7.0_40.jdk/Contents/Home/include/darwin
LDFLAGS = -L/Library/Java/JavaVirtualMachines/jdk1.7.0_40.jdk/Contents/Home/jre/lib/jli -ljli

all: ghettojava

ghettojava: ghettojava.o HelloWorld.class
	$(CC) $(LDFLAGS) -o $@ *.o
	
HelloWorld.class: HelloWorld.java
	javac HelloWorld.java
	
ghettojava.o: ghettojava.c
	$(CC) $(CFLAGS) -c -o $@ $<
	
clean:
	rm -f ghettojava *.o *.class