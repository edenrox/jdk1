package java.lang;

public class Float extends Number {
	public static final float MIN_VALUE = 1.4e-45f;
	public static final float MAX_VALUE = 3.4028235e+38f;
	public static final float NEGATIVE_INFINITY = -1.0f/0.0f;
	public static final float POSITIVE_INFINITY = 1.0f/0.0f;
	public static final float NaN = 0.0f/0.0f;
	
	public float value;
	
	public Float(float value) {
		this.value = value;
	}
}
