package alt.java.io;

public class StreamTokenizer {
	public static final int TT_EOF = -1;
	public static final int TT_EOL = '\n';
	public static final int TT_NUMBER = -2;
	public static final int TT_WORD = -3;
	
	public int ttype;
	public String sval;
	public double nval;
	
	private InputStream in;
	
	public StreamTokenizer(InputStream in) {
		this.in = in;
	}
	
	public void resetSyntax() {
		
	}
	
	public void slashSlashComments(boolean flag) {
		
	}
	
	public void lowerCaseMode(boolean flag) {
		
	}
	
	public int nextToken() throws IOException {
		return -1;
	}
	
	public void pushBack() {
		
	}
	
	public int lineno() {
		return 0;
	}
	
	public String toString() {
		return "";
	}
}
