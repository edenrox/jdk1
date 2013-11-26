package test.java.util;

import static org.junit.Assert.*;

import org.junit.Test;

import alt.java.util.Random;

public class RandomTest {

	@Test
	public void testRandomRange() {
		
		Random r = new Random();
		for(int i = 0; i < 100; i++) {
			float rnd = r.nextFloat();
			assertTrue(rnd >= 0.0);
			assertTrue(rnd <= 1.0);
			
			int irnd = r.nextInt(i+1);
			assertTrue(irnd >= 0);
			assertTrue(irnd < i+1);
			
			double drnd = r.nextDouble();
			assertTrue(drnd >= 0.0);
			assertTrue(drnd <= 1.0);
		}
	}
	
	@Test
	public void testRepeatable() {
		Random r1 = new Random(15);
		Random r2 = new Random(15);
		for(int i = 0; i < 100; i++) {
			assertEquals(r1.nextInt(), r2.nextInt());
		}
	}

}
