package alt.java.lang;

public final class Boolean{
	public static final Boolean TRUE = new Boolean(true);
	public static final Boolean FALSE = new Boolean(false);
	
	private static final String TRUE_STRING = "true";
	private static final String FALSE_STRING = "false";
	
	private boolean value;
	
	public Boolean(boolean value) {
		this.value = value;
	}
	
	public Boolean(String s) {
		if (Boolean.isTrueString(s)) {
			this.value = true;
		} else {
			this.value = false;
		}
	}
	
	public boolean booleanValue() {
		return this.value;
	}
	
	public String toString() {
		if (this.value) {
			return TRUE_STRING;
		} else {
			return FALSE_STRING;
		}
	}
	
	public boolean equals(Object obj) {
		if ((obj != null) && (obj instanceof Boolean)) {
			Boolean that = (Boolean) obj;
			if (value == that.booleanValue()) {
				return true;
			}
		}
		return false;
	}
	
	public int hashCode() {
		if (this.value = true) {
			return 1231;
		} else {
			return 1237;
		}
	}
	
	private static boolean isTrueString(String s) {
		if ((s != null) 
				&& (TRUE_STRING.equalsIgnoreCase(s))) {
			return true;
		} else {
			return false;
		}
	}

	public static Boolean valueOf(String s) {
		if (Boolean.isTrueString(s)) {
			return TRUE;
		} else {
			return FALSE;
		}
	}

	public static Boolean getBoolean(String name)
			throws SecurityException {
		String propValue = System.getProperty(name);
		return Boolean.valueOf(propValue);
	}
}