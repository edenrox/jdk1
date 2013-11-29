package test.java.io;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import alt.java.io.ByteArrayInputStream;

public class ByteArrayInputStreamTest {
	
	private String src;
	private ByteArrayInputStream isSrc;
	
	@Before
	public void initStreams() {
		src = "Here are some Bytes for ya!";
		isSrc = new ByteArrayInputStream(src.getBytes());
	}

	@Test
	public void testRead() {
		byte[] buffer = new byte[32];
		assertEquals('H', isSrc.read());
		assertEquals('e', isSrc.read());
		assertEquals(4, isSrc.read(buffer, 0, 4));
		assertEquals("re a", new String(buffer, 0, 4));
	}
	
	@Test
	public void testSkip() {
		byte[] buffer = new byte[32];
		assertEquals('H', isSrc.read());
		assertEquals(4, isSrc.skip(4));
		assertEquals('a', isSrc.read());
		
		assertEquals(8, isSrc.skip(8));
		assertEquals(4, isSrc.read(buffer, 5, 4));
		assertEquals("Byte", new String(buffer, 5, 4));
	}
	
	@Test
	public void testReset() {
		assertEquals('H', isSrc.read());
		assertEquals('e', isSrc.read());
		isSrc.skip(4);
		assertEquals('r', isSrc.read());
		
		isSrc.reset();
		assertEquals('H', isSrc.read());
		
		isSrc.reset();
		isSrc.skip(3);
		assertEquals('e', isSrc.read());
	}
	
	@Test
	public void testEOF() throws Exception {
		assertEquals(src.length(), isSrc.skip(src.length()));
		assertEquals(-1, isSrc.read());
		
		byte[] buffer = new byte[8];
		isSrc.reset();
		assertEquals(src.length(), isSrc.skip(src.length()));
		assertEquals(-1, isSrc.read(buffer));
	}

}
