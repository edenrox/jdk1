CC      = gcc
CFLAGS  = -g -I /System/Library/Frameworks/JavaVM.framework/Versions/Current/Headers/
LDFLAGS = -F/System/Library/Frameworks/JavaVM.framework/Frameworks -framework JavaVM

all: ghettojava

ghettojava: ghettojava.o
	$(CC) $(LDFLAGS) -o $@ $^
	
ghettojava.o: ghettojava.c
	$(CC) $(CFLAGS) -c -o $@ $<
	
clean:
	rm -f ghettojava *.o