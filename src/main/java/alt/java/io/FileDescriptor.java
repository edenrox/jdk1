package alt.java.io;

public final class FileDescriptor {
	public static final FileDescriptor in = new FileDescriptor(0);
	public static final FileDescriptor out = new FileDescriptor(1);
	public static final FileDescriptor err = new FileDescriptor(2);
	
	private int fd;
	private FileDescriptor(int fd) {
		this.fd = fd;
	}
	
	public boolean valid() {
		return (fd != -1);
	}
}
