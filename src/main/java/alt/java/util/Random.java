package alt.java.util;

public class Random {

	public Random() {
		setSeed(System.currentTimeMillis());
	}
	
	public Random(long seed) {
		setSeed(seed);
	}
	
	protected int next(int bits) {
		// Basically we need to use our 32 bit random nextInt
		// to generate an X-bit uniformly random number
		
		return -1;
	}
	
	public double nextDouble() {
		long numerator = (long) next(26) << 26 + next(26);
		long denominator = 1 << 52;
		
		return ((double) numerator) / denominator;
	}
	
	public float nextFloat() {
		int numerator = next(24);
		int denominator = 1 << 24;
		
		return ((float) numerator) / denominator;
	}
	
	public int nextInt() {
		return nextInt0();
	}
	
	protected native int nextInt0();
		// #include <stdlib.h>
		// return rand();
	
	public int nextInt(int n) throws IllegalArgumentException {
		if (n <= 0) {
			throw new IllegalArgumentException("n must be positive");
		}
		
		// TODO: implement this
		
		return -1;
	}
	
	public long nextLong() {
		return -1l;
	}
	
	public final void setSeed(long seed) {
		setSeed0((int) seed);
	}
	
	public final native void setSeed0(int seed);
		// #include <stdlib.h>
		// return srand(seed);
}
