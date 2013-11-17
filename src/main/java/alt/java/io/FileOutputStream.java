package alt.java.io;

public class FileOutputStream extends OutputStream {
	
	private FileDescriptor fd;

	public FileOutputStream(String path)
			throws SecurityException, FileNotFoundException {
		
	}
	
	public FileOutputStream(File file)
			throws SecurityException, FileNotFoundException {
		
	}
	
	public FileOutputStream(FileDescriptor fd)
			throws SecurityException {
		checkWrite(fd);
		this.fd = fd;
	}
	
	public final FileDescriptor getFD() throws IOException {
		if (fd == null) {
			throw new IOException("FileDescriptor is closed");
		}
		return fd;
	}
	
	private void checkWrite(FileDescriptor fd) throws SecurityException {
		SecurityManager secman = System.getSecurityManager();
		if (secman != null) {
			//secman.checkWrite(fd);
		}
	}
	
	public void write(int b) throws IOException {
		
	}
}
