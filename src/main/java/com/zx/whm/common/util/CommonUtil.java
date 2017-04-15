package com.zx.whm.common.util;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CommonUtil {
	
	//通过生日计算年龄 Timestamp
	public static String getAge(Timestamp tsTime) throws Exception {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String str = df.format(tsTime);
		Date d = Date.valueOf(str);

		Calendar cal = Calendar.getInstance();
		if (cal.before(d)) {
			throw new IllegalArgumentException(
					"The birthDay is before Now.It's unbelievable!");
		}
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(d);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				// monthNow>monthBirth
				age--;
			}
		}
		return age + "";
	}
	//通过生日计算年龄 string--yyyyMMDD
	public static String getAge(String  str) throws Exception {

		   String  str1 = str.substring(0,4) ;
   		   String  str2 = str.substring(4,6) ;
   		   String  str3 = str.substring(6, 8) ;
   		str=str1+"-"+str2+"-"+str3 ;
   	    Date date = Date.valueOf(str);

		Calendar cal = Calendar.getInstance();
		if (cal.before(date)) {
			throw new IllegalArgumentException(
					"The birthDay is before Now.It's unbelievable!");
		}
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(date);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				// monthNow>monthBirth
				age--;
			}
		}
		return age + "";
	}
	//Validate String is null
	public static boolean isNull(String value){
		return value == null || "".equals(value.trim());
	}
	//Validate String is NonNull
	public static boolean isNonNull(String value){
		return !isNull(value);
	}

	//Validate Object is NonNull
	public static boolean isNullObj(Object value){
		if(value instanceof  String){
			return value == null || "".equals(value.toString().trim());
		}
		return  value == null;
	}

	//Validate Object is NonNull
	public static boolean isNonNullObj(Object value){
		return !isNullObj(value);
	}

	public static String transferEncode(String resource) throws UnsupportedEncodingException {
		return new String(resource.getBytes("ISO-8859-1"),"UTF-8");
	}
}
