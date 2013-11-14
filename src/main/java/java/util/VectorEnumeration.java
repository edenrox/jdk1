package java.util;

public class VectorEnumeration
	implements Enumeration {
	
	private Vector vector;
	private int current;
	
	public VectorEnumeration(Vector v) {
		vector = v;
		current = 0;
	}
	
	public void reset() {
		current = 0;
	}
	
	public boolean hasMoreElements() {
		return (current < vector.size());
	}
	
	public Object nextElement()
			throws NoSuchElementException {
		if (!hasMoreElements()) {
			throw new NoSuchElementException();
		}
		Object rv = vector.elementAt(current);
		current++;
		return rv;
	}
	
}