package java.util;

public class StringTokenizer
		implements Enumeration {
		
	private static final String WHITE_SPACE_DELIMITERS = " \t\n\x0B\f\r";
	
	private String str;
	private String delim;
	private String returnTokens;
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
	
	private final init(String str, String delim, boolean returnTokens) {
		this.str = str;
		this.delim = delim;
		this.returnTokens = returnTokens;
		this.pos = 0;
	}
	
	public boolean hasMoreTokens() {
		if (pos < str.length())
	
	public String nextToken() {
		int startPos = pos;
		boolean done = false;
		
		while (!done) {
			if (pos >= str.length()) {
				done = true;
			}
			if (delim.indexOf(str.charAt(pos)) >= 0) {
				done = true;
			}
			pos++;
		}
		
		// TODO: fix this
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
	}
}