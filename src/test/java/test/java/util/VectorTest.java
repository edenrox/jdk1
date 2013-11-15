package test.java.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import alt.java.util.Enumeration;
import alt.java.util.NoSuchElementException;
import alt.java.util.Vector;

public class VectorTest {
	
	Vector abc;
	
	@Before 
	public void initVector() {
		abc = new Vector();
		abc.addElement("A");
		abc.addElement("B");
		abc.addElement("C");
	}

	@Test
	public void testConstructors() {
		Vector v1, v2;
		
		v1 = new Vector();
		assertEquals(0, v1.size());
		
		v2 = new Vector(22);
		assertEquals(0, v2.size());
		assertEquals(22, v2.capacity());
		assertTrue(v2.isEmpty());
		
		
		ExceptionHelper.assertThrows(IllegalArgumentException.class, new Runnable() {
			public void run() {
				Vector v3 = new Vector(-1);
			}
		});
	}
	
	@Test
	public void testAddAndGet() {
		Vector v1;
		
		v1 = new Vector();
		v1.addElement("Test");
	
		assertFalse(v1.isEmpty());
		assertEquals("Test", v1.elementAt(0));
		assertEquals(1, v1.size());
		
		v1.addElement("Legal Object");
		v1.addElement(new Object());
		
		assertEquals("Legal Object", v1.elementAt(1));
		assertEquals(Object.class, v1.elementAt(2).getClass());
		
		ExceptionHelper.assertThrows(IndexOutOfBoundsException.class, new Runnable() {
			public void run() {
				Vector v3 = new Vector(10);
				v3.elementAt(0);
			}
		});
		
		ExceptionHelper.assertThrows(IndexOutOfBoundsException.class, new Runnable() {
			public void run() {
				Vector v3 = new Vector(10);
				v3.elementAt(-1);
			}
		});
	}
	
	@Test
	public void testRemoveAll() {
		assertFalse(abc.isEmpty());
		
		abc.removeAllElements();
		
		assertTrue(abc.isEmpty());
	}
	
	@Test
	public void testResize() {
		Vector v1 = new Vector(0, 4);
		
		assertEquals(0, v1.size());
		assertEquals(0, v1.capacity());
		
		v1.addElement("test");
		
		assertEquals(1, v1.size());
		assertEquals(4, v1.capacity());
	}
	
	@Test
	public void testRemove() {
		assertEquals(3, abc.size());
		
		boolean result = abc.removeElement("B");
		
		assertTrue(result);
		assertEquals(2, abc.size());
		
		result = abc.removeElement("B");
		
		assertFalse(result);
		assertEquals(2, abc.size());
		
		abc.removeElementAt(0);
		
		assertEquals(1, abc.size());
		assertEquals("C", abc.elementAt(0));
		
	}
	
	@Test
	public void testEnumeration() {
		String[] strs = new String[] {"a", "b", "c", "d"};
		Vector v1 = new Vector();
		for(int i = 0; i < strs.length; i++) {
			v1.addElement(strs[i]);
		}
		
		assertEquals(strs.length, v1.size());
		for (int i = 0; i < v1.size(); i++) {
			assertEquals(strs[i], v1.elementAt(i));
		}
		
		final Enumeration e = v1.elements();
		int index = 0;
		while (e.hasMoreElements()) {
			assertEquals(strs[index], e.nextElement());
			index++;
		}
		
		ExceptionHelper.assertThrows(NoSuchElementException.class, new Runnable() {
			public void run() {
				e.nextElement();
			}
		});
		
		assertEquals("[a, b, c, d]", v1.toString());
		
		
	}
	
	@Test
	public void testSetSize() {
		
		assertEquals(3, abc.size());
		
		abc.setSize(2);
		
		assertEquals(2, abc.size());
		
		abc.setSize(4);
		
		assertEquals(4, abc.size());
		assertEquals("[A, B, null, null]", abc.toString());
	}
	
	@Test
	public void testIndexOf() {
		assertEquals(1, abc.indexOf("B"));	
		assertEquals(-1, abc.indexOf("D"));
		assertEquals(-1, abc.indexOf("A", 1));
		
		abc.addElement(null);
		
		assertEquals(3, abc.indexOf(null));
	}
	
	@Test
	public void testContains() {
		assertTrue(abc.contains("A"));
		assertFalse(abc.contains("D"));
	}
	
	@Test
	public void testFirstAndLast() {
		assertEquals("A", abc.firstElement());
		assertEquals("C", abc.lastElement());
		
		abc.addElement("D");
		assertEquals("D", abc.lastElement());
		
		abc.removeAllElements();
		
		ExceptionHelper.assertThrows(NoSuchElementException.class, new Runnable() {
			public void run() {
				abc.firstElement();
			}
		});
		
		ExceptionHelper.assertThrows(NoSuchElementException.class, new Runnable() {
			public void run() {
				abc.lastElement();
			}
		});
	}
	
	@Test
	public void testTrim() {
		abc.trimToSize();
		assertEquals(3, abc.capacity());
		
		abc.addElement("D");
		abc.trimToSize();
		assertEquals(4, abc.capacity());
		
		abc.removeAllElements();
		abc.trimToSize();
		assertEquals(0, abc.capacity());
	}
	
	@Test
	public void testCopyInto() {
		Object[] dest = new Object[10];
		
		abc.copyInto(dest);
		
		ExceptionHelper.assertThrows(NullPointerException.class, new Runnable() {
			public void run() {
				abc.copyInto(null);
			}
		});
		
		ExceptionHelper.assertThrows(IndexOutOfBoundsException.class, new Runnable() {
			public void run() {
				Object[] dest2 = new Object[0];
				abc.copyInto(dest2);
			}
		});
	}
	
	@Test
	public void testSetAndInsert() {
		abc.setElementAt("E", 1);
		
		assertEquals("[A, E, C]", abc.toString());
		
		abc.insertElementAt("I", 0);
		abc.insertElementAt("O", 3);
		
		assertEquals("[I, A, E, O, C]", abc.toString());
		
		abc.setElementAt("U", 4);
		
		assertEquals("[I, A, E, O, U]", abc.toString());
		
		abc.insertElementAt("N", 5);
		
		assertEquals("[I, A, E, O, U, N]", abc.toString());
		
		ExceptionHelper.assertThrows(IndexOutOfBoundsException.class, new Runnable() {
			public void run() {
				abc.insertElementAt("5", 50);
			}
		});
	}
	
	
	

}
