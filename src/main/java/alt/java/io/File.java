package alt.java.io;

public class File {

	public static final String separator = System.getProperty("file.separator");
	public static final char separatorChar = separator.charAt(0);
	public static final String pathSeparator = System.getProperty("path.separator");
	public static final char pathSeparatorChar = pathSeparator.charAt(0);
	
	private File parent;
	private String name;
	
	public File(String name) throws NullPointerException {
		this((File) null, name);
	}
	public File(String dirname, String name) throws NullPointerException {
		this(new File(dirname), name);
	}
	public File(File dir, String name) throws NullPointerException {
		if (name == null) {
			throw new NullPointerException("name cannot be null");
		}
		this.parent = dir;
		this.name = name;
	}
	
	public String toString() {
		return getPath();
	}
	public boolean equals(Object obj) {
		if ((obj != null) && (obj instanceof File)) {
			File that = (File) obj;
			if (this.getAbsolutePath().equals(that.getAbsolutePath())) {
				return true;
			}
		}
		return false;
	}
	
	public int hashCode() {
		return this.getPath().hashCode() ^ 1234321;
	}
	public String getName() {
		String name = getPath();
		int pos = name.lastIndexOf(separatorChar);
		if (pos > 0) {
			name = name.substring(pos);
		}
		return name;
	}
	public String getPath() {
		return name;
	}
	
	public String getAbsolutePath() {
		if (parent == null) {
			return getPath();
		} else {
			return parent.getAbsolutePath() + pathSeparator + getPath();
		}
	}
	public String getParent() {
		return parent.getName();
	}
	public boolean isAbsolute() {
		return (parent == null);
	}
	
	private void checkRead() throws SecurityException {
		SecurityManager secman = System.getSecurityManager();
		if (secman != null) {
			secman.checkRead(name);
		}
	}
	
	private void checkWrite() throws SecurityException {
		SecurityManager secman = System.getSecurityManager();
		if (secman != null) {
			secman.checkWrite(name);
		}
	}
	private void checkDelete() throws SecurityException {
		SecurityManager secman = System.getSecurityManager();
		if (secman != null) {
			secman.checkDelete(name);
		}
	}
		
	
	public boolean exists() throws SecurityException {
		checkRead();
		return exists0();
	}
	private native boolean exists0();
		
	public boolean canRead() throws SecurityException {
		checkRead();
		return canRead0();
	}
	private native boolean canRead0();
	
	public boolean isFile() throws SecurityException {
		checkRead();
		
		return isFile0();
	}
	private native boolean isFile0();
	
	public boolean isDirectory() throws SecurityException {
		checkRead();
		
		return isDirectory0();
	}
	private native boolean isDirectory0();
	
	public long lastModified() throws SecurityException {
		checkRead();
		
		return 0;
	}
	
	public long length() throws SecurityException {
		checkRead();
		
		return 0;
	}
	
	public boolean mkdir() throws SecurityException {
		checkWrite();
		
		return false;
	}
	
	public boolean mkdirs() throws SecurityException {
		checkWrite();
	
		String parent = getParent();
		if (parent != null) {
			File parentFile = new File(parent);
			if (!parentFile.mkdirs()) {
				return false;
			}
		}
		return mkdir();
	}
	
	public String[] list() throws SecurityException {
		checkRead();
		
		
		return new String[] {};
	}
	
	public String[] list(FilenameFilter filter) {
		String[] list = list();
		
		// How many files match?
		int matches = 0;
		for(int i = 0; i < list.length; i++) {
			if (filter.accept(this, list[i])) {
				matches++;
			}
		}
		
		// Build the array of matches
		String[] rv = new String[matches];
		int pos = 0;
		for(int i = 0; i < list.length; i++) {
			if (filter.accept(this, list[i])) {
				rv[pos] = list[i];
				pos++;
			}
		}
		
		return rv;
	}
	
	public boolean renameTo(File dest) throws SecurityException {
		if (dest == null) {
			throw new NullPointerException();
		}
		checkWrite();
		dest.checkWrite();
		
		return false;
	}
	
	public boolean delete() throws SecurityException {
		checkDelete();
		
		return false;
	}
	
}
