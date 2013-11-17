package test.java.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import alt.java.util.NoSuchElementException;
import alt.java.util.StringTokenizer;
import alt.java.util.Vector;

public class StringTokenizerTest {
	
	public static final String TO_TOKENIZE = " this,\tis a,\nstring ";

	public StringTokenizer space, is, ts, its, xs, def, comma;
	
	@Before
	public void initTokenizers() {
		def = new StringTokenizer(TO_TOKENIZE);
		space = new StringTokenizer(TO_TOKENIZE, " ");
		is = new StringTokenizer(TO_TOKENIZE, "i");
		ts = new StringTokenizer(TO_TOKENIZE, "t");
		xs = new StringTokenizer(TO_TOKENIZE, "x");
		its = new StringTokenizer(TO_TOKENIZE, "it");
		comma = new StringTokenizer(TO_TOKENIZE, ",");
	}
	
	@Test
	public void testTokenCount() {
		assertEquals(4, getTokenCount(def));
		assertEquals(2, getTokenCount(space));
		assertEquals(4, getTokenCount(is));
	}
	
	@Test
	public void testTokens() {
		assertArrayEquals(new String[] {"this,", "is", "a,", "string"}, getTokens(def));
		assertArrayEquals(new String[] {"this,\tis", "a,\nstring"}, getTokens(space));
		assertArrayEquals(new String[] {" th", "s,\t", "s a,\nstr", "ng "}, getTokens(is));
		assertArrayEquals(new String[] {TO_TOKENIZE}, getTokens(xs));
	}
	
	@Test
	public void testIncludeTokens() {
		space = new StringTokenizer(TO_TOKENIZE, " ", true);
		is = new StringTokenizer(TO_TOKENIZE, "i", true);
		
		assertArrayEquals(new String[] {" this,\tis ", "a,\nstring"}, getTokens(space));
		assertArrayEquals(new String[] {" thi", "s,\ti", "s a,\nstri", "ng "}, getTokens(is));
	}
	
	@Test
	public void testEmpty() {
		final StringTokenizer st = new StringTokenizer("");
		assertFalse(st.hasMoreElements());
		
		ExceptionHelper.assertThrows(NoSuchElementException.class, new Runnable() {
			public void run() {
				st.nextElement();
			}
		});
	}
	
	protected int getTokenCount(StringTokenizer st) {
		return getTokens(st).length;
	}
	
	protected String[] getTokens(StringTokenizer st) {
		Vector v = new Vector();
		
		while (st.hasMoreTokens()) {
			v.addElement(st.nextToken());
		}
		
		String rv[] = new String[v.size()];
		v.copyInto(rv);
		return rv;
	}
	
}
