package alt.java.util;

public class ObjectArrayEnumeration implements Enumeration {
	private Object[] array;
	private int cur;
	
	public ObjectArrayEnumeration(Object[] array) {
		this.array = array;
		this.cur = 0;
	}
	
	public boolean hasMoreElements() {
		return cur < array.length;
	}
	
	public Object nextElement() {
		Object rv = array[cur];
		cur++;
		return rv;
	}
}
