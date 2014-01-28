package alt.java.io;

public class RandomAccessFile
	implements DataOutput, DataInput {
	
	public RandomAccessFile(File file, String mode) {
		
	}
	
	public RandomAccessFile(String name, String mode) {
		this(new File(name), mode);
	}
	
	public void close() {
		
	}
	
	public final FileDescriptor getFD() throws IOException {
		throw new RuntimeException("Not implemented");
	}
	
	public long getFilePointer() throws IOException {
		throw new RuntimeException("Not implemented");
	}
	
	public long length() throws IOException {
		throw new RuntimeException("Not implemented");
	}
	
	public int read() throws IOException {
		throw new RuntimeException("Not implemented");
	}
	
	public int read(byte bytes[]) throws IOException {
		return read(bytes, 0, bytes.length);
	}
	
	public int read(byte bytes[], int offset, int length) throws IOException {
		throw new RuntimeException("Not implemented");
	}
	
	public final boolean readBoolean() throws IOException {
		if (read() == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public final byte readByte() throws IOException {
		return (byte) read();
	}
	
	public final char readChar() throws IOException {
		int b1 = read();
		int b2 = read();
		return (char)((b1 << 8) | b2);
	}
	public final double readDouble() throws IOException {
		return Double.longBitsToDouble(readLong());
	}
	public final float readFloat() throws IOException {
		return Float.intBitsToFloat(readInt());
	}
	public final void readFully(byte bytes[]) throws IOException {
		readFully(bytes, 0, bytes.length);
	}
	public final void readFuly(byte bytes[], int offset, int length) throws IOException {
		
	}
	public final int readInt() throws IOException {
		int b1 = read();
		int b2 = read();
		int b3 = read();
		int b4 = read();
		return (b1<<24) | (b2<<16) + (b3<<8) + b4;
	}
	public final String readLine() throws IOException {
		throw new RuntimeException("Not implemented");
	}
	public final long readLong() throws IOException {
		long b1 = read();
		long b2 = read();
		long b3 = read();
		long b4 = read();
		long b5 = read();
		long b6 = read();
		long b7 = read();
		long b8 = read();
		return (b1 << 56) + (b2 << 48) + (b3 << 40) + (b4 << 32)
				+ (b5 << 24) + (b6 << 16) + (b7 << 8) + b8;
	}
	public final short readShort() throws IOException {
		int b1 = read();
		int b2 = read();
		return (short) ((b1 << 8) | b2);
	}
	public final int readUnsignedByte() throws IOException {
		throw new RuntimeException("Not implemented");
	}
	public final int readUnsignedShort() throws IOException {
		throw new RuntimeException("Not implemented");
	}
	public final String readUTF() throws IOException {
		throw new RuntimeException("Not implemented");
	}
	public void seek(long pos) throws IOException {
		
	}
	public void skipBytes(int n) throws IOException {
		throw new RuntimeException("Not implemented");
	}
	public void write(byte bytes[]) throws IOException {
		write(bytes, 0, bytes.length);
	}
	public void write(byte bytes[], int offset, int length) throws IOException {
		
	}
	public void write(int b) throws IOException {
		
	}
	public final void writeBoolean(boolean v) throws IOException {
		if (v) {
			write(1);
		} else {
			write(0);
		}
	}
	public final void writeByte(int v) throws IOException {
		write(v);
	}
	public final void writeBytes(String s) throws IOException {
		write(s.getBytes());
	}
	public final void writeChar(int v) throws IOException {
		write((v >> 8) & 0xFF);
		write(v & 0xFF);
	}
	public final void writeDouble(double d) throws IOException {
		writeLong(Double.doubleToLongBits(d));
	}
	public final void writeFloat(float f) throws IOException {
		writeInt(Float.floatToIntBits(f));
	}
	public final void writeInt(int i) throws IOException {
		write((i >>> 24) & 0xFF);
		write((i >> 16) & 0xFF);
		write((i >> 8) & 0xFF);
		write(i & 0xFF);
	}
	public final void writeLong(long v) throws IOException {
		write((int) (v >> 56) & 0xFF);
		write((int) (v >> 48) & 0xFF);
		write((int) (v >> 40) & 0xFF);
		write((int) (v >> 32) & 0xFF);
		write((int) (v >> 24) & 0xFF);
		write((int) (v >> 16) & 0xFF);
		write((int) (v >> 8) & 0xFF);
		write((int) v & 0xFF);
	}
	public final void writeShort(int i) throws IOException {
		write((i >> 8) & 0xFF);
		write(i & 0xFF);
	}
	public final void writeUTF(String str) throws IOException {
		
	}

	@Override
	public void readFully(byte[] b, int off, int len) throws IOException,
			NullPointerException, IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeChars(String s) throws IOException, NullPointerException {
		// TODO Auto-generated method stub
		
	}
}
