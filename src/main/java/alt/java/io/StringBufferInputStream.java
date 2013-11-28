package alt.java.io;

public class StringBufferInputStream extends InputStream {

	protected String buffer;
	protected int pos;
	protected int count;
	
	public StringBufferInputStream(String str) throws NullPointerException {
		buffer = str;
		pos = 0;
		count = str.length();
	}
	
	public int read() {
		if (pos == count) {
			return -1;
		} else {
			int rv = (buffer.charAt(pos) & 0xff);
			pos++;
			return rv;
		}
	}
	
	public int read(byte[] bytes, int offset, int length) {
		int toRead = Math.min(length, available());
		if (toRead > 0) {
			for(int i = 0; i < toRead; i++) {
				bytes[offset + i] = (byte) (buffer.charAt(pos + i) & 0xff);
			}
			pos += toRead;
			return toRead;
		} else {
			return -1;
		}
	}
	
	public long skip(long n) {
		int toSkip = (int) Math.min(n, available());
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
