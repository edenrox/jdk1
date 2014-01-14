package test.java.util;

import static org.junit.Assert.*;

import org.junit.Test;

import alt.java.util.Date;

public class DateTest {

	@Test
	public void testEpoch() {
		// Jan 1, 1970
		Date d = new Date(70, 0, 1);
		assertEquals(0, d.getTime());
		
		// Jan 1, 1970 @ midnight
		d = new Date(70, 0, 1, 0, 0, 0);
		assertEquals(0, d.getTime());
		
		// Epoch milliseconds
		Date d2 = new Date(0);
		assertEquals(0, d2.getTime());
		assertEquals(70, d2.getYear());
		assertEquals(0, d2.getMonth());
		assertEquals(1, d2.getDate());
		assertEquals(0, d2.getTime());
		assertEquals(0, d2.getHours());
		assertEquals(0, d2.getMinutes());
		assertEquals(0, d2.getSeconds());
	}

	@Test
	public void testToGMTString() {
		Date d = new Date(84, 4, 12);
		assertEquals("12 May 1984 00:00:00 GMT", d.toGMTString());
		
		d = new Date(96, 11, 25, 5, 0, 7);
		assertEquals("25 Dec 1996 05:00:07 GMT", d.toGMTString());
		
		d = new Date(95, 07, 12, 2, 30, 0);
		assertEquals("12 Aug 1995 02:30:00 GMT", d.toGMTString());
		
	}
	
	@Test
	public void testFieldAccess() {
		Date d;
		long msPerDay = 24*60*60*1000L;
		long msPerYear = 365*msPerDay;
		
		
		d = new Date(71, 0, 1);
		assertEquals(msPerYear, d.getTime());
		
		d = new Date(71, 0, 2);
		assertEquals(msPerYear + msPerDay, d.getTime());
		
		d = new Date(71, 0, 5);
		assertEquals(msPerYear + 4*msPerDay, d.getTime());
		
		// Apr 5, 1972
		d = new Date(72, 3, 5);
		assertEquals(2 * msPerYear + (31+29+31 + 4)*msPerDay, d.getTime());
		
		// Apr 5, 1972
		d = new Date(73, 3, 5);
		assertEquals(3 * msPerYear + msPerDay + (31+28+31 + 4)*msPerDay, d.getTime());
		
		// May 12, 1969
		d = new Date(69, 4, 12);
		assertEquals(-1 * msPerYear + (31+28+31+30 + 11)*msPerDay, d.getTime());
		
		// Wed, Dec 25, 1996
		d = new Date(96, 11, 25, 5, 0, 7);
		assertEquals(851490007000L, d.getTime());
		assertEquals(11, d.getMonth());
		assertEquals(25, d.getDate());
		assertEquals(3, d.getDay());
		assertEquals(5, d.getHours());
		assertEquals(0, d.getMinutes());
		assertEquals(7, d.getSeconds());
		
		// Nov 7, 1965
		d = new Date(65, 10, 7);
		assertEquals(-130982400000L, d.getTime());
		
		// And in reverse
		d = new Date(-130982400000L);
		assertEquals(65, d.getYear());
		assertEquals(10, d.getMonth());
		assertEquals(7, d.getDate());
		
		
		// Sep 27, 2015
		d = new Date(115, 8, 27);
		assertEquals(1443312000000L, d.getTime());
		
		// And in reverse
		d = new Date(1443312000000L);
		assertEquals(115, d.getYear());
		assertEquals(8, d.getMonth());
		assertEquals(27, d.getDate());
	}
	
	@Test
	public void testToString() {
		Date d = new Date(84, 4, 12, 5, 21, 38);
		assertEquals("Sat May 12 05:21:38 GMT 1984", d.toString());
	}
	
	@Test
	public void testCompare() {
		Date d1 = new Date(96, 11, 25);
		Date d2 = new Date(96, 11, 25);
		Date d3 = new Date(97, 9, 31);
		Date d4 = new Date(98, 0, 1);
		Date d5 = new Date(96, 11, 25, 12, 5, 5);
		
		// Equals
		assertTrue(d1.equals(d2));
		assertFalse(d1.equals(d3));
		assertFalse(d1.equals(d5));
		assertFalse(d1.equals(null));
		assertFalse(d1.equals("test"));
		
		assertTrue(d1.before(d3));
		assertTrue(d3.after(d1));
		assertFalse(d1.before(d2));
		assertFalse(d1.after(d2));
		assertFalse(d1.after(d3));
		assertTrue(d4.after(d3));
		
		assertTrue(d5.after(d1));
	}
	
	@Test
	public void testMutators() {
		Date d1 = new Date(96, 11, 25);
		
		assertEquals(96, d1.getYear());
		assertEquals(851472000000L, d1.getTime());
		
		d1.setYear(95);
		assertEquals(95, d1.getYear());
		assertEquals(819849600000L, d1.getTime());
		
		d1.setMonth(4);
		assertEquals(4, d1.getMonth());
		assertEquals(801360000000L, d1.getTime());
		
		d1.setSeconds(50);
		assertEquals(50, d1.getSeconds());
		assertEquals(801360050000L, d1.getTime());
		
		d1.setMinutes(5);
		assertEquals(50, d1.getSeconds());
		assertEquals(801360350000L, d1.getTime());
		
		d1.setMinutes(0);
		d1.setSeconds(0);
		assertEquals(801360000000L, d1.getTime());
	}
}
