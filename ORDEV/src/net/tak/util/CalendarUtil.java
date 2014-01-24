package net.tak.util;

import java.sql.Timestamp;
import java.util.Calendar;

public class CalendarUtil {
	
	public static Timestamp plusTimestamp(Timestamp value, int type , int diff){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(value.getTime());
		calendar.add(type, diff);
		return new Timestamp(calendar.getTimeInMillis());
	}
	
	public static Timestamp minusTimestamp(Timestamp value, int type , int diff){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(value.getTime());
		calendar.add(type, -diff);
		return new Timestamp(calendar.getTimeInMillis());
	}
	
}
