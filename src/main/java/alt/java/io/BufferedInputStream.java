package alt.java.io;


public class BufferedInputStream extends FilterInputStream {
	private static final int DEFAULT_BUFFER_SIZE = 4096;
	
	protected byte[] buf;
	protected int count = 0;
	protected int pos = 0;
	protected int markpos = -1;
	protected int marklimit = 0;
	
	public BufferedInputStream(InputStream in) {
		this(in, DEFAULT_BUFFER_SIZE);
	}
	
	public BufferedInputStream(InputStream in, int size) {
		super(in);		
		buf = new byte[size];
	}
	
	private int readToBuffer() throws IOException {
		count = 0;
		pos = 0;
		int numRead = super.read(buf, 0, buf.length);
		if (numRead > 0) {
			count += numRead;
		}
		return numRead;
	}
	
	public synchronized int read() throws IOException {
		if (pos == count) {
			if (readToBuffer() < 1) {
				return -1;
			}
		}
		int rv = buf[pos];
		pos++;
		return rv;
	}
	
	public int read(byte[] bytes) throws IOException {
		return read(bytes, 0, bytes.length);
	}
	
	public int read(byte[] bytes, int offset, int length) throws IOException {
		if (pos == count) {
			int numRead = readToBuffer();
			if (numRead < 1) {
				return numRead;
			}
		}
		
		int numRead = Math.min(count - pos, length);
		System.arraycopy(buf, pos, bytes, offset, numRead);
		pos += numRead;
		return numRead;
	}
	
	public int available()  {
		return (count - pos);
	}
}
