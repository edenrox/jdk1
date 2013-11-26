package alt.java.util;

public class Random {
	
	private long seed;

	public Random() {
		setSeed(System.currentTimeMillis());
	}
	
	public Random(long seed) {
		setSeed(seed);
	}
	
	protected int next(int bits) {
		this.seed = (seed * 0x5DEECE66DL + 0xBL) & ((1L << 48) - 1);
		
		return (int) (seed >>> (48 - bits));
	}
	
	public double nextDouble() {
		long numerator = (((long) next(26)) << 26) + next(26);
		long denominator = 1L << 52;
		
		return numerator / ((double) denominator);
	}
	
	public float nextFloat() {
		int numerator = next(24);
		int denominator = 1 << 24;
		
		return ((float) numerator) / denominator;
	}
	
	public int nextInt() {
		return next(32);
	}
	
	public int nextInt(int n) throws IllegalArgumentException {
		if (n <= 0) {
			throw new IllegalArgumentException("n must be positive");
		}
		if ((n & -n) == n) {
		     return (int)((n * (long)next(31)) >> 31);
		}
		
		int bits, val;
		do {
			bits = next(31);
			val = bits % n;
		} while (bits - val + (n-1) < 0);
		return val;
	}
	
	public long nextLong() {
		return ((long)next(32) << 32) + next(32);
	}
	
	public synchronized void setSeed(long seed) {
		this.seed = (seed ^ 0x5DEECE66DL) & ((1L << 48) - 1);
	}

}
