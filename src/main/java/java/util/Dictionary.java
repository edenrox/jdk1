package java.util;

public abstract class Dictionary {
	public abstract int size();
	public abstract boolean isEmpty();
	public abstract Object get(Object key) throws NullPointerException;
	public abstract Object put(Object key, Object element) throws NullPointerException;
	public abstract Object remove(Object key) throws NullPointerException;
	public abstract Enumeration keys();
	public abstract Enumeration elements();
}