package test.java.io;

import static org.junit.Assert.*;

import org.junit.Test;

import alt.java.io.IOException;
import alt.java.io.StringBufferInputStream;

public class StringBufferInputStreamTest {

	@Test
	public void testReading() throws Exception {
		
		StringBufferInputStream sbis = new StringBufferInputStream("this is a test string");
		
		byte buffer[] = new byte[16];
		int numRead;
		
		assertEquals('t', (byte) sbis.read());
		assertEquals('h', (byte) sbis.read());
		
		numRead = sbis.read(buffer, 0, 4);
		assertEquals(4, numRead);
		assertEquals("is i", new String(buffer, 0, 4));
		
		numRead = sbis.read(buffer, 0, 8);
		assertEquals(8, numRead);
		assertEquals("s a test", new String(buffer, 0, 8));
		
		numRead = sbis.read(buffer, 8, 4);
		assertEquals(4, numRead);
		assertEquals("s a test str", new String(buffer, 0, 12));
		
		numRead = sbis.read(buffer, 0, 10);
		assertEquals(3, numRead);
		assertEquals("ing test str", new String(buffer, 0, 12));
		
		assertEquals(-1, sbis.read());
		assertEquals(-1, sbis.read(buffer, 0, 10));
	}
	
	@Test
	public void testEOF() {
		StringBufferInputStream sbis = new StringBufferInputStream("");
		byte[] buffer = new byte[10];
		
		assertEquals(-1, sbis.read());
		assertEquals(-1, sbis.read(buffer, 0, 10));
	}
	
	@Test
	public void testReadAndReset() {
		StringBufferInputStream sbis = new StringBufferInputStream("this");
		
		// first read
		assertEquals('t', (char) sbis.read());
		assertEquals('h', (char) sbis.read());
		assertEquals('i', (char) sbis.read());
		assertEquals('s', (char) sbis.read());
		assertEquals(-1, sbis.read());
		
		// second read
		sbis.reset();
		assertEquals('t', (char) sbis.read());
		assertEquals('h', (char) sbis.read());
		
		// third read
		sbis.reset();
		assertEquals('t', (char) sbis.read());
		
		// fourth reset
		sbis.reset();
		int read = 0;
		int count = 0;
		while ((read >= 0)) {
			read = sbis.read();
			count++;
		}
		assertEquals(5, count);
	}
	
	@Test
	public void testReadToBuffer() throws IOException {
		String src = "this is a really long string that we can know the length of";
		StringBufferInputStream sbis = new StringBufferInputStream(src);
		byte[] buffer = new byte[1024];
	
		
		assertEquals(20, sbis.read(buffer, 0, 20));
		assertEquals(20, sbis.read(buffer, 10, 20));
		assertEquals(src.substring(0, 10) + src.substring(20, 40), new String(buffer, 0, 30));
		
		sbis.reset();
		assertEquals(src.length(), sbis.read(buffer, 0, 512));
		assertEquals(-1, sbis.read(buffer, 0, 512));
		
		sbis.reset();
		assertEquals(src.length(), sbis.read(buffer));
		assertEquals(-1, sbis.read(buffer));
	}
	
	@Test
	public void testSkip() {
		String src = "four char word time";
		StringBufferInputStream sbis = new StringBufferInputStream(src);
		
		byte[] buffer = new byte[1024];
		
		sbis.read(buffer, 0, 4);
		assertEquals("four", new String(buffer, 0, 4));
		
		sbis.skip(6);
		sbis.read(buffer, 0, 4);
		assertEquals("word", new String(buffer, 0, 4));
		
		sbis.reset();
		sbis.skip(11);
		sbis.read(buffer, 0, 4);
		assertEquals("ord ", new String(buffer, 0, 4));
	}

}
