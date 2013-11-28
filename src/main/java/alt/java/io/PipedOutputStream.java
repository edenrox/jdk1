package alt.java.io;

public class PipedOutputStream extends OutputStream {

	private PipedInputStream dest;
	
	public PipedOutputStream(PipedInputStream snk) throws IOException {
		connect(snk);
	}
	
	public PipedOutputStream() {
		// noop
	}
	
	public void connect(PipedInputStream snk) throws IOException {
		dest = snk;
	}
	
	public void write(int b) throws IOException {
		if (dest == null) {
			throw new IOException("PipedOutputStream is closed or not connected");
		}
		dest.receive(b);
	}
	
	public void write(byte bytes[], int offset, int length) throws IOException {
		// Have the parent handle this
		super.write(bytes, offset, length);
	}
	
	public void flush() throws IOException {
		if (dest == null) {
			throw new IOException("PipedOutputStream is closed or not connected");
		}
		synchronized (dest) {
			dest.notify();
		}
	}
	
	public void close() throws IOException {
		if (dest == null) {
			throw new IOException("Pipe is closed or not connected");
		}
		flush();
		dest.close();
		dest = null;
	}

}
