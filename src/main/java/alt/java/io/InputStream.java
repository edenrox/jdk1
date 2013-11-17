package alt.java.io;

public abstract class InputStream {
	public abstract int read() throws IOException;
	public int read(byte[] dest) throws IOException, NullPointerException {
		return read(dest, 0, dest.length);
	}
	public int read(byte[] dest, int offset, int length) 
			throws IOException, NullPointerException, IndexOutOfBoundsException {
		if (dest == null) {
			throw new NullPointerException("dest cannot be null");
		}
		if (offset < 0) {
			throw new IndexOutOfBoundsException("offset cannot be negative");
		}
		if (length < 0) {
			throw new IndexOutOfBoundsException("length cannot be negative");
		}
		if (offset + length > dest.length) {
			throw new IndexOutOfBoundsException("offset + length cannot exceed length of dest");
		}
		length = Math.min(length,  available());
		for(int i = offset; i < length; i++) {
			dest[i] = (byte) read();
		}
		return length;
	}
	
	public long skip(long n) throws IOException {
		return 0;
	}
	
	public int available() throws IOException {
		return 0;
	}
	
	public void close() throws IOException {
		// noop
	}
	
	public void mark(int readlimit) {
		// noop
	}
	
	public void reset() throws IOException {
		throw new IOException("reset not supported");
	}
	
	public boolean markSupported() {
		return false;
	}
}
