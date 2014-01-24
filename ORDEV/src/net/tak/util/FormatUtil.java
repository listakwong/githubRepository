package net.tak.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

public class FormatUtil {
	
	public static Timestamp stringToTimestamp(String value, String format){
		if(StringUtils.isEmpty(value)) {
			return null;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		
		try {
			return new Timestamp(sdf.parse(value).getTime());
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static java.util.Date stringToUtilDate(String value, String format){
		if(StringUtils.isEmpty(value)){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		
		try {
			return sdf.parse(value);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static java.util.Date stringToUtilDate(String value, String format,Locale locale){
		if(StringUtils.isEmpty(value)){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format,locale);
		
		try {
			return sdf.parse(value);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static java.sql.Date stringToSqlDate(String value, String format){
		if(StringUtils.isEmpty(value)){
			return null;
		}
		
		return new java.sql.Date(stringToUtilDate(value,format).getTime());
	}
	
	public static String utildateToString(java.util.Date value,String format){
		if(value == null) {
			return null;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(value);
	}
	
	public static String sqlDateToString(java.sql.Date value , String format){
		if(value == null) {
			return null;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(value);
	}
	
	public static String timestampToString(Timestamp value, String format){
		if(value == null) {
			return null;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		
		return sdf.format(value);
	}
	
	public static String bigDecimaltoString(BigDecimal value,String format) {
		if(value == null) {
			return null;
		}
		
		DecimalFormat df = new DecimalFormat(format);
		
		return df.format(value);
	}
	
	
}
