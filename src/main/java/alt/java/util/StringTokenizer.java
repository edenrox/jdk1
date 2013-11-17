package alt.java.util;

public class StringTokenizer
		implements Enumeration {
		
	private static final String WHITE_SPACE_DELIMITERS = " \t\n\f\r";
	
	private char[] chars;
	private String delim;
	private boolean returnTokens;
	private int pos;
	
	public StringTokenizer(String str, String delim, boolean returnTokens) {
		init(str, delim, returnTokens);
	}
	
	public StringTokenizer(String str, String delim) {
		init(str, delim, false);
	}
	
	public StringTokenizer(String str) {
		init(str, WHITE_SPACE_DELIMITERS, false);
	}
	
	private final void init(String str, String delim, boolean returnTokens) {
		this.chars = new char[str.length()];
		str.getChars(0, str.length(), this.chars, 0);
		this.delim = delim;
		this.returnTokens = returnTokens;
		this.pos = 0;
	}
	
	private boolean isDelimChar(char c) {
		return (delim.indexOf(c) > -1);
	}
	
	public boolean hasMoreTokens() {
		while ((pos < chars.length) && (isDelimChar(chars[pos]))) {
			pos++;
		}
		return (pos < chars.length);
	}
	
	public String nextToken() {
		if (!hasMoreTokens()) {
			throw new NoSuchElementException("No more tokens in string");
		}
		int startPos = pos;
		
		// find the first non delim char (or end of string)
		while ((pos < chars.length) && (!isDelimChar(chars[pos]))) {
			pos++;
		}
		
		if (returnTokens) {
			// advance to the end of the next group of delimiters
			hasMoreTokens();
		}
		
		return new String(chars, startPos, pos - startPos);
	}	
	
	public String nextToken(String delim) {
		this.delim = delim;
		
		return nextToken();
	}
	
	public boolean hasMoreElements() {
		return hasMoreTokens();
	}
	
	public Object nextElement() {
		return nextToken();
	}
	
	public int countTokens() {
		// TODO: Implement this
		return 0;
	}
}