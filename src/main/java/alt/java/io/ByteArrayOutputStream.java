package alt.java.io;

public class ByteArrayOutputStream extends OutputStream {
	
	protected byte[] buff;
	protected int count;
	
	public ByteArrayOutputStream() {
		this(0);
	}
	
	public ByteArrayOutputStream(int size) {
		buff = new byte[size];
		count = 0;
	}
	
	public void write(int b) {
		ensureCapacity(count + 1);
		
		count++;
		buff[count] = (byte) (0xff & b);
	}
	public void write(byte[] bytes, int offset, int length)
			throws NullPointerException, IndexOutOfBoundsException {
		ensureCapacity(count + length);
		
		System.arraycopy(bytes, offset, buff, count, length);
		count += length;
	}
	
	public int size() {
		return count;
	}
	
	public void reset() {
		count = 0;
	}
	
	public byte[] toByteArray() {
		byte[] rv = new byte[count];
		System.arraycopy(buff, 0, rv, 0, count);
		return rv;
	}
	
	public String toString() {
		return toString(0);
	}
	public String toString(int hibyte) {
		int hibytemask = ((hibyte & 0xff) << 8);
		char[] chars = new char[count];
		for(int i = 0; i < count; i++) {
			chars[i] = (char) (hibytemask | (buff[i] & 0xff));
		}
		return new String(chars);
	}

	public void writeTo(OutputStream out)
			throws IOException {
		out.write(buff, 0, count);
	}

	private void ensureCapacity(int capacity) {
		if (buff.length < capacity) {
			byte[] oldBuff = buff;
			buff = new byte[capacity];
			System.arraycopy(oldBuff, 0, buff, 0, count);
		}
	}
	
}
