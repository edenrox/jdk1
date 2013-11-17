package com.hopkins.java.test;

import static org.junit.Assert.*;

public class ExceptionHelper {

	public static void assertThrows(Class<? extends Throwable> expected, Runnable actual) {
		try {
			actual.run();
			fail("Exception not thrown");
		} catch (Throwable thrown) {
			assertEquals(expected, thrown.getClass());
		}
	}
}
