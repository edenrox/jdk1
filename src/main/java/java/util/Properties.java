package java.util;

import java.io.InputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.OutputStream;

public class Properties extends Hashtable {

	protected Properties defaults;
	
	public Properties() {
		defaults = null;
	}
	
	public Properties(Properties defaults) {
		this.defaults = defaults;
	}
	
	public String getProperty(String key) {
		return getProperty(key, null);
	}
	
	public String getProperty(String key, String defaultValue) {
		if (contains(key)) {
			return getString(key);
		} else if (defaults != null) {
			return defaults.getProperty(key);
		} else {
			return defaultValue;
		}
	}
	
	private String getString(String key) {
		return (String) get(key);
	}
	
	public Enumeration propertyNames() {
		return keys();
	}
	
	public void load(InputStream in) 
			throws IOException {
		throw new UnsupportedOperationException();
	}
	
	public void save(OutputStream out, String header)
			throws IOException {
		throw new UnsupportedOperationException();
	}
	
	public void list(PrintStream out) {
	
		// get the property names
		Enumeration propNames = propertyNames();
		
		while (propNames.hasMoreElements()) {
			String key = (String) propNames.nextElement();
			
			// output this property
			out.print(key);
			out.print(": ");
			out.print(valueTruncated(key));
			out.print("\n");
		}
	}
	
	private String valueTruncated(String key) {
		String rv = getProperty(key);
		if (rv.length() > 40) {
			return rv.substring(0, 37) + "...";
		}
		return rv;
	}
}