package alt.java.io;

public abstract class OutputStream {

	public abstract void write(int b)
			throws IOException;
	
	public void write(byte[] bytes)
			throws IOException, NullPointerException {
		
		write(bytes, 0, bytes.length);
	}
	
	public void write(byte[] bytes, int offset, int length)
			throws IOException, NullPointerException, IndexOutOfBoundsException {
		if (bytes == null) {
			throw new NullPointerException("bytes cannot be null");
		}
		if (offset < 0) {
			throw new IndexOutOfBoundsException("offset cannot be negative");
		}
		if (length < 0) {
			throw new IndexOutOfBoundsException("length cannot be negative");
		}
		if (offset + length > bytes.length) {
			throw new IndexOutOfBoundsException("offset + length extend beyond bytes length");
		}
		
		for(int i = offset; i < length; i++) {
			write(bytes[i]);
		}
	}
	
	public void flush()
			throws IOException {
		// noop
	}
	
	public void close()
			throws IOException {
		// noop
	}
	
	
}
