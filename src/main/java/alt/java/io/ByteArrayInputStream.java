package alt.java.io;

public class ByteArrayInputStream extends InputStream {
	
	protected byte[] buff;
	protected int pos;
	protected int count;
	
	public ByteArrayInputStream(byte[] buf) {
		this(buf, 0, buf.length);
	}
	
	public ByteArrayInputStream(byte[] buf, int offset, int length) {
		buff = buf;
		pos = offset;
		count = length;
	}
	
	public int read() throws NullPointerException, IndexOutOfBoundsException {
		if (pos == count) {
			return -1;
		} else {
			int rv = (buff[pos] & 0xff);
			pos++;
			return rv;
		}
	}
	
	public int read(byte[] bytes, int offset, int length) {
		int toRead = Math.min(length, available());
		if (toRead > 0) {
			System.arraycopy(buff, pos, bytes, offset, toRead);
			pos += toRead;
			return toRead;
		} else {
			return -1;
		}
		
	}
	
	public long skip(long n) {
		int toSkip = (int) Math.min(available(), n);
		pos += toSkip;
		return toSkip;
	}
	
	public int available() {
		return count - pos;
	}
	
	public void reset() {
		pos = 0;
	}
}
