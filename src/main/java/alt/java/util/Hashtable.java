package alt.java.util;

public class Hashtable extends Dictionary implements Cloneable {

	private static final float DEFAULT_LOAD_FACTOR = 0.75f;
	
	protected float maxLoadFactor;
	protected int count;
	protected HashtableEntry[] table;
	
	public Hashtable() {
		init(10, DEFAULT_LOAD_FACTOR);
	}
	
	public Hashtable(int initialCapacity) {
		init(initialCapacity, DEFAULT_LOAD_FACTOR);
	}
	
	public Hashtable(int initialCapacity, float loadFactor) {
		init(initialCapacity, loadFactor);
	}
	
	public void init(int initialCapacity, float loadFactor) {
		this.table = new HashtableEntry[initialCapacity];
		this.count = 0;
		this.maxLoadFactor = loadFactor;
	}


	public Object clone() {
		Hashtable rv = new Hashtable(table.length, maxLoadFactor);
		Enumeration keys = keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			rv.put(key, get(key));
		}
		return rv;
	}

	public int size() {
		return count;
	}

	public boolean isEmpty() {
		return (size() == 0);
	}
	
	private int indexForKey(Object key) {
		return Math.abs(key.hashCode()) % table.length;
	}
	
	public Object get(Object key) 
			throws NullPointerException {
		if (key == null) {
			throw new NullPointerException("Cannot use a null key");
		}
		if (isEmpty()) {
			return null;
		}
		int index = indexForKey(key);
		
		HashtableEntry entry = table[index];
		while (entry != null) {
			if (entry.getKey().equals(key)) {
				return entry.getValue();
			}
			entry = entry.getNext();
		}
		return null;
	}
	
	public Object put(Object key, Object element)
			throws NullPointerException {
		if (key == null) {
			throw new NullPointerException("Cannot use a null key");
		}
		if (element == null) {
			throw new NullPointerException("Cannot use a null value");
		}
		if (table.length == 0) {
			rehash();
		}
		int index = indexForKey(key);
		
		// See if this key exists in our hashtable
		HashtableEntry matchEntry = null;
		HashtableEntry entry = table[index];
		while (entry != null) {
			if (entry.getKey().equals(key)) {
				matchEntry = entry;
				entry = null;
			} else {
				entry = entry.getNext();
			}
		}
		
		if (matchEntry != null) {
			// Replace the old value
			matchEntry.setValue(element);
		} else {
			// Add a new value
			table[index] = new HashtableEntry(key, element, table[index]);
			count++;
			
			float loadFactor = ((float) count) / table.length;
			if (loadFactor > maxLoadFactor) {
				rehash();
			}
		}
		
		return element;
	}
	
	public Object remove(Object key)
			throws NullPointerException {
		if (key == null) {
			throw new NullPointerException("Cannot use a null key");
		}
		if (isEmpty()) {
			return null;
		}
		
		int index = indexForKey(key);
		HashtableEntry prevEntry = null;
		HashtableEntry entry = table[index];
		while (entry != null) {
			if (entry.getKey().equals(key)) {
				if (prevEntry == null) {
					// first entry in the list
					table[index] = entry.getNext();
				} else {
					// not the first entry in the list
					prevEntry.setNext(entry.getNext());
				}
				count--;
				return entry.getValue();
			}
			prevEntry = entry;
			entry = entry.getNext();
		}
		return null;
	}
	
	public boolean containsKey(Object key) {
		if (key == null) {
			return false;
		}
		return (get(key) != null);
	}
	
	public boolean contains(Object o) {
		if (o == null) {
			return false;
		}
		if (isEmpty()) {
			return false;
		}
		Enumeration elements = elements();
		while(elements.hasMoreElements()) {
			Object element = elements.nextElement();
			if (o.equals(element)) {
				return true;
			}
		}
		return false;
	}
	
	protected void rehash() {
		Enumeration entries = entries();
		int newSize = table.length;
		if (newSize < 5) {
			newSize = 10;
		} else {
			newSize += Math.min(newSize, 64);
		}
		table = new HashtableEntry[newSize];
		while (entries.hasMoreElements()) {
			HashtableEntry entry = (HashtableEntry) entries.nextElement();
			int index = entry.getKey().hashCode() % newSize;
			entry.setNext(table[index]);
			table[index] = entry;
		}
	}
	
	public void clear() {
		for(int i = 0; i < table.length; i++) {
			table[i] = null;
		}
		count = 0;
	}

	public Enumeration keys() {
		HashtableEnumeration rv = new HashtableEnumeration(table);
		rv.makeKeyEnumeration();
		return rv;
	}
	
	public Enumeration elements() {
		HashtableEnumeration rv = new HashtableEnumeration(table);
		rv.makeValueEnumeration();
		return rv;
	}
	
	private Enumeration entries() {
		return new HashtableEnumeration(table);
	}
	

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		
		Enumeration entries = entries();
		while(entries.hasMoreElements()) {
			if (sb.length() > 1) {
				sb.append(", ");
			}
			HashtableEntry entry = (HashtableEntry) entries.nextElement();
			sb.append(entry.toString());
		}
		
		sb.append("}");
		return sb.toString();
	}
	

}