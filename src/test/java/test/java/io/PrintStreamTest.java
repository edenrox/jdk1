package test.java.io;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import alt.java.io.ByteArrayOutputStream;
import alt.java.io.PrintStream;

public class PrintStreamTest {
	
	private ByteArrayOutputStream bos;
	private PrintStream ps;
	
	@Before
	public void initPrintStream() {
		bos = new ByteArrayOutputStream(1024);
		ps = new PrintStream(bos);
	}

	@Test
	public void testPrintln() {
		String[] lines = new String[] {
				"this is a test",
				"this is another line",
				"well that is interesting",
				"but I think its fun"
		};
		int expectedSize = 0;
		for(String line : lines) {
			expectedSize += line.length() + 1;
			ps.println(line);
		}
		assertEquals(expectedSize, bos.size());
	}
	
	@Test
	public void testPrint() {
		ps.print('t');
		ps.print('h');
		ps.print("is ");
		ps.print(256);
		ps.print(" ");
		ps.print(true);
		
		assertEquals("this 256 true", new String(bos.toByteArray()));
	}

}
