package alt.java.io;

public interface DataInput {
	public void readFully(byte[] b) throws IOException, NullPointerException;
	public void readFully(byte[] b, int off, int len) throws IOException, NullPointerException, IndexOutOfBoundsException;
	public void skipBytes(int in) throws IOException;
	public boolean readBoolean() throws IOException;
	public byte readByte() throws IOException;
	public short readShort() throws IOException;
	public int readUnsignedShort() throws IOException;
	public char readChar() throws IOException;
	public int readInt() throws IOException;
	public long readLong() throws IOException;
	public float readFloat() throws IOException;
	public double readDouble() throws IOException;
	public String readLine() throws IOException;
	public String readUTF() throws IOException;
}
