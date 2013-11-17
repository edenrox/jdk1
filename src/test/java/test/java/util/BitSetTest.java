package test.java.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import alt.java.util.BitSet;

public class BitSetTest {
	
	BitSet alt, empty, none, all;
	BitSet none4, alt4, all4, altnot4;
	
	@Before
	public void initBitSets() {
		alt = new BitSet(17);
		for(int i = 0; i < 17; i+=2) {
			alt.set(i);
		}
		
		empty = new BitSet();
		
		none = new BitSet(50);
		
		all = new BitSet(31);
		for (int i = 0; i < 31; i++) {
			all.set(i);
		}
		
		none4 = new BitSet(4);
		alt4 = new BitSet(4);
		alt4.set(0);
		alt4.set(2);
		all4 = new BitSet(4);
		all4.set(0);
		all4.set(1);
		all4.set(2);
		all4.set(3);
		altnot4 = new BitSet(4);
		altnot4.set(1);
		altnot4.set(3);
	}
	
	@Test
	public void testSize() {
		assertEquals(17, alt.size());
		assertEquals(0, empty.size());
	}
	
	@Test
	public void testGet() {
		assertTrue(alt.get(0));
		assertFalse(alt.get(1));
		assertTrue(alt.get(2));
		
		assertFalse(none.get(2));
		
		assertTrue(all.get(1));
		assertTrue(all.get(30));
		
		ExceptionHelper.assertThrows(IndexOutOfBoundsException.class, new Runnable() {
			public void run() {
				alt.get(-1);
			}
		});
		
		ExceptionHelper.assertThrows(IndexOutOfBoundsException.class, new Runnable() {
			public void run() {
				alt.get(18);
			}
		});
	}
	
	@Test
	public void testClear() {
		assertTrue(alt.get(0));
		assertFalse(alt.get(1));
		assertTrue(alt.get(2));
		
		alt.clear(0);
		
		assertFalse(alt.get(0));
		assertFalse(alt.get(1));
		assertTrue(alt.get(2));
	}
	
	@Test
	public void testToString() {
		assertEquals("{}", none.toString());
		assertEquals("{}", empty.toString());
		
		none.set(1);
		none.set(3);
		assertEquals("{1, 3}", none.toString());
		
		none.clear(3);
		none.set(4);
		assertEquals("{1, 4}", none.toString());
	}
	
	@Test
	public void testEquals() {
		assertEquals(new BitSet(), empty);
		assertNotEquals("junk", empty);
		assertNotEquals(empty, "junk");
		
		BitSet clone = (BitSet) alt.clone();
		assertEquals(alt, clone);
		
		clone.set(1);
		
		assertTrue(clone.get(1));
		assertFalse(alt.get(1));
		assertNotEquals(alt, clone);
	}
	
	@Test
	public void testAnd() {
		BitSet clone = (BitSet) all.clone();
		BitSet emptyAll = new BitSet(all.size());
		
		clone.and(all);
		assertEquals(all, clone);
		
		clone.and(none);
		assertEquals(emptyAll, clone);
	}
	
	@Test
	public void testOr() {
		BitSet clone = (BitSet) all.clone();
		BitSet emptyAll = new BitSet(all.size());
		
		clone.or(all);
		assertEquals(all, clone);
		
		clone.or(emptyAll);
		assertEquals(all, clone);
		
		clone.and(emptyAll);
		clone.or(all);
		assertEquals(all, clone);
	}
	
	@Test
	public void testXor() {
		BitSet clone = (BitSet) alt4.clone();
		
		clone.xor(all4);
		assertEquals(altnot4, clone);
		
		clone.xor(altnot4);
		assertEquals(none4, clone);
		
		clone.xor(alt4);
		assertEquals(alt4, clone);
		
		clone.xor(none4);
		assertEquals(alt4, clone);
	}
}
