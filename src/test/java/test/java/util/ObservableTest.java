package test.java.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import alt.java.util.Observable;
import alt.java.util.Observer;

public class ObservableTest {
	
	Observable o1, o2;
	
	@Before
	public void initObservables() {
		o1 = new Observable();
		o2 = new Observable();
	}

	@Test
	public void testMutate() {
		NoopObserver noopA = new NoopObserver();
		NoopObserver noopB = new NoopObserver();		
		assertEquals(0, o1.countObservers());
		
		o1.addObserver(noopA);
		assertEquals(1, o1.countObservers());
		
		o1.deleteObserver(noopB);
		assertEquals(1, o1.countObservers());
		
		o1.deleteObserver(noopA);
		assertEquals(0, o1.countObservers());
		
		o1.addObserver(noopA);
		o1.addObserver(noopA);
		assertEquals(1, o1.countObservers());
		
		o1.addObserver(noopB);
		assertEquals(2, o1.countObservers());
		
		o1.deleteObservers();
		assertEquals(0, o1.countObservers());
	}
	
	@Test
	public void testChanged() {
		assertFalse(o1.hasChanged());
		
		o1.setChanged();
		assertTrue(o1.hasChanged());
		
		o1.clearChanged();
		assertFalse(o1.hasChanged());
	}

	
	@Test
	public void testNotify() {
		
	}
	
	
	public class NoopObserver implements Observer {
		@Override
		public void update(Observable o, Object arg) {
			// noop			
		}
	}
	
}
