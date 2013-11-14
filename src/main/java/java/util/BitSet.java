package java.util;

public class BitSet {
	private byte[] bytes;
	private int size;
	
	public BitSet() {
		size = 0;
		bytes = new byte[0];
	}
	
	public BitSet(int nbits) {
		size = nbits;
		
		int nbytes = nbits / 8;
		if (nbits % 8 > 0) {
			nbytes++;
		}
		bytes = new byte[nbytes];
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		for(int i = 0; i < size; i++) {
			if (get(i)) {
				if (sb.length() > 1) {
					sb.append(", ");
				}
				sb.append(i);
			}
		}
		
		sb.append("}");
		return sb.toString();
	}
	
	public boolean equals(Object obj) {
		if ((obj != null) && (obj instanceof BitSet)) {
			BitSet that = (BitSet) obj;
			if (this.size == that.size) {
				for(int i = 0; i < size; i++) {
					if (get(i) != that.get(i)) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}
	
	public int hashCode() {
		return 1234;
	}
	
	public Object clone() {
		BitSet rv = new BitSet(size);
		rv.or(this);
		return rv;
	}
	
	private void checkIndex(int bitIndex) 
			throws IndexOutOfBoundsException {
		if (bitIndex < 0) {
			throw new IndexOutOfBoundsException("bitIndex cannot be negative");
		}
		if (bitIndex >= size) {
			throw new IndexOutOfBoundsException("bitIndex cannot extend past size");
		}
	}
	
	public boolean get(int bitIndex)
			throws IndexOutOfBoundsException {
		checkIndex(bitIndex);
		
		int byteIndex = bitIndex / 8;
		int bitOffset = bitIndex % 8;
		
		byte rv = bytes[byteIndex];
		rv = (byte) (rv & (1 << bitOffset));
		
		return (rv > 0);
	}
	
	public void set(int bitIndex)
			throws IndexOutOfBoundsException {
		checkIndex(bitIndex);
		
		int byteIndex = bitIndex / 8;
		int bitOffset = bitIndex % 8;
		
		bytes[byteIndex] = (byte) (bytes[byteIndex] | (1 << bitOffset));
	}
	
	public void clear(int bitIndex)
			throws IndexOutOfBoundsException {
		checkIndex(bitIndex);
		
		int byteIndex = bitIndex / 8;
		int bitOffset = bitIndex % 8;
		
		bytes[byteIndex] = (byte) (bytes[byteIndex] & ~(1 << bitOffset));
	}
	
	public void and(BitSet set) {
		int len = Math.min(this.size, set.size);
		for(int i = 0; i < len; i++) {
			if (get(i) && set.get(i)) {
				set(i);
			} else {
				clear(i);
			}
		}
	}
	
	public void or(BitSet set) {
		int len = Math.min(this.size, set.size);
		for(int i = 0; i < len; i++) {
			if (get(i) || set.get(i)) {
				set(i);
			} else {
				clear(i);
			}
		}
	}
	
	public void xor(BitSet set) {
		int len = Math.min(this.size, set.size);
		for(int i = 0; i < len; i++) {
			if (get(i) ^ set.get(i)) {
				set(i);
			} else {
				clear(i);
			}
		}
	}
	
	public int size() {
		return size;
	}
}
