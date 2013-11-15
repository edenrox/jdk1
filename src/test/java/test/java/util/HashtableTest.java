package test.java.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import alt.java.util.Hashtable;

public class HashtableTest {
	
	Hashtable colors;
	
	@Before
	public void initTables() {
		colors = new Hashtable();
		colors.put("red", "#FF0000");
		colors.put("green", "#00FF00");
		colors.put("blue", "#0000FF");
		colors.put("white", "#FFFFFF");
		colors.put("black", "#000000");
	}

	@Test
	public void testConstructors() {
		
	}
	
	@Test
	public void testSize() {
		Hashtable ht = new Hashtable();
		
		assertEquals(0, ht.size());
		assertTrue(ht.isEmpty());
		assertEquals(5, colors.size());
		assertFalse(colors.isEmpty());
	}
	
	@Test
	public void testAddSetGet() {
		
		colors.put("magenta", "#FF00FF");
		assertEquals(6, colors.size());
		assertEquals("#FF00FF", colors.get("magenta"));
		assertEquals("#FF0000", colors.get("red"));
		
		colors.put("red", "#FE0000");
		assertEquals(6, colors.size());
		assertEquals("#FE0000", colors.get("red"));
	}
	
	@Test
	public void testContains() {
		assertFalse(colors.contains(null));
		assertFalse(colors.containsKey(null));
		
		assertTrue(colors.containsKey("red"));
		assertTrue(colors.contains("#FF0000"));
		
		assertFalse(colors.contains("greenyblue"));
	}
	
	@Test
	public void testClear() {
		assertFalse(colors.isEmpty());
		assertEquals("#FF0000", colors.get("red"));
		
		colors.clear();
		assertTrue(colors.isEmpty());
		assertEquals(null, colors.get("red"));
		assertFalse(colors.contains("red"));
	}
	
	@Test
	public void testEnumerators() {
		
	}
	
	@Test
	public void testOverflow() {
		Hashtable ht = new Hashtable(1, 25.0f);
		
		ht.put("Gandalf", "wizard");
		ht.put("Legolas", "elf");
		
		assertEquals(2, ht.size());
		assertEquals("wizard", ht.get("Gandalf"));
		assertEquals(null, ht.get("Smeagle"));
		assertTrue(ht.containsKey("Legolas"));
		assertTrue(ht.contains("elf"));
		
		assertEquals("{Legolas=elf, Gandalf=wizard}", ht.toString());
	}

}
