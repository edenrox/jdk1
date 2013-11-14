package java.lang;

public class Integer extends Number {

	public static final int MIN_VALUE = 0x80000000;
	public static final int MAX_VALUE = 0x7fffffff;
	
	public static final int DECIMAL_RADIX = 10;
	
	protected int value;
	
	public Integer(int value) {
		this.value = value;
	}
	public Integer(String value)
			throws NumberFormatException {
		this.value = Integer.parseInt(value);
	}
	
	public String toString() {
		return Integer.toString(this.value);
	}
	
	public boolean equals(Object obj) {
		if ((obj != null) && (obj instanceof Integer)) {
			Integer that = (Integer) obj;
			if (this.value == that.intValue()) {
				return true;
			} 
		}
		
		return false;
	}
	
	public int hashCode() {
		return this.value;
	}
	
	public int intValue() {
		return this.value;
	}
	public long longValue() {
		return (long) this.value;
	}
	public float floatValue() {
		return (float) this.value;
	}
	public double doubleValue() {
		return (double) this.value;
	}
	
	
	
	public static int parseInt(String s)
			throws NumberFormatException {
		return Integer.parseInt(s, DECIMAL_RADIX);
	}
	
	public static int parseInt(String s, int radix)
			throws NumberFormatException {
		int accum = 0;
		for(int i = s.length() - 1; i >= 0; i--) {
			accum *= radix;
			
			char c = s.charAt(i);
			int offset = c - '0';
			if ((offset < 0) || (offset >= radix)) {
				throw new NumberFormatException("Invalid character in string: " + c);
			}
			accum += offset;
		}
		return accum;
	}
	public static String toString(int i) {
		return "";
	}
	
	public static String toString(int i, int radix) {
		return "";
	}
	
	public static String toHexString(int i) {
		return Integer.toString(i, 16);
	}
	
	public static Integer valueOf(String s) {
		return new Integer(s);
	}
	
}
		