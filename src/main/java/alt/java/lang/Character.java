package alt.java.lang;

import java.lang.Object;
import java.lang.String;

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
	
	
	
	
	
	
	
	
	
	
	
	public static boolean isDefined(char c) {
		return false;
	}
	public static boolean isLowerCase(char c) {
		return ((c >= 'a') && (c <= 'z'));
	}
	public static boolean isUpperCase(char c) {
		return ((c >= 'A') && (c <= 'Z'));
	}
	public static boolean isTitleCase(char c) {
		return false;
	}
	public static boolean isDigit(char c) {
		return ((c >= '0') && (c <= '9'));
	}
	
	public static boolean isLetter(char c) {
		return (((c >= 'a') && (c <= 'z'))
				|| ((c >= 'A') && (c <= 'Z')));
	}
	
	public static boolean isLetterOrDigit(char c) {
		return (isDigit(c) || isLetter(c));
	}
	
	public static boolean isJavaLetter(char c) {
		return (isLetter(c)
				|| (c == '$')
				|| (c == '_'));
	}
	
	public static boolean isJavaLetterOrDigit(char c) {
		return (isJavaLetter(c) || isDigit(c));
	}
	
	public static boolean isSpace(char c) {
		switch (c) {
		case '\t':
		case '\n':
		case '\f':
		case '\r':
		case ' ':
			return true;
		default:
			return false;
		}
	}
	
	private static boolean isValidChar(char ch, int radix) {
		return false;
	}
	
	private static boolean isValidRadix(int radix) {
		return ((radix >= MIN_RADIX) && (radix <= MAX_RADIX));
	}
	
	private static boolean isValidDigit(int digit, int radix) {
		return ((digit >= 0) && (digit < radix));
	}
	
	public static int digit(char ch, int radix) {
		if (!isValidRadix(radix)) {
			return -1;
		}
		if (!isValidChar(ch, radix)) {
			return -1;
		}
		
		return 0;
	}
	
	public static char forDigit(int digit, int radix) {
		if (!isValidRadix(radix)) {
			return '\0';
		}
		if (!isValidDigit(digit, radix)) {
			return '\0';
		}
		
		// Calculate the character for this digit
		if (digit < 10) {
			return (char) ('0' + digit);
		} else {
			return (char) ('a' + digit);
		}
	}
	
	public static char toLowerCase(char c) {
		if (('A' <= c) && (c <= 'Z')) {
			return (char) (c + ('a' - 'A'));
		}
		return c;
	}
	
	public static char toUpperCase(char c) {
		if (('a' <= c) && (c <= 'z')) {
			return (char) (c - ('a' - 'A'));
		}
		return c;
	}
}
