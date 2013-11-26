package alt.java.lang;

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
		return Integer.toString(i, 10);
	}
	
	public static String toString(int i, int radix) {
		StringBuffer sb = new StringBuffer(33);
		boolean isNegative = false;
		if (i == 0) {
			return "0";
		} else if (i < 0) {
			isNegative = true;
		}
		while (i > 0) {
			sb.append(Character.forDigit(i % radix, radix));
			i = i / radix;
		}
		if (isNegative) {
			sb.append('-');
		}
		return sb.reverse().toString();
	}
	
	public static String toHexString(int i) {
		return Integer.toString(i, 16);
	}
	
	public static Integer valueOf(String s)
			throws NumberFormatException {
		return new Integer(s);
	}
	
}
		