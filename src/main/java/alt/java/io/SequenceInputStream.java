package alt.java.io;

import alt.java.util.Enumeration;
import alt.java.util.ObjectArrayEnumeration;

public class SequenceInputStream extends InputStream {
	
	private Enumeration streams;
	private InputStream cur;
	
	public SequenceInputStream(Enumeration e) {
		streams = e;
	}
	
	public SequenceInputStream(InputStream s1, InputStream s2) {
		streams = new ObjectArrayEnumeration(new Object[] {s1, s2});
	}
	
	public int read() throws IOException {
		while (cur == null) {
			if (streams.hasMoreElements()) {
				cur = (InputStream) streams.nextElement();
			} else {
				return -1; // EOF
			}
		}
		int rv = cur.read();
		while ((rv == -1) && (streams.hasMoreElements())) {
			cur = (InputStream) streams.nextElement();
			rv = cur.read();
		}
		return rv;
	}
	
	public int read(byte[] buffer, int offset, int length)
			throws IOException, NullPointerException, IndexOutOfBoundsException {
		while (cur == null) {
			if (streams.hasMoreElements()) {
				cur = (InputStream) streams.nextElement();
			} else {
				return -1; // EOF
			}
		}
		int rv = cur.read(buffer, offset, length);
		while ((rv == -1) && (streams.hasMoreElements())) {
			cur = (InputStream) streams.nextElement();
			rv = cur.read();
		}
		return rv;
	}
	
	public void close() throws IOException {
		while (streams.hasMoreElements()) {
			InputStream stream = (InputStream) streams.nextElement();
			stream.close();
		}
	}
}
