package alt.java.lang;

import java.lang.*;

public class StringBuffer {
	private int size;
	private char[] buffer;
	
	public StringBuffer() {
		init(0);
	}
	
	public StringBuffer(int length) throws NegativeArraySizeException {
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
	
	
	
	public StringBuffer append(String str) {
		ensureCapacity(size + str.length());
		str.getChars(0, str.length(), buffer, size);
		size += str.length();
		return this;
	}
	public StringBuffer append(boolean b) {
		return append(String.valueOf(b));
	}
	public StringBuffer append(char c) {
		ensureCapacity(size + 1);
		buffer[size] = c;
		size++;
		return this;
	}
	public StringBuffer append(char[] str)
			throws NullPointerException {
		ensureCapacity(size + str.length);
		System.arraycopy(str, 0, buffer, size, str.length);
		size += str.length;
		return this;
	}
	public StringBuffer append(char[] str, int offset, int len) 
			throws NullPointerException, IndexOutOfBoundsException {
		ensureCapacity(size + len);
		System.arraycopy(str, offset, buffer, size, len);
		size += len;
		return this;
	}
	public StringBuffer append(double d) {
		return append(String.valueOf(d));
	}
	public StringBuffer append(float f) {
		return append(String.valueOf(f));
	}
	public StringBuffer append(int i) {
		return append(String.valueOf(i));
	}
	public StringBuffer append(long l) {
		return append(String.valueOf(l));
	}
	public StringBuffer append(Object obj) {
		return append(String.valueOf(obj));
	}
	
	
	public StringBuffer insert(int offset, String str) {
		if (offset > size) {
			throw new IndexOutOfBoundsException();
		}
		ensureCapacity(size + str.length());
		
		// move the old chars after the insertion point
		System.arraycopy(buffer, offset, buffer, offset + str.length(), size - offset);
		
		// move the new chars into the insertion point
		str.getChars(offset, str.length(), buffer, offset);
		
		return this;
	}
	public StringBuffer insert(int offset, boolean b) {
		return insert(offset, String.valueOf(b));
	}
	public StringBuffer insert(int offset, char c) {
		return insert(offset, String.valueOf(c));
	}
	public StringBuffer insert(int offset, char str[]) {
		return insert(offset, String.valueOf(str));
	}
	public StringBuffer insert(int offset, double d) {
		return insert(offset, String.valueOf(d));
	}
	public StringBuffer insert(int offset, float f) {
		return insert(offset, String.valueOf(f));
	}
	public StringBuffer insert(int offset, int i) {
		return insert(offset, String.valueOf(i));
	}
	public StringBuffer insert(int offset, long l) {
		return insert(offset, String.valueOf(l));
	}
	public StringBuffer insert(int offset, Object o) {
		return insert(offset, String.valueOf(o));
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
	
	public char charAt(int index) {
		if (index >= size) {
			throw new IndexOutOfBoundsException("index is beyond length of string buffer");
		}
		return buffer[index];
	}
	
	public void setCharAt(int index, char ch) {
		if (index >= size) {
			throw new IndexOutOfBoundsException("index is beyond length of string buffer");
		}
		buffer[index] = ch;
	}
	
	public void getChars(int srcBegin, int srcEnd, char dst[], int dstOffset) {
		int length = srcEnd - srcBegin;
		if (srcEnd >= size) {
			throw new IndexOutOfBoundsException("srcEnd is beyond the length of string buffer");
		}
		System.arraycopy(buffer, srcBegin, dst, dstOffset, length);
	}
	
	
	public String toString() {
		return new String(buffer, 0, size);
	}
}
