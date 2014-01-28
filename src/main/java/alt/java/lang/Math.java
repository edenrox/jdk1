package alt.java.lang;

import alt.java.util.Random;

public class Math {
	public static final double E = 2.7182818284590452354;
	public static final double PI = 3.14159265358979323846;
	
	public static native double acos(double a);
	public static native double asin(double a);
	public static native double atan(double a);
	public static native double atan2(double a, double b);
	public static native double cos(double a);
	public static native double exp(double a);
	public static native double IEEEremainder(double a, double b);
	public static native double log(double a);
	public static native double pow(double a, double b);
	public static native double sin(double a);
	public static native double sqrt(double a);
	public static native double tan(double a);
	
	public static int round(float a) {
		return (int)Math.floor(a + 0.5f);
	}
	public static long round(double a) {
		return (long)Math.floor(a + 0.5d);
	}
	
	public static double floor(double a) {
		if (a >= 0) {
			return ((long) a);
		} else {
			return ((long) a);
		}
	}
	
	public static double ceil(double a) {
		return -Math.floor(-a);
	}
	
	
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
