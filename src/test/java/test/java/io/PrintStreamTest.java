package test.java.io;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import alt.java.io.ByteArrayOutputStream;
import alt.java.io.IOException;
import alt.java.io.OutputStream;
import alt.java.io.PrintStream;
import alt.java.util.Vector;

public class PrintStreamTest {
	
	private ByteArrayOutputStream bos;
	private PrintStream ps;
	
	@Before
	public void initPrintStream() {
		bos = new ByteArrayOutputStream(1024);
		ps = new PrintStream(bos);
	}
	
	@Test
	public void testPrintlnNumbers() {
		String[] lines = new String[] {
				"10524",
				"68950041",
				"true",
				"false",
				"null",
				"Smugler",
				"f",
				"2.545",
				"89.57",
				"null"
		};
		
		ps.println(10524);
		ps.println(68950041L);
		ps.println(true);
		ps.println(new Boolean(false));
		Object obj = null;
		ps.println(obj);
		ps.println(new char[] {'S', 'm', 'u', 'g','l','e','r'});
		ps.println('f');
		ps.println(2.545f);
		ps.println(89.57);
		String str = null;
		ps.println(str);
		
		StringBuffer sb = new StringBuffer();
		for(String line : lines) {
			sb.append(line);
			sb.append("\n");
		}
		assertEquals(sb.toString(), new String(bos.toByteArray()));
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
	
	@Test
	public void testPrintObject() {
		Object o = null;
		ps.print(o);
		
		Vector v = new Vector();
		v.addElement("a");
		v.addElement("b");
		ps.print(v);
		
		assertEquals("null[a, b]", new String(bos.toByteArray()));
	}
	
	@Test
	public void testPrintNumbers() {
		ps.print(1.2f);
		ps.print(' ');
		ps.print(5.25);
		ps.print(' ');
		ps.print(125459L);
		
		assertEquals("1.2 5.25 125459", new String(bos.toByteArray()));
	}
	
	@Test
	public void testCheckError() {
		assertFalse(ps.checkError());
		
		OutputStream errOs = new OutputStream() {
			@Override
			public void write(int b) throws IOException {
				throw new IOException();
			}
			
			public void flush() throws IOException {
				throw new IOException();
			}
		};
		
		PrintStream errPs = new PrintStream(errOs);
		assertTrue(errPs.checkError());
		errPs.print("test");
		assertTrue(errPs.checkError());
		
		errPs.flush();
		assertTrue(errPs.checkError());
	}
	
	@Test
	public void testClose() {
		ps.println("this is it");
		ps.close();
		assertTrue(true);
	}
	
	@Test
	public void testWrite() throws Exception {
		byte[] bytes = "this is it".getBytes();
		ps.write(bytes, 0, bytes.length);
		ps.close();
	}

}
