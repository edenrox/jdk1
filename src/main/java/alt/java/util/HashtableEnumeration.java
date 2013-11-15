package alt.java.util;

public class HashtableEnumeration implements Enumeration {
	
	private static final int TYPE_ENTRIES = 0;
	private static final int TYPE_KEYS = 1;
	private static final int TYPE_VALUES = 2;
	
	private HashtableEntry[] table;
	private HashtableEntry cur;
	private int index;
	private int type; 
	
	public HashtableEnumeration(HashtableEntry[] table) {
		this.table = table;
		this.cur = null;
		this.index = 0;
		this.type = TYPE_ENTRIES;
	}
	
	public void makeKeyEnumeration() {
		this.type = TYPE_KEYS;
	}
	
	public void makeValueEnumeration() {
		this.type = TYPE_VALUES;
	}
	
	public boolean hasMoreElements() {
		if (cur != null) {
			return true;
		}
		while (cur == null) {
			if (index >= table.length) {
				return false;
			}
			cur = table[index];
			index++;
		}
		return true;
	}
	
	public Object nextElement() 
			throws NoSuchElementException {
		if (!hasMoreElements()) {
			throw new NoSuchElementException("no more elements");
		}
		HashtableEntry rv = cur;
		rv = rv.getNext(); // advance to the next item
		
		switch (type) {
		case TYPE_KEYS:
			return cur.getKey();
		case TYPE_VALUES:
			return cur.getValue();
		case TYPE_ENTRIES:
		default: 
			return cur;	
		}
		
	}
	

}
