package alt.java.util;

public class Date {
	private static final String[] MONTHS = new String[] {
		"Jan", "Feb", "Mar", "Apr", "May", "Jun",
		"Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
	};
	private static final String TIMEZONE_GMT = "GMT";
	
	private long time;
	
	public Date() {
		time = System.currentTimeMillis();
	}
	
	public Date(long time) {
		this.time = time;
	}
	
	public Date(int year, int month, int day, int hour, int minute, int second) {
		
	}

	
	public int getDay() {
		// day of week
		return 2;
	}
	
	public int getDate() {
		return 12;
	}
	
	public int getMonth() {
		return 1;
	}
	
	private String getMonthAbbr() {
		return MONTHS[getMonth()];
	}
	
	public int getYear() {
		return 2013;
	}
	
	public int getHour() {
		return 12;
	}
	
	public int getMinute() {
		return 45;
	}
	
	public int getSecond() {
		return 10;
	}
	
	public int getTimezoneOffset() {
		return 0;
	}
	
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	
	public boolean equals(Object obj) {
		if ((obj != null) &&(obj instanceof Date)) {
			Date that = (Date) obj;
			if (this.time == that.time) {
				return true;
			}
		}
		return false;
	}
	
	public boolean before(Date when) {
		return (this.time < when.time); 
	}
	
	public boolean after(Date when) {
		return (this.time > when.time);
	}
	
	public String toLocaleString() {
		return "";
	}
	
	public String toGMTString() {
		return getDate() + " "
				+ getMonthAbbr() + " "
				+ getYear() + " "
				+ getHour() + ":"
				+ getMinute() + ":"
				+ getSecond() + ":"
				+ TIMEZONE_GMT;
		
	}
	
	public int hashCode() {
		return (int) (this.getTime()^ (this.getTime()>>>32));
	}
	
	
	public static long UTC(int year, int month, int day, int hours, int minutes, int seconds) {
		return 1l;
	}
	
	public static long parse(String s)
			throws IllegalArgumentException {
		
		return 1l;
	}
	
}
