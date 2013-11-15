package test.java.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import alt.java.util.Enumeration;
import alt.java.util.Properties;
import alt.java.util.Vector;

public class PropertiesTest {

	private Properties top, middle, bottom;
	
	@Before
	public void initProps() {
		top = new Properties();
		middle = new Properties(top);
		bottom = new Properties(middle);
		
		top.put("key1", "top1");
		top.put("key2", "top2");
		top.put("key3", "top3");
		
		middle.put("key1", "middle1");
		middle.put("key2", "middle2");
		middle.put("key4", "middle4");
		
		bottom.put("key1", "bottom1");
		bottom.put("key5", "bottom5");
	}
	
	@Test
	public void testGetProperty() {
		// Top tests (no default)
		assertEquals("top1", top.getProperty("key1"));
		assertEquals("top2", top.getProperty("key2"));
		assertNull(top.getProperty("key4"));
		
		// Middle tests (top default)
		assertEquals("top3", middle.getProperty("key3"));
		assertEquals("middle1", middle.getProperty("key1"));
		assertNull(middle.getProperty("key5"));
		
		// Bottom tests (middle, then top defaults)
		assertEquals("top3", bottom.getProperty("key3"));
		assertEquals("bottom1", bottom.getProperty("key1"));
		assertEquals("bottom5", bottom.getProperty("key5"));
		assertEquals("middle4", bottom.getProperty("key4"));
		assertNull(bottom.getProperty("key6"));
	}
	
	@Test
	public void testGetDefault() {
		assertNull(top.getProperty("key4"));
		assertEquals("def", top.getProperty("key4", "def"));
		
		assertEquals("top1", top.getProperty("key1", "def"));
	}
	
	@Test
	public void testPropertyNames() {
		Vector keys = new Vector();
		keys.addElement("key1");
		keys.addElement("key2");
		keys.addElement("key3");
		
		Enumeration names = top.propertyNames();
		
		while (names.hasMoreElements()) {
			Object name = names.nextElement();
			assertTrue(keys.removeElement(name));
		}
		assertTrue(keys.isEmpty());
	}
	
}
