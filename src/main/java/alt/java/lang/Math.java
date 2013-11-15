package alt.java.lang;

import alt.java.util.Random;

public class Math {
	public static final double E = 2.7182818284590452354;
	public static final double PI = 3.14159265358979323846;
	
	
	private static Random random; 
	public static synchronized double random() {
		if (random == null) {
			random = new Random();
		}
		return random.nextDouble();
	}
	
	public static int abs(int a) {
		return (a < 0) ? -a : a;
	}
	public static long abs(long a) {
		return (a < 0) ? -a : a;
	}
	public static float abs(float a) {
		return (a < 0) ? -a : a;
	}
	public static double abs(double a) {
		return (a < 0) ? -a : a;
	}
	
	public static int max(int a, int b) {
		return (a >= b) ? a : b;
	}
	public static long max(long a, long b) {
		return (a >= b) ? a : b;
	}
	public static float max(float a, float b) {
		return (a >= b) ? a : b;
	}
	public static double max(double a, double b) {
		return (a >= b) ? a : b;
	}
	
	public static int min(int a, int b) {
		return (a >= b) ? b : a;
	}
	public static long min(long a, long b) {
		return (a >= b) ? b : a;
	}
	public static float min(float a, float b) {
		return (a >= b) ? b : a;
	}
	public static double min(double a, double b) {
		return (a >= b) ? b : a;
	}
}
