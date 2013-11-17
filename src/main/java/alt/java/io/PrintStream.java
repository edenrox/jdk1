package alt.java.io;

public class PrintStream extends FilterOutputStream {
	
	private OutputStream out;
	private boolean autoflush;

	public PrintStream(OutputStream out) {
		this(out, true);
	}
	
	public PrintStream(OutputStream out, boolean autoflush) {
		this.out = out;
		this.autoflush = autoflush;
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
			out.write(b);
		} catch (IOException ex) {
			throw new RuntimeException("Error writing to OutputStream");
		}
	}
	
	public void print(byte b) {
		tryWrite(b);
	}
	
	public void print(char c) {
		tryWrite(c);
	}
	
	public void print(int i) {
		print(Integer.toString(i));
	}
	public void print(long l) {
		print(Long.toString(l));
	}
	public void print(String str) {
		
	}
	
	public void println(String str) {
		if (str == null) {
			print("null");
		} else {
			print(str);
		}
		print(Character.LINE_SEPARATOR);
	}
	
	public void println(char c) {
		print(c);
		print(Character.LINE_SEPARATOR);
	}
	
	public void println(Object obj) {
		if (obj == null) {
			println("null");
		} else {
			println(obj.toString());
		}
	}
}
