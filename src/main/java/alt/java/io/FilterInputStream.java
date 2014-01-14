package alt.java.io;


public class FilterInputStream extends InputStream {
	protected InputStream in;
	
	protected FilterInputStream(InputStream in) {
		this.in = in;
	}
	
	public int read() throws IOException {
		return in.read();
	}
	
	public int read(byte[] bytes) throws IOException, NullPointerException {
		return in.read(bytes, 0, bytes.length);
	}
	
	public int read(byte[] bytes, int offset, int length) throws IOException, NullPointerException {
		return in.read(bytes, offset, length);
	}
	
	public long skip(long n) throws IOException {
		return in.skip(n);
	}
	
	public int available() throws IOException {
		return in.available();
	}
	
	public void close() throws IOException {
		in.close();
	}
	
	public void mark(int readlimit) {
		in.mark(readlimit);
	}
	
	public void reset() throws IOException {
		in.reset();
	}
	
	public boolean markSupported() {
		return in.markSupported();
	}
}
