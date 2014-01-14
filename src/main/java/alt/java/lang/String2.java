package alt.java.lang;

import java.util.Hashtable;

import alt.java.lang.Character;
import alt.java.lang.IndexOutOfBoundsException;

public class String2 {

	private static final int INDEX_NOT_FOUND = -1;
	private static final char[] EMPTY_BUFFER = new char[0];
	private static final char SPACE_CHAR = ' ';
	private static final String2 EMPTY_STRING = new String2();
	
	private static Hashtable internedStrings;

	protected int offset;
	protected int size;
	protected char[] buffer;

	public String2() {
		
		// Create string that points to the empty buffer
		buffer = EMPTY_BUFFER;
		offset = 0;
		size = 0;
	}

	public String2(String2 value) throws NullPointerException {
		if (value == null) {
			throw new NullPointerException("value cannot be null");
		}
		
		// Create a string that points to the underlying data of the other string
		this.buffer = value.buffer;
		this.offset = value.offset;
		this.size = value.size;
	}
	public String2(char[] value) throws NullPointerException {
		this(value, 0, value.length);
		
	}
	public String2(char[] value, int offset, int length) throws NullPointerException {
		if (value == null) {
			throw new NullPointerException("value cannot be null");
		}
		
		// Declare a new buffer
		this.buffer = new char[length];
		this.offset = 0;
		this.size = length;
		
		// Copy the characters into the new buffer
		System.arraycopy(value, offset, this.buffer, 0, length);
	}

	public int hashCode() {
		return 0;
	}

	public int length() {
		return size;
	}
	
	public char charAt(int index) throws NullPointerException, IndexOutOfBoundsException {
		if ((index < 0) || (index >= size)) {
			throw new IndexOutOfBoundsException();
		}
		return buffer[offset + index];
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

		System.arraycopy(buffer, offset + srcBegin, dst, dstBegin, srcEnd - srcBegin);
	}
	
	public char[] toCharArray() {
		char[] rv = new char[size];
		System.arraycopy(buffer, offset, rv, 0, size);
		return rv;
	}
	
	public void getBytes(int srcBegin, int srcEnd, byte dst[], int dstBegin) {
		// TODO: implement this
	}
	
	public int indexOf(int ch) {
		return indexOf(ch, 0);
	}
	
	public int indexOf(int ch, int fromIndex) {
		fromIndex = Math.max(0,  fromIndex);
		fromIndex = Math.min(fromIndex, size);
		for(int i = fromIndex; i < length(); i++) {
			if (charAt(i) == ch) {
				return 	i;
			}
		}
		return INDEX_NOT_FOUND;
	}
	
	public int indexOf(String2 str)
			throws NullPointerException {
		return indexOf(str, 0);
	}
	
	public int indexOf(String2 str, int fromIndex) 
			throws NullPointerException {
		if (str == null) {
			throw new NullPointerException("str cannot be null");
		}
		fromIndex = Math.max(0,  fromIndex);
		fromIndex = Math.min(fromIndex, size);
		for(int i = offset + fromIndex; i < size - str.length(); i++) {
			if (regionMatches(i, str, 0, str.length())) {
				return i;
			}
		}
		return INDEX_NOT_FOUND;
	}
	
	public int lastIndexOf(char c) {
		return lastIndexOf(c, length() - 1);
	}
	
	public int lastIndexOf(char c, int fromIndex) {
		fromIndex = Math.max(0,  fromIndex);
		fromIndex = Math.min(fromIndex, size);
		for(int i = fromIndex; i >= 0; i--) {
			if (charAt(i) == c) {
				return i;
			}
		}
		return INDEX_NOT_FOUND;
	}
	
	public int lastIndexOf(String2 str)
			throws NullPointerException {
		return lastIndexOf(str, length() - 1);
	}
	
	public int lastIndexOf(String2 str, int fromIndex) 
			throws NullPointerException {
		if (str == null) {
			throw new NullPointerException();
		}
		fromIndex = Math.max(0,  fromIndex);
		fromIndex = Math.min(fromIndex, size);
		for(int i = fromIndex; i >= 0; i--) {
			if (regionMatches(i, str, 0, str.length())) {
				return i;
			}
		}
		return INDEX_NOT_FOUND;
	}
	
	
	public String2 substring(int beginIndex)
			throws IndexOutOfBoundsException {
		int endIndex = length() - beginIndex;
		return substring(beginIndex, endIndex);
	}
	
