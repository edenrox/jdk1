package alt.java.io;

public class FileInputStream extends InputStream {
	
	private FileDescriptor fd;

	public FileInputStream(String path) throws SecurityException, FileNotFoundException {
		this(new File(path));
	}
	
	public FileInputStream(File file) throws SecurityException, FileNotFoundException {
		if (file == null) {
			throw new NullPointerException("file cannot be null");
		}
		if (!file.exists()) {
			throw new FileNotFoundException("file does not exist");
		}
		fd = open(file.getName());
	}
	
	public FileInputStream(FileDescriptor fd) {
		if (fd == null) {
			throw new NullPointerException("file descriptor cannot be null");
		}
		
		this.fd = fd;
	}
	
	private native FileDescriptor open(String filename);
	
	public native int read() throws IOException, NullPointerException;
	
	public int read(byte[] bytes) throws IOException, NullPointerException {
		return read(bytes, 0, bytes.length);
	}
	
	public int read(byte[] bytes, int offset, int length) throws IOException, NullPointerException {
		
		return 0;
	}
	
	public native long skip(long n) throws IOException;
	
	public native int available() throws IOException;
	
	public native void close() throws IOException;
	
	public final FileDescriptor getFD() throws IOException {
		return fd;
	}
	
	protected void finalize() throws IOException {
		
	}
}
