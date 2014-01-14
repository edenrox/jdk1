package alt.java.util;

public class Date {
	
	private static final int EPOCH_DAY_OF_WEEK = 4; // Thursday
	private static final int EPOCH_YEAR = 70; // Jan 1, 1970
	private static final int YEAR_OFFSET = 1900;
	private static final int DAYS_PER_YEAR = 365;

	private static final long MILLIS_PER_YEAR = 365 * 24 * 60 * 60 * 1000L;
	private static final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000L;
	private static final long MILLIS_PER_HOUR = 60 * 60 * 1000L;
	private static final long MILLIS_PER_MINUTE = 60 * 1000L;
	private static final long MILLIS_PER_SECOND = 1000L;
	
	private static final String[] DAYS = new String[] {
		"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
	};
	
	private static final String[] MONTHS = new String[] {
		"Jan", "Feb", "Mar", "Apr", "May", "Jun",
		"Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
	};
	private static final int[] DAYS_PER_MONTH = new int[] {
		31, 28, 31, 30, 31, 30,
		31, 31, 30, 31, 30, 31
	};
	
	private static final String TIMEZONE_GMT = "GMT";
	
	private long timestamp;
	private boolean isTimestampValid;
	private boolean isFieldsValid;
	private int second;
	private int minute;
	private int hour;
	private int day;
	private int month;
	private int year;
	
	public Date() {
		this(System.currentTimeMillis());
	}
	
	public Date(long time) {
		this.timestamp = time;
		this.isTimestampValid = true;
		this.isFieldsValid = false;
	}
	
	public Date(int year, int month, int day) {
		this(year, month, day, 0, 0, 0);
	}
	
	public Date(int year, int month, int day, int hour, int minute, int second) {
		if ((month < 0) || (month >= MONTHS.length)) {
			throw new IllegalArgumentException("month must be between 0 and 11");
		}
		
		// Set the field values
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		this.isFieldsValid = true;
		
		// Calculate the timestamp
		calculateTimestamp();
	}
	
	private void calculateFields() {
		
		// Easy stuff
		second = (int) (timestamp / MILLIS_PER_SECOND) % 60;
		minute = (int) (timestamp / MILLIS_PER_MINUTE) % 60;
		hour = (int) (timestamp / MILLIS_PER_HOUR) % 24;
		
		// Hard stuff
		long days = (timestamp / MILLIS_PER_DAY);
		
		// Setup the epoch
		int curYear = EPOCH_YEAR;
		int curMonth = 0;
		
		// Roll back to the year
		while (days < 0) {
			curYear -= 1;
			if (isLeapYear(curYear)) {
				days += 1;
			}
			days += DAYS_PER_YEAR;
		}
		
		// Roll forward to the year
		while (true) {
			if (isLeapYear(curYear)) {
				if (days >= DAYS_PER_YEAR + 1) {
					curYear++;
					days -= DAYS_PER_YEAR + 1;
				} else {
					break;
				}
				
			} else {
				if (days >= DAYS_PER_YEAR) {
					curYear++;
					days -= DAYS_PER_YEAR;
				} else {
					break;
				}
			}
		}
		// Roll forward to the month
		for(int i = 0; i < 12; i++) {
			int daysInMonth = DAYS_PER_MONTH[i];
			if ((i == 1) && (isLeapYear(curYear))) {
				daysInMonth++;
			}
			if (days > daysInMonth) {
				days-= daysInMonth;
				curMonth++;
			} else {
				break;
			}
		}
		
		this.year = curYear;
		this.month = curMonth;
		this.day = (int) days + 1;
		
		isFieldsValid = true;
	}
	private void calculateTimestamp() {
		// Start at the epoch
		timestamp = 0;
		
		// Add the years
		if (year > EPOCH_YEAR) {
			for (int i = EPOCH_YEAR; i < year; i++) {
				if (isLeapYear(i)) {
					timestamp += MILLIS_PER_DAY;
				}
				timestamp += MILLIS_PER_YEAR;
			}
		} else {
			for (int i = EPOCH_YEAR - 1; i >= year; i--) {
				if (isLeapYear(i)) {
					timestamp -= MILLIS_PER_DAY;
				}
				timestamp -= MILLIS_PER_YEAR;
			}
		}
		
		// Add the months
		boolean isLeapYear = isLeapYear(year);
		for(int i = 0; i < month; i++) {
			if ((isLeapYear) && (i == 1)) {
				timestamp += MILLIS_PER_DAY;
			}
			timestamp += DAYS_PER_MONTH[i] * MILLIS_PER_DAY;
		}

		// Add the days
		timestamp += (day - 1) * (MILLIS_PER_DAY);
		
		timestamp += hour * MILLIS_PER_HOUR;
		timestamp += minute * MILLIS_PER_MINUTE;
		timestamp += second * MILLIS_PER_SECOND;
		
		isTimestampValid = true;
	}

	
	/* Get the day of the week */
	public int getDay() {
		if (!isTimestampValid) {
			calculateTimestamp();
		}
		return (int) (timestamp / MILLIS_PER_DAY + EPOCH_DAY_OF_WEEK) % 7;
	}
	
