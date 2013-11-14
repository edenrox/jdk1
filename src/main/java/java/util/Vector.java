package java.util;

public class Vector
	implements Cloneable {
	
	private static final String NULL_STRING = "null";
	private static final int DEFAULT_CAPACITY = 10;
	private static final int INDEX_NOT_FOUND = -1;
	
	protected Object[] elementData;
	protected int elementCount;
	protected int capacityIncrement;
	
	public Vector(int initialCapacity, int capacityIncrement) {
		init(initialCapacity, capacityIncrement);
	}
	
	public Vector(int initialCapacity) {
		init(initialCapacity, 0);
	}
	
	public Vector() {
		init(DEFAULT_CAPACITY, 0);
	}
	
	private final void init(int initialCapacity, int capacityIncrement) {
		this.elementData = new Object[initialCapacity];
		this.elementCount = 0;
		this.capacityIncrement = 0;
	}
	
	public final String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (int i = 0; i < size(); i++) {
			Object item = elementAt(i);
			if (item == null) {
				sb.append(NULL_STRING);
			} else {
				sb.append(item.toString());
			}
		}
		sb.append("]");
		
		return sb.toString();
	}
	
	public Object clone() {
		Vector rv = new Vector(elementCount);
		System.arraycopy(elementData, 0, rv.elementData, 0, elementCount);
		return rv;
	}
	
	public final Object elementAt(int index)
			throws IndexOutOfBoundsException {
		checkIndex(index);
		return elementData[index];
	}
	public final void setElementAt(Object obj, int index)
			throws IndexOutOfBoundsException {
		checkIndex(index);
		elementData[index] = obj;
	}
	public final Object firstElement() 
			throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return elementAt(0);
	}
	
	public final Object lastElement()
			throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		int lastIndex = elementCount - 1;
		return elementAt(lastIndex);
	}
	
	public final void addElement(Object obj) {
		ensureCapacity(elementCount + 1);
		
		elementData[elementCount] = obj;
		elementCount++;
	}
	
	public final void insertElementAt(Object obj, int index) 
			throws IndexOutOfBoundsException {
		
		if (index == elementCount) {
			// add at the end of the list
			addElement(obj);
		} else {
			// Otherwise check the index and add in the middle of the list
			checkIndex(index);
			ensureCapacity(elementCount + 1);
			
			elementCount++;
			for(int i = elementCount; i > index; i--) {
				elementData[i] = elementData[i-1];
			}
			elementData[index] = obj;
		}
	}
	
	public final boolean removeElement(Object o) {
		int index = indexOf(o);
		if (index >= 0) {
			removeElementAt(index);
			return true;
		} else {
			return false;
		}
	}
	
	public final void removeElementAt(int index) 
			throws IndexOutOfBoundsException {
		checkIndex(index);
		
		elementCount--;
		for(int i = index; i < elementCount; i++) {
			elementData[i] = elementData[i+1];
		}
	}
	
	public final void removeAllElements() {
		elementCount = 0;
	}
	
	public final boolean isEmpty() {
		return (size() == 0);
	}
	
	public final int size() {
		return elementCount;
	}
	
	public final void setSize(int newSize) {
		ensureCapacity(newSize);
		elementCount = newSize;
	}
	
	public final int capacity() {
		return elementData.length;
	}
	public final void ensureCapacity(int minCapacity) {
		if (capacity() < minCapacity) {
			
		}
	}
	
	public final void trimToSize() {
		if (capacity() != size()) {
			Object[] oldElementData = elementData;
			elementData = new Object[elementCount];
			System.arraycopy(oldElementData, 0, elementData, 0, elementCount);
		}
	}
	
	public final void copyInto(Object anArray[])
			throws IndexOutOfBoundsException, NullPointerException {
		if (anArray == null) {
			throw new NullPointerException("destination array cannot be null");
		}
		if (anArray.length < size()) {
			throw new IndexOutOfBoundsException("destination array is not big enough to hold all items");
		}
		System.arraycopy(elementData, 0, anArray, 0, elementCount);
	}
	
	public Enumeration elements() {
		return new VectorEnumeration(this);
	}
	
	public boolean contains(Object elem) {
		return (indexOf(elem) != INDEX_NOT_FOUND);
	}
	
	public int indexOf(Object elem) {
		return indexOf(elem, 0);
	}
	
	public int indexOf(Object elem, int index) 
			throws IndexOutOfBoundsException {
		checkIndex(index);
		for(int i = index; i < elementCount; i++) {
			if (elem == null) {
				if (elementData[i] == null) {
					return i;
				}
			} else if (elem.equals(elementData[i])) {
				return i;
			}
		}
		return INDEX_NOT_FOUND;
	}
	
	public int lastIndexOf(Object elem) {
		return lastIndexOf(elem, size() - 1);
	}
	
	public int lastIndexOf(Object elem, int index)
			throws IndexOutOfBoundsException {
		checkIndex(index);
		for(int i = index; i >= 0; i--) {
			if (elem == null) {
				if (elementData[i] == null) {
					return i;
				}
			} else if (elem.equals(elementData[i])) {
				return i;
			}
		}
		return INDEX_NOT_FOUND;
	}
	
	protected void checkIndex(int index)
			throws IndexOutOfBoundsException {
		if (index < 0) {
			throw new IndexOutOfBoundsException("index cannot be negative");
		}
		if (index >= elementCount) {
			throw new IndexOutOfBoundsException("index cannot be >= size()");
		}
	}
}