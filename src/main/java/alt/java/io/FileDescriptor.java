package alt.java.io;

public final class FileDescriptor {
	public static final FileDescriptor in = new FileDescriptor();
	public static final FileDescriptor out = new FileDescriptor();
	public static final FileDescriptor err = new FileDescriptor();
	
	public boolean valid() {
		return true;
	}
}
