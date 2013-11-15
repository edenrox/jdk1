package alt.java.lang;

import alt.java.lang.IndexOutOfBoundsException;

public class StringBuffer {
	private int size;
	private char[] buffer;
	
	public StringBuffer() {
		init(0);
	}
	
	public StringBuffer(int length) 
			throws NegativeArraySizeException {
		if (length < 0) {
			throw new NegativeArraySizeException("length cannot be negative");
		}
		init(length);
	}
	
	public StringBuffer(String str) {
		init(str.length());
		append(str);
	}
	
	private final void init(int length) {
		buffer = new char[length];
	}
	
	public int length() {
		return size;
	}
	
	public void setLength(int newLength)
			throws IndexOutOfBoundsException {
		int copySize = Math.min(newLength, size); 
		
		char[] oldBuffer = buffer;
		buffer = new char[newLength];
		System.arraycopy(oldBuffer, 0, buffer, 0, copySize);
	}
	
	public int capacity() {
		return buffer.length;
	}
	
	public void ensureCapacity(int minimumCapacity) {
		if (buffer.length < minimumCapacity) {
			setLength(minimumCapacity);
		}
	}
	
	public StringBuffer append(Object obj) {
		append(obj.toString());
		return this;
	}
	
	public StringBuffer append(String str) {
		ensureCapacity(size + str.length());
		str.getChars(0, str.length(), buffer, size);
		size += str.length();
		return this;
	}
	
	public StringBuffer append(char[] str)
			throws NullPointerException {
		if (str == null) {
			throw new NullPointerException("str cannot be null");
		}
		ensureCapacity(size + str.length);
		System.arraycopy(str, 0, buffer, size, str.length);
		size += str.length;
		return this;
	}
	
	public StringBuffer append(char[] str, int offset, int len) 
			throws NullPointerException, IndexOutOfBoundsException {
		if (str == null) {
			throw new NullPointerException("str cannot be null");
		}
		if (offset < 0) {
			throw new IndexOutOfBoundsException("offset must be positive");
		}
		if (offset > str.length) {
			throw new IndexOutOfBoundsException("offset is greater than length");
		}
		if (offset + len > str.length) {
			throw new IndexOutOfBoundsException("offset and length exceed array bound");
		}
		ensureCapacity(size + len);
		System.arraycopy(str, offset, buffer, size, len);
		size += len;
		return this;
	}
	
	public StringBuffer append(boolean b) {
		if (b) {
			return append("true");
		} else {
			return append("false");
		}
	}
	
	public StringBuffer append(char c) {
		ensureCapacity(size + 1);
		buffer[size] = c;
		size++;
		return this;
	}
	
	public StringBuffer append(int i) {
		return append(Integer.toString(i));
	}
	
	
	public StringBuffer reverse() {
		int max = size / 2;
		for(int i = 0; i < max; i++) {
			int j = size - i;
			char tmp = buffer[i];
			buffer[i] = buffer[j];
			buffer[j] = tmp;
		}
		return this;
	}
	
}
