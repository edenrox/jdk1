package alt.java.io;

public class BufferedOutputStream {
	private static final int DEFAULT_SIZE = 1024;
	
	private OutputStream out;
	protected byte[] buffer;
	protected int count;
	
	public BufferedOutputStream(OutputStream out) {
		this(out, DEFAULT_SIZE);
	}
	public BufferedOutputStream(OutputStream out, int size) {
		this.out = out;
		this.buffer = new byte[size];
		this.count = 0;
	}
	
	public void write(int b) throws IOException {
		if (count == buffer.length) {
			flush();
		}
		buffer[count] = (byte) b;
		count++;
	}
	
	public void write(byte[] buffer) throws IOException, NullPointerException {
		write(buffer, 0, buffer.length);
	}
	
	public void write(byte[] buffer, int offset, int length) throws IOException, NullPointerException {
		while (length > 0) {
			if (count == buffer.length) {
				flush();
			}
			int bytesToWrite = Math.min(length, buffer.length - count);
			System.arraycopy(buffer, offset, buffer, count, bytesToWrite);
			offset += bytesToWrite;
			length -= bytesToWrite;
		}
	}
	
	public void flush() throws IOException {
		out.write(buffer, 0, count);
		out.flush();
		count = 0;
	}
}
