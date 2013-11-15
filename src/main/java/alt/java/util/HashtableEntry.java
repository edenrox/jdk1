package alt.java.util;

public class HashtableEntry {

	private Object key;
	private Object value;
	private HashtableEntry next;
	
	public HashtableEntry(Object key, Object value, HashtableEntry next) {
		this.key = key;
		this.value = value;
		this.next = next;
	}
	
	public Object getKey() {
		return key;
	}
	
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	public HashtableEntry getNext() {
		return next;
	}
	
	public void setNext(HashtableEntry next) {
		this.next = next;
	}
	
	public void clearNext() {
		this.next = null;
	}
	
	public int hashCode() {
		return key.hashCode();
	}
	
	public String toString() {
		return key.toString() + "=" + value.toString();
	}
}


