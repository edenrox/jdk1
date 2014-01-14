package alt.java.io;

public class PrintStream extends FilterOutputStream {
	
	private OutputStream out;
	private boolean autoflush;
	private boolean hasError;

	public PrintStream(OutputStream out) {
		this(out, true);
	}
	
	public PrintStream(OutputStream out, boolean autoflush) {
		this.out = out;
		this.autoflush = autoflush;
	}
	
	public boolean checkError() {
		flush();
		return hasError;
	}
	
	public void close() {
		try {
			out.close();
		} catch (IOException ex) {
			hasError = true;
		}
	}
	
	public void flush() {
		try {
			out.flush();
		} catch (IOException ex) {
			hasError = true;
		}
	}
	
	public void write(int b) throws IOException{
		out.write(b);
		if (autoflush) {
			out.flush();
		}
	}
	
	public void write(byte[] bytes, int offset, int length) 
			throws NullPointerException, IOException, IndexOutOfBoundsException {
		out.write(bytes, offset, length);
		if (autoflush) {
			out.flush();
		}
	}
	
	private void tryWrite(int b) {
		try {
			write(b);
		} catch (IOException ex) {
			hasError = true;
		}
	}
	
	public void print(boolean b) {
		print(Boolean.toString(b));
	}
	public void print(byte b) {
		tryWrite(b);
	}
	public void print(char c) {
		tryWrite((byte) (c & 0xFF));
	}
	public void print(char[] s) {
		for(int i = 0; i < s.length; i++) {
			print(s[i]);
		}
	}
	public void print(double d) {
		print(Double.toString(d));
	}
	public void print(float f) {
		print(Float.toString(f));
	}
	public void print(int i) {
		print(Integer.toString(i));
	}
	public void print(long l) {
		print(Long.toString(l));
	}
	public void print(Object obj) {
		print(String.valueOf(obj));
	}
	public void print(String str) {
		for(int i = 0; i < str.length(); i++) {
			print(str.charAt(i));
		}
	}
	
	public void println() {
		print('\n');
	}
	public void println(boolean b) {
		print(b);
		println();
	}
	public void println(char c) {
		print(c);
		println();
	}
	public void println(char s[]) {
		print(s);
		println();
	}
	public void println(double d) {
		print(d);
		println();
	}
	public void println(float f) {
		print(f);
		println();
	}
	public void println(int i) {
		print(i);
		println();
	}
	public void println(long l) {
		print(l);
		println();
	}
	public void println(Object obj) {
		print(String.valueOf(obj));
		println();
	}
	public void println(String str) {
		if (str == null) {
			print("null");
		} else {
			print(str);
		}
		println();
	}
}
