package com.twitstreet.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;

public class Util {

	public static java.sql.Date toSqlDate(java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}

	private static String convertToHex(byte[] data) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			int halfbyte = (data[i] >>> 4) & 0x0F;
			int two_halfs = 0;
			do {
				if ((0 <= halfbyte) && (halfbyte <= 9))
					buf.append((char) ('0' + halfbyte));
				else
					buf.append((char) ('a' + (halfbyte - 10)));
				halfbyte = data[i] & 0x0F;
			} while (two_halfs++ < 1);
		}
		return buf.toString();
	}

	public static String MD5(String text) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		MessageDigest md;
		md = MessageDigest.getInstance("MD5");
		byte[] md5hash = new byte[32];
		md.update(text.getBytes("iso-8859-1"), 0, text.length());
		md5hash = md.digest();
		return convertToHex(md5hash);
	}

	/**
	 * Remove/collapse multiple spaces.
	 * 
	 * @param argStr
	 *            string to remove multiple spaces from.
	 * @return String
	 */
	public static String collapseSpaces(String argStr) {
		if (argStr != null) {
			char last = argStr.charAt(0);
			StringBuffer argBuf = new StringBuffer();

			for (int cIdx = 0; cIdx < argStr.length(); cIdx++) {
				char ch = argStr.charAt(cIdx);
				if (ch != ' ' || last != ' ') {
					argBuf.append(ch);
					last = ch;
				}
			}

			return argBuf.toString();
		} else {
			return "";
		}
	}

	public static String commaSep(double amount) {
		DecimalFormat decimalFormatter = new DecimalFormat("#,###,###.00");
		String formatted = decimalFormatter.format(amount);
		if (formatted.startsWith(".")) {
			formatted = "0" + formatted;
		}
		return formatted;
	}

	public static String commaSep(int amount) {
		DecimalFormat decimalFormatter = new DecimalFormat("#,###,###");
		return decimalFormatter.format(amount);
	}

	public static String convertStringToValidURL(String str)
			throws UnsupportedEncodingException {

		String newStr = "";

		newStr = URLEncoder.encode(str, "UTF-8");

		return newStr;
	}

}
