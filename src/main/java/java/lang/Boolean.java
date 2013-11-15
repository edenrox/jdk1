package java.lang;

public final class Boolean {
	public static final Boolean TRUE = new Boolean(true);
	public static final Boolean FALSE = new Boolean(false);
	
	public static final String TRUE_STRING = "true";
	public static final String FALSE_STRING = "false";
	
	protected boolean value;
	
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
	
	protected static boolean isTrueString(String s) {
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

	public static Boolean getBoolean(String name) {
		String propValue = System.getProperty(name);
		return Boolean.valueOf(propValue);
	}
}