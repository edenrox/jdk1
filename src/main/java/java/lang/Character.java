package java.lang;

public class Character {
	public static final char MIN_VALUE = '\u0000';
	public static final char MAX_VALUE = '\uffff';
	
	public static final int MIN_RADIX = 2;
	public static final int MAX_RADIX = 36;
	
	private char value;
	
	public Character(char value) {
		this.value = value;
	}
	
	public String toString() {
		return new String(new char[] {value});
	}
	
	public boolean equals(Object obj) {
		if ((obj != null) && (obj instanceof Character)) {
			Character that = (Character) obj;
			if (that.value == this.value) {
				return true;
			}
		}
		return false;
	}
	
	public int hashCode() {
		return (int) value;
	}
	
	public char charValue() {
		return value;
	}
}
