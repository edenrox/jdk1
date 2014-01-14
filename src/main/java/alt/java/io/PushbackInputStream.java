package alt.java.io;

public class PushbackInputStream extends FilterInputStream {

	protected int pushBack;
	
	public PushbackInputStream(InputStream in) {
		super(in);
		pushBack = -1;
	}
	
	public int available() throws IOException {
		int numAvailable = available();
		if (pushBack > -1) {
			if (numAvailable == -1) {
				numAvailable = 1;
			} else {
				numAvailable += 1;
			}
		}
		return numAvailable;
	}
	
	public boolean markSupported() {
		return false;
	}
	
	public int read() throws IOException {
		int rv;
		if (pushBack > -1) {
			rv = pushBack;
			pushBack = -1;
		} else {
			rv = in.read();
		}
		return rv;
	}
	
	public int read(byte bytes[], int offset, int length) throws IOException {
		if (length < 0) {
			throw new IllegalArgumentException("length must be positive");
		}
		int numRead = 0;
		if ((pushBack > -1) && (length > 0)) {
			bytes[offset] = (byte) pushBack;
			pushBack = -1;
			offset++;
			length--;
			numRead++;
		}
		numRead += in.read(bytes, offset, length);
		
		return numRead;
	}
	
	public void unread(int ch) throws IOException {
		if (pushBack > -1) {
			throw new IOException("Can only push back one character at a time");
		}
		pushBack = ch;
	}
	
}
