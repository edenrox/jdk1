package test.java.lang;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import alt.java.lang.Character;

public class CharacterTest {
	
	Character a, b, c;
	Character zero, one;
	
	@Before
	public void initChars() {
		a = new Character('a');
		b = new Character('b');
		c = new Character('c');
		
		zero = new Character('0');
		one = new Character('1');
		
	}

	@Test
	public void testToLowerCase() {
		
		assertEquals('t', Character.toLowerCase('T'));
		assertEquals('t', Character.toLowerCase('t'));
		assertEquals('0', Character.toLowerCase('0'));
		assertEquals('b', Character.toLowerCase('B'));
		assertEquals('c', Character.toLowerCase('c'));
	}
	
	@Test
	public void testToUpperCase() {
		assertEquals('J', Character.toUpperCase('j'));
		assertEquals('J', Character.toUpperCase('J'));
		assertTrue(Character.isLowerCase('j'));
		assertFalse(Character.isLowerCase('H'));
		
		assertEquals('!', Character.toUpperCase('!'));
		assertEquals('F', Character.toUpperCase('f'));
		assertEquals('S', Character.toUpperCase('S'));
	}
	
	@Test
	public void testEquals() {
		assertEquals(new Character('a'), a);
		assertNotEquals(new Character('A'), a);
	}

}
