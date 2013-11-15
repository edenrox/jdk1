package test.java.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import alt.java.util.EmptyStackException;
import alt.java.util.Stack;

public class StackTest {
	
	Stack abc;
	
	@Before
	public void initStacks() {
		abc = new Stack();
		abc.push("A");
		abc.push("B");
		abc.push("C");
	}

	@Test
	public void testPushPeek() {
		assertEquals(3, abc.size());
	
		assertEquals("C", abc.peek());
		
		abc.push("D");
		assertEquals("D", abc.peek());
	}
	
	@Test
	public void testEmpty() {
		assertFalse(abc.empty());
		
		abc.removeAllElements();
		assertTrue(abc.empty());
	}
	
	@Test
	public void testPop() {
		Object item = abc.pop();
		
		assertEquals("C", item);
		assertEquals(2, abc.size());
		
		abc.push("D");
		item = abc.pop();
		
		assertEquals("D", item);
		assertEquals(2, abc.size());
		
		abc.pop();
		abc.pop();
		
		assertTrue(abc.empty());
		
		
		ExceptionHelper.assertThrows(EmptyStackException.class, new Runnable() {
			public void run() {
				abc.pop();
			}
		});
		
		ExceptionHelper.assertThrows(EmptyStackException.class, new Runnable() {
			public void run() {
				abc.peek();
			}
		});
	}
	
	@Test
	public void testSearch() {
		assertEquals(1, abc.search("C"));
		assertEquals(3, abc.search("A"));
		assertEquals(-1, abc.search("E"));
		
		abc.push("D");
		assertEquals(2, abc.search("C"));
		assertEquals(1, abc.search("D"));
		
		abc.push("D");
		assertEquals(3, abc.search("C"));
		assertEquals(1, abc.search("D"));
		assertEquals(-1, abc.search("E"));
	}

}
