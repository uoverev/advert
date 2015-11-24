package com.advert.cms.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RegexUtils {

	/**
	 * 整数
	 */
	public static final String NUMBER = "^\\d+";
	/**
	 * 电子邮箱
	 */
	public static final String EMAIL = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
	/**
	 * 域名验证
	 */
	public static final String URL = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

	private static final Map<String, String> regexs = new HashMap<String, String>();
	static {
		regexs.put(NUMBER, NUMBER);
		regexs.put(EMAIL, EMAIL);
		regexs.put(URL, URL);
	}

	private static final Map<String, String> characters = new HashMap<String, String>();
	static {
		characters.put("<", "&lt;");
		characters.put(">", "&gt;");
		characters.put("'", "&#039;");
		characters.put("\"", "&quot;");
		characters.put("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");// 替换跳格
		characters.put(" ", "&nbsp;");// 替换空格
	};

	public static void main(String[] args) {
		System.out.println(number("12"));
	}

	public static boolean number(String val) {
		return regex(NUMBER, val);
	}

	public static boolean email(String val) {
		return regex(EMAIL, val);
	}

	public static boolean url(String val) {
		return regex(URL, val);
	}

	public static boolean regex(String regex, String val) {
		String regexStr = regexs.get(regex);
		if (isNull(regexStr)) {
			regexs.put(regex, regex);
		}
		return Pattern.matches(regex, val);
	}

	public static boolean required(String val) {
		return !isNull(val);
	}

	public static boolean isNull(String val) {
		int strLen;
		if (val == null || (strLen = val.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(val.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	public static String tranHtml(String val) {
		if (isNull(val)) {
			return val;
		}
		val = val.replaceAll("&", "&amp;");// 需要首先替换
		for (String key : characters.keySet()) {
			val = val.replaceAll(key, characters.get(key));
		}
		return val;

	}
}
