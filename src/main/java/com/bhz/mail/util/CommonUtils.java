package com.bhz.mail.util;

import java.util.Calendar;

/**
 * :根据身份证号，计算年龄
 * 
 * @author admin
 *
 */
public class CommonUtils {

	/**
	 * 根据身份证号码计算年龄
	 * 
	 * @param IDCardNum
	 * @return
	 */
	public static int getAge(String IDCardNum) {
		int year, month, day, idLength = IDCardNum.length();
		Calendar cal1 = Calendar.getInstance();
		Calendar today = Calendar.getInstance();
		if (idLength == 18) {
			year = Integer.parseInt(IDCardNum.substring(6, 10));
			month = Integer.parseInt(IDCardNum.substring(10, 12));
			day = Integer.parseInt(IDCardNum.substring(12, 14));
		} else if (idLength == 15) {
			year = Integer.parseInt(IDCardNum.substring(6, 8)) + 1900;
			month = Integer.parseInt(IDCardNum.substring(8, 10));
			day = Integer.parseInt(IDCardNum.substring(10, 12));
		} else {
			System.out.println("This ID card number is invalid!");
			return -1;
		}
		cal1.set(year, month, day);
		return getYearDiff(today, cal1);
	}

	/**
	 * 获取两个日期之间相差几年
	 * 
	 * @param cal
	 * @param cal1
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static int getYearDiff(Calendar cal, Calendar cal1) {
		int m = (cal.get(Calendar.MONTH)) - (cal1.get(Calendar.MONTH));
		int y = (cal.get(Calendar.YEAR)) - (cal1.get(Calendar.YEAR));
		return (y * 12 + m) / 12;
	}

	public static void main(String[] args) {
		System.out.println(getAge("222222199006043333"));
		System.out.println(getAge("222222640604333"));
		System.out.println(getAge("2222226406043333"));
	}

}
