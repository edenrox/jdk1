package alt.java.lang;

public class Long extends Number {
	
	private long value;
	
	public Long(long value) {
		this.value = value;
	}

	public int intValue() {
		return (int) value;
	}
	public long longValue() {
		return value;
	}
	public float floatValue() {
		return (float) value;
	}
	public double doubleValue() {
		return (double) value;
	}
	
	
	public String toString() {
		return Long.toString(value, 10);
	}
	
	public static String toString(long i) {
		return Long.toString(i, 10);
	}
	
	public static String toString(long i, int radix) {
		StringBuffer sb = new StringBuffer(65);
		boolean isNegative = false;
		if (i == 0) {
			return "0";
		} else if (i < 0) {
			isNegative = true;
		}
		while (i > 0) {
			sb.append(Character.forDigit((int) i % radix, radix));
			i = i / radix;
		}
		if (isNegative) {
			sb.append('-');
		}
		return sb.reverse().toString();
	}
}
