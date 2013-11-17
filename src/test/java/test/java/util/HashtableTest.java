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
		HashtableTester ht;
		
		// Zero item hash table 
		ht = new HashtableTester(0);
		assertEquals(0, ht.getTableSize());
		assertFalse(ht.containsKey("Legolas"));
		assertFalse(ht.contains("anything"));
		assertNull(ht.get("hello"));
		assertEquals(0, ht.size());
		ht.put("test", "that");
		assertTrue(ht.getTableSize() > 0);
		assertEquals(1, ht.size());
		
		// Small hash table
		ht = new HashtableTester(4, 0.25f);
		assertEquals(4, ht.getTableSize());
		assertEquals(0.25f, ht.getLoadFactor(), 0.001f);
		assertEquals(0, ht.size());
		
		// Default hash table
		ht = new HashtableTester();
		assertEquals(10, ht.getTableSize());
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
	public void testClone() {
		Hashtable clone = (Hashtable) colors.clone();
		
		assertEquals(colors.size(), clone.size());
		assertEquals(colors.get("red"), clone.get("red"));
		assertEquals(colors.get("purple"), clone.get("purple"));
		
		clone.remove("red");
		clone.put("blue", "blueberry");
		clone.put("gray", "#CCCCCC");
		
		
		assertNotNull(colors.get("red"));
		assertNull(clone.get("red"));
		assertEquals("blueberry", clone.get("blue"));
		assertEquals("#FF0000", colors.get("red"));
		assertTrue(clone.containsKey("gray"));
		assertFalse(colors.contains("gray"));
		
	}
	
	@Test
	public void testRemove() {
		assertEquals("#FF0000", colors.remove("red"));
		assertEquals(4, colors.size());
		
		assertEquals(null, colors.remove("red"));
		
		// no keys
		Hashtable ht = new Hashtable();
		assertEquals(null, ht.remove("test"));
		
		// no capacity
		ht = new Hashtable(0);
		assertEquals(null, ht.remove("test"));
	}
	
	@Test
	public void testRemoveOverflow() {
		
		// Build a linked/overflowing hashtable
		Hashtable ht = new Hashtable(1, 22f);
		ht.put("Gandalf", "wizard");
		ht.put("Gimli", "dwarf");
		ht.put("Legolas",  "elf");
		ht.put("Elrond", "elf");
		ht.put("Bilbo", "hobbit");
		
		
		// remove the head of the list
		assertEquals("hobbit", ht.remove("Bilbo"));
		assertEquals("elf", ht.get("Elrond"));
		assertEquals(4, ht.size());
		
		
		// remove the end of the lsit
		assertEquals("wizard", ht.remove("Gandalf"));
		assertEquals(null, ht.remove("Gandalf"));
		assertEquals(3, ht.size());
		
		// remove the middle of the list
		assertEquals("elf", ht.remove("Legolas"));
		assertEquals(2, ht.size());
		assertEquals("dwarf", ht.get("Gimli"));
	}
	
	@Test
	public void testOverflow() {
		HashtableTester ht = new HashtableTester(1, 25.0f);
		
		ht.put("Gandalf", "wizard");
		ht.put("Legolas", "elf");
		ht.put("Gimli", "dwarf");
		
		assertEquals(3, ht.size());
		assertEquals("wizard", ht.get("Gandalf"));
		assertEquals(null, ht.get("Smeagle"));
		assertTrue(ht.containsKey("Legolas"));
		assertTrue(ht.contains("elf"));
		assertEquals(1, ht.getTableSize());
		
		assertEquals("{Gimli=dwarf, Legolas=elf, Gandalf=wizard}", ht.toString());
	}
	
	@Test
	public void testResize() {
		HashtableTester ht = new HashtableTester(10, 0.5f);
		
		assertEquals(0, ht.size());
		assertEquals(10, ht.getTableSize());
		
		ht.put("a", "1");
		ht.put("b", "2");
		ht.put("c", "3");
		ht.put("d", "4");
		ht.put("e", "5");
		
		assertEquals(5, ht.size());
		assertEquals(10, ht.getTableSize());
		assertEquals("5", ht.get("e"));
		assertEquals("3", ht.get("c"));
		
		ht.put("f", "6");
		
		assertEquals(6, ht.size());
		assertEquals(20, ht.getTableSize());
		assertEquals("5", ht.get("e"));
		assertEquals("3", ht.get("c"));
		assertEquals("6", ht.get("f"));
		
		ht.put("g", "7");
		ht.put("h", "8");
		ht.put("i", "9");
		ht.put("j", "10");
		ht.put("k", "11");
		
		assertEquals(11, ht.size());
		assertEquals(40, ht.getTableSize());
		assertEquals("5", ht.get("e"));
		assertEquals("3", ht.get("c"));
		assertEquals("6", ht.get("f"));
		assertEquals("11", ht.get("k"));
	}
	
	
	public class HashtableTester extends Hashtable {
		public HashtableTester() {
			super();
		}
		
		public HashtableTester(int capacity) {
			super(capacity);
		}
		
		public HashtableTester(int capacity, float loadFactor) {
			super(capacity, loadFactor);
		}
		
		public int getTableSize() {
			return table.length;
		}
		
		public float getLoadFactor() {
			return maxLoadFactor;
		}
	}

}
