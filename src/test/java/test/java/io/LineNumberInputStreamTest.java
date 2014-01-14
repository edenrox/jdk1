package test.java.io;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import alt.java.io.ByteArrayInputStream;
import alt.java.io.LineNumberInputStream;

public class LineNumberInputStreamTest {
	
	protected String[] LINES;
	protected LineNumberInputStream is;
	
	@Before
	public void initInputStream() {
		// Setup a set of lines to read
		LINES = new String[] {
				"this is the first line\n",
				"second line\r",
				"\r",
				"fourth line\n",
				"the fifthline is a long one."
		};
		
		// Put all the lines into the input stream
		String content = LINES[0] + LINES[1] + LINES[2] + LINES[3] + LINES[4];
		is = new LineNumberInputStream(new ByteArrayInputStream(content.getBytes()));
	}

	@Test
	public void testLineNumbers() throws Exception {
		
		// start on line number 1
		assertEquals(0, is.getLineNumber());
		
		// read 4 characters (still on line 1)
		byte[] buffer = new byte[1024];
		is.read(buffer, 0, 4);
		assertEquals("this", new String(buffer, 0, 4));
		assertEquals(0, is.getLineNumber());
		
		// read until the next line
		is.reset();
		assertEquals(0, is.getLineNumber());
		
		is.read(buffer, 0, LINES[0].length());
		assertEquals(LINES[0], new String(buffer, 0, LINES[0].length()));
		assertEquals(1, is.getLineNumber());
		
		// read the next line
		is.read(buffer, 0, LINES[1].length());
		assertEquals(LINES[1], new String(buffer, 0, LINES[1].length()));
		assertEquals(2, is.getLineNumber());
		
		// test reseting back to the beginning of the input stream.
		is.reset();
		is.read(buffer, 0, 4);
		assertEquals(0, is.getLineNumber());
		assertEquals("this", new String(buffer, 0, 4));
	}

}