	public String2 substring(int beginIndex, int endIndex)
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
		return new String2(buffer, beginIndex, newLength);
	}
		
	
	public String2 concat(String2 str) 
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
		System.arraycopy(buffer, offset, buffer, 0, size);
		System.arraycopy(str.buffer, str.offset, buffer, this.size, str.size);
		
		// Create a new string object with the new buffer
		return new String2(buffer);
	}
	
	public String2 replace(char oldChar, char newChar) {
	
		// Allocate a buffer for the new string
		char[] buffer = new char[this.length()];
		
		// Copy the string into the new buffer
		System.arraycopy(buffer, offset, buffer, 0, size);
		
		// Replace characters in the new buffer
		for(int i = 0; i < buffer.length; i++) {
			if (buffer[i] == oldChar) {
				buffer[i] = newChar;
			}
		}
		
		// Create a new string object with the new buffer
		return new String2(buffer);
	}
	
	public String2 trim() {
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
			return new String2(buffer, startIndex, endIndex - startIndex);
		}
	}
	
	public boolean equals(Object that) {
		if ((that == null) || !(that instanceof String2)) {
			return false;
		}
		return equals((String2) that, false);
	}
	
	public boolean equalsIgnoreCase(String2 that) {
		return equals(that, true);
	}
	
	public boolean equals(String2 that, boolean ignoreCase) {
		if (that == null) {
			return false;
		}
		if (this.size != that.size) {
			return false;
		}
		if (ignoreCase) {
			for (int i = 0; i < size; i++) {
				if (Character.toLowerCase(charAt(i)) != Character.toLowerCase(that.charAt(i))) {
					return false;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (charAt(i) != that.charAt(i)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public int compareTo(String anotherString) throws NullPointerException {
		if (anotherString == null) {
			throw new NullPointerException("cannot compare to null");
		}
		int max = Math.min(this.length(), anotherString.length());
		for(int i = 0; i < max; i++) {
			int diff = this.charAt(i) - anotherString.charAt(i);
			if (diff != 0) {
				// Difference in characters, so return it
				return diff;
			}
		}
		
		// No difference in characters, so order by length
		return this.length() - anotherString.length();
	}
	
	public static final String2 fromString(String that) {
		return new String2(that.toCharArray());
	}
	
	public String toString() {
		return new String(buffer, offset, size);
	}
	
	public boolean regionMatches(int thisOffset, String2 other, int otherOffset, int length) {
		return regionMatches(false, thisOffset, other, otherOffset, length);
	}
	
	public boolean regionMatches(boolean ignoreCase, int thisOffset, String2 other, int otherOffset, int length) {
		if (thisOffset+length > length()) {
			throw new IndexOutOfBoundsException();
		}
		if (otherOffset+length > other.length()) {
			throw new IndexOutOfBoundsException();
		}
		for(int i = 0; i < length; i++) {
			if (ignoreCase) {
				if (Character.toLowerCase(charAt(thisOffset + i)) != Character.toLowerCase(other.charAt(otherOffset + i))) {
					return false;
				}
			} else {
				if (charAt(thisOffset + i) != other.charAt(otherOffset + i)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean startsWith(String2 prefix) throws NullPointerException {
		return startsWith(prefix, prefix.length());
	}
	
	public boolean startsWith(String2 prefix, int toLength) throws NullPointerException {
		return regionMatches(0, prefix, 0, toLength);
	}
	
	public boolean endsWith(String2 suffix) throws NullPointerException {
		return regionMatches(length() - suffix.length(), suffix, 0, suffix.length());
	}
	
	public String2 intern() {
		if (internedStrings == null) {
			internedStrings = new Hashtable();
		}
		if (!internedStrings.containsKey(this)) {
			internedStrings.put(this, this);
		}
		return (String2) internedStrings.get(this);
	}
}