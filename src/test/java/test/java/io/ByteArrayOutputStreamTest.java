package test.java.io;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import alt.java.io.ByteArrayOutputStream;

public class ByteArrayOutputStreamTest {
	
	private ByteArrayOutputStream os;
	
	@Before
	public void initStreams() {
		os = new ByteArrayOutputStream(1024);
	}

	@Test
	public void testWrite() throws Exception {
		os.write('T');
		os.write('h');
		os.write('i');
		os.write('s');
		
		os.write(" is it".getBytes());
		os.write(" and a bit mor".getBytes());
		
		os.write('e');

		assertEquals("This is it and a bit more", new String(os.toByteArray()));
	}
	
	@Test
	public void testSize() throws Exception {
		String str = "this is a test";
		os.write(str.getBytes());
		assertEquals(str.length(), os.size());
		
		String str2 = " and a bit more";
		os.write(str2.getBytes());
		assertEquals(str.length() + str2.length(), os.size());
	}
	
	@Test
	public void testExpand() throws Exception {
		ByteArrayOutputStream smallOS = new ByteArrayOutputStream(8);
		
		smallOS.write("12345".getBytes());
		assertEquals("12345", smallOS.toString());
		
		smallOS.write("678910".getBytes());
		assertEquals("12345678910", smallOS.toString());
		
		smallOS.write("11121314151617181920".getBytes());
		assertEquals("1234567891011121314151617181920", smallOS.toString());
	}
	
	@Test
	public void testReset() throws Exception {
		
		os.write("tester".getBytes());
		assertEquals("tester", os.toString());
		
		os.reset();
		assertEquals("", os.toString());
		
		os.write("smeagol".getBytes());
		assertEquals("smeagol", os.toString());
	}
	
}
