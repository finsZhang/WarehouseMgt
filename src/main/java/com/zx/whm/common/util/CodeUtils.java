package com.zx.whm.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * 功能描述:编码转换工具
 *
 * @author fangll
 */
public class CodeUtils {


	/**
	 * 功能描述:字符串转换成十六进制值
	 *
	 * @param bin
	 * @return fangll 2013-1-11下午04:21:19
	 */
	public static String string2HexString(String str) {
		char[] digital = "0123456789ABCDEF".toCharArray();
		StringBuffer sb = new StringBuffer("");
		byte[] bs = str.getBytes();
		int bit;
		for (int i = 0; i < bs.length; i++) {
			bit = (bs[i] & 0x0f0) >> 4;
			sb.append(digital[bit]);
			bit = bs[i] & 0x0f;
			sb.append(digital[bit]);
		}
		return sb.toString();
	}


	/**
	 * 十六进制串转换字符串
	 *
	 * @param hexStr 十六进制
	 * @return String 转换后的字符串
	 */
	public static String hexString2String(String hexStr) {

		String digital = "0123456789ABCDEF";
		char[] hex2char = hexStr.toCharArray();
		byte[] bytes = new byte[hexStr.length() / 2];
		int temp;
		for (int i = 0; i < bytes.length; i++) {
			temp = digital.indexOf(hex2char[2 * i]) * 16;
			temp += digital.indexOf(hex2char[2 * i + 1]);
			bytes[i] = (byte) (temp & 0xff);
		}
		return new String(bytes);
	}


	/**
	 *
	 * 功能描述: byte转成16进制字符串
	 * @param src
	 * @return fangll 2013-1-9下午05:38:43
	 */
	/*
	public static String byte2HexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) { return null; }
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}	
	*/

	/**
	 * java字节码转16进制字符串
	 *
	 * @param b
	 * @return
	 */

	public static String byte2HexString(byte[] b) { // 一个字节的数
		// 转成16进制字符串
		String hs = "";
		String tmp = "";
		for (int n = 0; n < b.length; n++) {
			// 整数转成十六进制表示
			tmp = (Integer.toHexString(b[n] & 0XFF));
			if (tmp.length() == 1) {
				hs = hs + "0" + tmp;
			} else {
				hs = hs + tmp;
			}
		}

		tmp = null;
		return hs.toUpperCase(); // 转成大写
	}


	public static String byte2HexString(byte[] b, int startOffset, int length) { // 一个字节的数
		// 转成16进制字符串
		String hs = "";
		String tmp = "";
		for (int n = startOffset; n < length; n++) {
			// 整数转成十六进制表示
			tmp = (Integer.toHexString(b[n] & 0XFF));
			if (tmp.length() == 1) {
				hs = hs + "0" + tmp;
			} else {
				hs = hs + tmp;
			}
		}

		tmp = null;
		return hs.toUpperCase(); // 转成大写
	}

	/**
	 * 字符串转java字节码
	 *
	 * @param b
	 * @return
	 */

	public static byte[] hexString2Byte(byte[] b) {
		if ((b.length % 2) != 0) {
			throw new IllegalArgumentException("长度不是偶数");
		}

		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			// 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个进制字节
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		b = null;
		return b2;
	}


	/**
	 * 功能描述:格式化时间
	 *
	 * @param Value
	 * @return fangll 2013-1-14下午03:53:42
	 */
	public static String GetGMTDateTime(long value) {
		String dateStr = "";
		int iDay, iHour, iMin, iSec;
		int RInt = 0;//闰年数
		int Year = 1970;
		int Month = 1;
		int Day = 1;
		Year = Year + (int) value / (365 * 24 * 60 * 60);
		iDay = (int) ((value % (365 * 24 * 60 * 60)) / (24 * 60 * 60));
		for (int i = 1970; i < Year; i++) {
			if (i % 4 == 0) {
				RInt = RInt + 1;
			}
		}
		iHour = (int) (value % (24 * 60 * 60) / (60 * 60));
		iMin = (int) (value % (60 * 60) / 60);
		iSec = (int) (value % 60);
		try {
			SimpleDateFormat simple = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			dateStr = Year + "-" + (Month < 10 ? "0" + Month : "" + Month)
					+ "-" + (Day < 10 ? "0" + Day : "" + Day) + " "
					+ (iHour < 10 ? "0" + iHour : iHour) + ":"
					+ (iMin < 10 ? "0" + iMin : iMin) + ":"
					+ (iSec < 10 ? "0" + iSec : iSec);
			Calendar date = Calendar.getInstance();
			date.setTime(simple.parse(dateStr));
			date.set(Calendar.DATE, date.get(Calendar.DATE) + (iDay - RInt));
			dateStr = simple.format(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateStr;
	}

	/**
	 * 功能描述:16进制转10进制
	 * Integer.parse(value,16) 方法,值很大时会报溢出异常
	 *
	 * @param hexstr
	 * @return fangll 2013-1-14下午04:13:24
	 */
	public static long hexTo10(String hexstr) {
		long result = 0;
		for (char c : hexstr.toCharArray())
			result = result * 16 + Character.digit(c, 16);
		return result;
	}

	/**
	 * 功能描述: 10进制转16进制
	 *
	 * @param x
	 * @return fangll 2013-1-14下午04:15:29
	 */
	public static String tenToHex(int x) {
		return Integer.toHexString(x).toUpperCase();
	}

	/**
	 * 功能描述:16进制字符串转byte[]
	 *
	 * @param hexString
	 * @return fangll 2013-1-15下午05:49:49
	 */
	public static byte[] hexString2Bytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	/**
	 * Convert char to byte
	 *
	 * @param c char
	 * @return byte
	 */
	public static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	/**
	 * 功能描述:将指定byte数组以16进制的形式打印到控制台
	 *
	 * @param b fangll 2013-1-15下午06:55:39
	 */
	public static void printHexString(byte[] b) {
		System.out.print("hexstr:");
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			System.out.print(hex.toUpperCase());
		}
		System.out.print("\n");
	}

	public static void main(String[] args) {

		String content = "这里有中文EDF%&^%#_|~";
		System.out.println(string2HexString(content));
		System.out.println(hexString2String(string2HexString(content)));
		System.out.println(hexTo10("00010E43"));  //A6		
		System.out.println(tenToHex(1008566646));  //A6	110,122
		System.out.println(tenToHex(122));  //A6	110,122
		System.out.println(CodeUtils.GetGMTDateTime(Integer.parseInt("1886E7F1", 16) + 946656000));

	}
	public static String subString(String attrValue,int maxLenth) {
		String attrStr = attrValue;
		if(attrStr != null && attrStr.length() > 0){
			byte[] attrBytes = attrValue.getBytes();
			if(attrBytes.length > maxLenth){
				byte[] subAttrBytes = new byte[maxLenth];
				for(int i = 0; i < maxLenth; i++){
					subAttrBytes[i] = attrBytes[i];
				}
				String subStr=new String(subAttrBytes);
				int subStrLen = subStr.length();
				if(attrStr.substring(0, subStrLen).getBytes().length > maxLenth){
					attrStr = attrValue.substring(0, subStrLen -1);
				}else{
					attrStr = attrValue.substring(0, subStrLen);
				}
			}
		}
		return attrStr;
	}
}