package alt.java.io;

public class PipedInputStream extends InputStream {
	
	protected static int PIPE_SIZE = 1024;
	
	protected byte[] buffer;
	protected int in;
	protected int out;
	private boolean isOpen; 
	
	public PipedInputStream() {
		in = -1;
		isOpen = false;
		buffer = new byte[PIPE_SIZE];
	}

	public PipedInputStream(PipedOutputStream src) throws IOException {
		this();
		connect(src);
	}

	public void connect(PipedOutputStream src) throws IOException {
		src.connect(this);
		in = 0;
		out = 0;
		isOpen = true;
	}
	
	protected synchronized void receive(int b) throws IOException {
		if (!isOpen) {
			throw new IOException("PipedInputStream is closed or not connected");
		}
		buffer[out] = (byte) b;
		out = (out + 1) % buffer.length;
	}
	
	public synchronized int read() throws IOException {
		if (in == -1) {
			throw new IOException("PipedInputStream is not connected");
		}
		if ((available() == 0) && (!isOpen)) {
			return -1;
		}
		waitForBytes();
		int rv = buffer[in];
		in = (in + 1) % buffer.length;
		return rv;
	}
	
	public synchronized int read(byte[] bytes, int offset, int length) 
			throws IOException, NullPointerException, IndexOutOfBoundsException {
		if (in == -1) {
			throw new IOException("PipedInputStream is not connected");
		}
		if ((available() == 0) && (!isOpen)) {
			return -1;
		}
		waitForBytes();
		int numBytes = Math.min(length, available());
		if (in < out) {
			System.arraycopy(buffer, in, bytes, offset, numBytes);
			in += numBytes;
		} else {
			// Copy to the end of the buffer
			int firstCopy = Math.min(numBytes, buffer.length - in);
			System.arraycopy(buffer, in, bytes, offset, firstCopy);
			
			// Copy from the beginning of the buffer
			int secondCopy = Math.min(numBytes - firstCopy, out);
			System.arraycopy(buffer, 0, bytes, offset + firstCopy, secondCopy);
			in = (in + numBytes) % buffer.length;
		}
		
		return numBytes;
	}
	
	private synchronized void waitForBytes() throws IOException {
		while (available() == 0) {
			try {
				this.wait();
			} catch(InterruptedException ex) {
				// noop
			}
		}
	}
	
	public synchronized int available() throws IOException {
		if (in == -1) {
			throw new IOException("PipedInputStream is not connected");
		}
		if (out > in) {
			return (out - in);
		} else {
			return out + (buffer.length - in);
		}
	}
	
	public synchronized void close() throws IOException {
		if (!isOpen) {
			throw new IOException("PipedInputStream is already closed");
		}
		isOpen = false;
	}
	
}
