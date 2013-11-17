package test.java.io;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.lang.Thread;
import java.lang.ThreadGroup;
import java.util.Hashtable;

public class ThreadGroupTest {
	
	ThreadGroup top, topc1, topc2, topc1c1, topc1c2, topc2c1, topc2c2, topc2c3;
	
	Thread ta, tb, tc1a, tc1b;
	
	@Before
	public void initGroups() {
		
		// Level 1
		top = new ThreadGroup("top");
		
		// Level 2
		topc1 = new ThreadGroup(top, "topc1");
		topc2 = new ThreadGroup(top, "topc2");
		
		// Level 3
		topc1c1 = new ThreadGroup(topc1, "topc1c1");
		topc1c2 = new ThreadGroup(topc1, "topc1c2");
		topc2c1 = new ThreadGroup(topc2, "topc2c1");
		topc2c2 = new ThreadGroup(topc2, "topc2c2");
		topc2c3 = new ThreadGroup(topc2, "topc2c3");
		
		// Threads
		ta = new Thread(top, "Top-A");
		tb = new Thread(top, "Top-B");
		
		tc1a = new Thread(topc1, "TopC1-A");
		tc1b = new Thread(topc1, "TopC1-B");
	}
	

	@Test
	public void testGroupParentage() {
		assertEquals(top, topc1.getParent());
		assertEquals(topc1, topc1c1.getParent());
		assertEquals(top, topc1c2.getParent().getParent());
	}
	
	@Test
	public void testThreadParentage() {
		assertEquals(top, ta.getThreadGroup());
		assertEquals(top, tb.getThreadGroup());
		assertEquals(topc1, tc1a.getThreadGroup());
		assertEquals(topc1, tc1b.getThreadGroup());
	}
	
	@Test
	public void testDaemon() {
		assertEquals(false, top.isDaemon());
		
		top.setDaemon(true);
		assertEquals(true, top.isDaemon());
	}
	
	@Test
	public void testMaxPriority() {
		assertEquals(Thread.MAX_PRIORITY, top.getMaxPriority());
		
		top.setMaxPriority(7);
		assertEquals(7, top.getMaxPriority());
		
		topc1.setMaxPriority(8);
		assertEquals(7, top.getMaxPriority());
	}
	
	@Test
	public void testEnumeration() {
		Thread[] children = new Thread[8];
		int count = top.enumerate(children, false);
		
		assertEquals(2, count);
		assertEquals(ta, children[0]);
		assertEquals(tb, children[1]);
		
		count = top.enumerate(children, true);
		assertEquals(4, count);
		assertEquals(ta, children[0]);
		assertEquals(tb, children[1]);
		assertEquals(tc1a, children[2]);
		assertEquals(tc1b, children[3]);
	}
	


}