	public int getDate() {
		if (!isFieldsValid) {
			calculateFields();
		}
		return day;
	}
	
	public void setDate(int date) {
		this.day = date;
		isTimestampValid = false;
	}
	
	public int getMonth() {
		if (!isFieldsValid) {
			calculateFields();
		}
		return month;
	}
	
	public void setMonth(int month) {
		this.month = month;
		isTimestampValid = false;
	}
	
	private String getMonthAbbr() {
		return MONTHS[getMonth()];
	}
	
	public int getYear() {
		if (!isFieldsValid) {
			calculateFields();
		}
		return year;
	}
	
	public int getFullYear() {
		return getYear() + YEAR_OFFSET;
	}
	
	public void setYear(int year) {
		this.year = year;
		isTimestampValid = false;
	}
	
	public int getHours() {
		if (!isFieldsValid) {
			calculateFields();
		}
		return hour;
	}
	
	public void setHours(int hours) {
		this.hour = hours;
		isTimestampValid = false;
	}
	
	public int getMinutes() {
		if (!isFieldsValid) {
			calculateFields();
		}
		return minute;
	}
	
	public void setMinutes(int minutes) {
		this.minute = minutes;
		isTimestampValid = false;
	}
	
	public int getSeconds() {
		if (!isFieldsValid) {
			calculateFields();
		}
		return second;
	}
	
	public void setSeconds(int seconds) {
		this.second = seconds;
		isTimestampValid = false;
	}
	
	public int getTimezoneOffset() {
		return 0;
	}
	
	public long getTime() {
		if (!isTimestampValid) {
			calculateTimestamp();
		}
		return timestamp;
	}
	public void setTime(long time) {
		timestamp = time;
		isTimestampValid = true;
		isFieldsValid = false;
	}
	
	public boolean equals(Object obj) {
		if ((obj != null) &&(obj instanceof Date)) {
			Date that = (Date) obj;
			if (this.getTime() == that.getTime()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean before(Date when) {
		return (this.getTime() < when.getTime()); 
	}
	
	public boolean after(Date when) {
		return (this.getTime() > when.getTime());
	}
	
	public String toString() {
		return DAYS[getDay()] + " "
				+ MONTHS[getMonth()] + " "
				+ getDate() + " "
				+ twoDigit(getHours()) + ":"
				+ twoDigit(getMinutes()) + ":"
				+ twoDigit(getSeconds()) + " "
				+ TIMEZONE_GMT + " "
				+ getFullYear();
	}
	
	public String toLocaleString() {
		return DAYS[getDay()] + " "
				+ MONTHS[getMonth()] + " "
				+ getDate() + " "
				+ twoDigit(getHours()) + ":"
				+ twoDigit(getMinutes()) + ":"
				+ twoDigit(getSeconds()) + " "
				+ getFullYear();
	}
	
	public String toGMTString() {
		return getDate() + " "
				+ getMonthAbbr() + " "
				+ getFullYear() + " "
				+ twoDigit(getHours()) + ":"
				+ twoDigit(getMinutes()) + ":"
				+ twoDigit(getSeconds()) + " "
				+ TIMEZONE_GMT;
	}
	
	private String twoDigit(int num) {
		if (num < 10) {
			return "0" + Integer.toString(num);
		}
		return Integer.toString(num);
	}
	
	public int hashCode() {
		return (int) (this.getTime()^ (this.getTime()>>>32));
	}
	
	
	public static long UTC(int year, int month, int day, int hours, int minutes, int seconds) {
		return new Date(year, month, day, hours, minutes, seconds).getTime();
	}
	
	public static long parse(String s)
			throws IllegalArgumentException {
		
		throw new RuntimeException("Method not implemented");
	}
	
	private static boolean isLeapYear(int year) {
		year = year + YEAR_OFFSET;
		if (year % 4 == 0) {
			if (year % 100 == 0) {
				if (year % 400 == 0) {
					return true;
				}
				return false;
			}
			return true;
		}
		return false;
	}
	
}
