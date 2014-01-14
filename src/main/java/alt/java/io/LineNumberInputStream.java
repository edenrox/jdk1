package alt.java.io;

public class LineNumberInputStream extends FilterInputStream {

	private int lineNumber;
	private int markLineNumber;
	
	public LineNumberInputStream(InputStream in) {
		super(in);
		lineNumber = 0;
		markLineNumber = 0;
	}
	
	public int read() throws IOException {
		int rv = super.read();
		if (isNewlineByte((byte) rv)) {
			lineNumber++;
		}
		return rv;
	}
	
	public int read(byte[] bytes)
			throws IOException, NullPointerException {
		int rv = super.read(bytes);
		incrementLines(bytes, 0, rv);
		return rv;
	}
	
	public int read(byte[] bytes, int offset, int length)
			throws IOException, NullPointerException, IndexOutOfBoundsException{
		int rv = super.read(bytes, offset, length);
		incrementLines(bytes, offset, rv);
		return rv;
	}
	
	private boolean isNewlineByte(byte b) {
		return ((b == '\n') || (b == '\r'));
	}
	
	
	private void incrementLines(byte[] bytes, int offset, int numRead) {
		for(int i = offset; i < numRead; i++) {
			if (isNewlineByte(bytes[i])) {
				lineNumber++;
			}
		}
	}
	
	
	public void mark(int readLimit) {
		markLineNumber = lineNumber;
		super.mark(readLimit);
	}
	
	public void reset() throws IOException {
		lineNumber = markLineNumber;
		super.reset();
	}
	
	public int getLineNumber() {
		return lineNumber;
	}
	
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
}
