package java.util;

public class Stack extends Vector {

	public Object push(Object item) {
		addElement(item);
		return item;
	}
	
	public Object pop()
			throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		Object rv = lastElement();
		this.elementCount--;
		return rv;
	}
	
	public Object peek()
			throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return lastElement();
	}
	
	public boolean empty() {
		return isEmpty();
	}
	
	public int search(Object o) {
		return size() - indexOf(o) + 1;
	}

}
