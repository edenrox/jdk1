package java.lang;

public class String {

	private static final int INDEX_NOT_FOUND = -1;
	private static final char[] EMPTY_BUFFER = new char[0];
	private static final char SPACE_CHAR = ' ';
	private static final String EMPTY_STRING = "";

	protected int offset;
	protected int size;
	protected char[] value;

	public String() {
		init(EMPTY_BUFFER, 0, 0);
	}

	public String(String value) 
			throws NullPointerException {
		if (value == null) {
			throw new NullPointerException("value cannot be null");
		}
		init(value.value, value.offset, value.size);
	}
	public String(char[] value) 
			throws NullPointerException {
		if (value == null) {
			throw new NullPointerException("value cannot be null");
		}
		init(value, 0, value.length);
	}
	public String(char[] value, int offset, int count) 
			throws NullPointerException {
		if (value == null) {
			throw new NullPointerException("value cannot be null");
		}
		init(value, offset, count);
	}
	
	protected final void init(char[] value, int offset, int count) {
		this.offset = offset;
		this.size = count;
		this.value = value;
	}
	


	public int hashCode() {
		return 0;
	}

	public int length() {
		return size;
	}
	
	public char charAt(int index)
			throws NullPointerException, IndexOutOfBoundsException {
		if ((index < 0) || (index >= size)) {
			throw new IndexOutOfBoundsException();
		}
		return value[offset + index];
	}
	
	public void getChars(int srcBegin, int srcEnd, char dst[], int dstBegin)
			throws NullPointerException, IndexOutOfBoundsException {
		if (dst == null) {
			throw new NullPointerException("dst cannot be null");
		}
		
		if ((srcBegin < 0) 
			|| (srcBegin > srcEnd)
			|| (srcEnd > size)
			|| (dstBegin < 0)
			|| (dstBegin + (srcEnd - srcBegin) > size)) {
			throw new IndexOutOfBoundsException();
		}

		System.arraycopy(value, offset + srcBegin, dst, dstBegin, srcEnd - srcBegin);
	}
	
	public int indexOf(int ch) {
		return indexOf(ch, 0);
	}
	
	public int indexOf(int ch, int fromIndex) {
		for(int i = fromIndex; i < length(); i++) {
			if (charAt(i) == ch) {
				return 	i;
			}
		}
		return INDEX_NOT_FOUND;
	}
	
	public int indexOf(String str)
			throws NullPointerException {
		return indexOf(str, 0);
	}
	
	public int indexOf(String str, int fromIndex) 
			throws NullPointerException {
		if (str == null) {
			throw new NullPointerException("str cannot be null");
		}
		for(int i = offset + fromIndex; i < size - str.length(); i++) {
			
		}
		return INDEX_NOT_FOUND;
	}
	
	public int lastIndexOf(char c) {
		return lastIndexOf(c, length() - 1);
	}
	
	public int lastIndexOf(char c, int fromIndex) {
		for(int i = fromIndex; i >= 0; i--) {
			if (charAt(i) == c) {
				return i;
			}
		}
		return INDEX_NOT_FOUND;
	}
	
	public int lastIndexOf(String str)
			throws NullPointerException {
		return lastIndexOf(str, length() - 1);
	}
	
	public int lastIndexOf(String str, int fromIndex) 
			throws NullPointerException {
		if (str == null) {
			throw new NullPointerException();
		}
		for(int i = fromIndex; i >= 0; i--) {
			
		}
		return INDEX_NOT_FOUND;
	}
	
	
	public String substring(int beginIndex)
			throws IndexOutOfBoundsException {
		int endIndex = length() - beginIndex;
		return substring(beginIndex, endIndex);
	}
	
	public String substring(int beginIndex, int endIndex)
			throws IndexOutOfBoundsException {
		if (beginIndex < 0) {
			throw new IndexOutOfBoundsException("beginIndex cannot be negative");
		}
		if (beginIndex > length()) {
			throw new IndexOutOfBoundsException("beginIndex cannot be greater than length");
		}
		if (endIndex > length()) {
			throw new IndexOutOfBoundsException("endIndex cannot be greater than length");
		}
		if (beginIndex == length()) {
			return EMPTY_STRING;
		}
		
		int newLength = endIndex - beginIndex;
		return new String(value, beginIndex, newLength);
	}
		
	
	public String concat(String str) 
			throws NullPointerException {
		if (str == null) {
			throw new NullPointerException();
		}
		if (str.length() == 0) {
			return this;
		}
		
		// Allocate a buffer for the new string
		char[] buffer = new char[this.length() + str.length()];
		
		// Copy the old strings into the new buffer
		System.arraycopy(value, offset, buffer, 0, size);
		System.arraycopy(str.value, str.offset, buffer, this.size, str.size);
		
		// Create a new string object with the new buffer
		return new String(buffer);
	}
	
	public String replace(char oldChar, char newChar) {
	
		// Allocate a buffer for the new string
		char[] buffer = new char[this.length()];
		
		// Copy the string into the new buffer
		System.arraycopy(value, offset, buffer, 0, size);
		
		// Replace characters in the new buffer
		for(int i = 0; i < buffer.length; i++) {
			if (value[i] == oldChar) {
				value[i] = newChar;
			}
		}
		
		// Create a new string object with the new buffer
		return new String(buffer);
	}
	
	public String trim() {
		if (size == 0) {
			// can't trim an empty string
			return this;
		}
		
		int startIndex = 0;
		int endIndex = size - 1;
		
		if ((charAt(startIndex) > SPACE_CHAR) && (charAt(endIndex) > SPACE_CHAR)) {
			// the string doesn't need to be trimmed
			return this;
		}
		
		// Trim the front
		while ((charAt(startIndex) <= SPACE_CHAR) && (startIndex < endIndex)) {
			startIndex++;
		}
		
		// Trim the back
		while (charAt(endIndex) <= SPACE_CHAR) {
			endIndex--;
		}
		
		if (startIndex == endIndex) {
			// The string was all whitespace
			return EMPTY_STRING;
		} else {
			// Return the trimmed string
			return new String(value, startIndex, endIndex - startIndex);
		}
	}
}