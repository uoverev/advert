package com.advert.cms.util;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

public abstract class StringUtil {

	private static final char[] CHARS = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	/**
	 * 随机生成字母字符串
	 * 
	 * @param length
	 *            - 字符串长度
	 * @return
	 */
	public static String randomString(int length) {
		Random random = new Random();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(CHARS.length);
			builder.append(CHARS[number]);
		}
		return builder.toString();
	}

	/**
	 * ids转isList
	 * 
	 * @param idss
	 *            前台传的ID数组
	 * @return
	 */
	public static List<Long> arrayToList(String[] idss) {
		List<Long> idList = Lists.newArrayList();
		if (idss == null || idss.length == 0) {
			return idList;
		}
		for (String id : idss) {
			if (StringUtils.isBlank(id)) {
				continue;
			}
			if (StringUtils.contains(id, ",")) {
				String[] subIds = id.split(",");
				for (String subId : subIds) {
					idList.add(Long.valueOf(subId));
				}
			} else {
				idList.add(Long.valueOf(id));
			}
		}
		return idList;
	}

	/**
	 * 向参数字符串 <strong>左</strong> 补0，直到长度达到参数指定长度，如果字符串长度大于指定长度，返回原字符串
	 * 
	 * @param value
	 *            - 字符串
	 * @param length
	 *            - 指定长度
	 * @return
	 */
	public static String zeroLeftCover(String value, int length) {
		return zeroCover(value, length, true);
	}

	/**
	 * 向参数字符串 <strong>右</strong> 补0，直到长度达到参数指定长度，如果字符串长度大于指定长度，返回原字符串
	 * 
	 * @param value
	 *            - 字符串
	 * @param length
	 *            - 指定长度
	 * @return
	 */
	public static String zeroRightCover(String value, int length) {
		return zeroCover(value, length, false);
	}

	private static String zeroCover(String value, int length, boolean isLeft) {
		int size = length - value.length();
		if (size < 0) {
			return value;
		}
		String cover = String.format("%0" + size + "d", 0);
		StringBuilder bulder = new StringBuilder(length);
		if (isLeft) {
			bulder.append(cover).append(value);
		} else {
			bulder.append(value).append(cover);
		}
		return bulder.toString();
	}

	public static String wapperValue(String[] values, String c) {
		StringBuilder builder = new StringBuilder();
		String value = null;
		for (int i = 0; values != null && i < values.length; i++) {
			value = values[i];
			if (StringUtils.isBlank(value))
				continue;
			if (i > 0 && value.startsWith(c)) {
				value = value.substring(c.length());
			}
			builder.append(value);
			if (i + 1 < values.length) {
				if (!value.endsWith(c)) {
					builder.append(c);
				}
			}
		}
		return builder.toString();
	}

	public static String wapperValue(Collection<String> values, String c) {
		return wapperValue(values.toArray(new String[values.size()]), c);
	}

	public static String wapperValue(String[] values, char c) {
		return wapperValue(values, String.valueOf(c));
	}

	public static String wapperValue(Collection<String> values, char c) {
		return wapperValue(values.toArray(new String[values.size()]), c);
	}

	public static String suffixToLastString(String str, String suffix) {
		if (str.endsWith(suffix))
			return str;
		else
			return new String(str + suffix);
	}

	public static String startWordToLower(String value) {
		StringBuilder b = new StringBuilder();
		if (StringUtils.isBlank(value)) {
			return b.toString();
		}
		String firstWord = value.substring(0, 1);
		b.append(firstWord.toLowerCase()).append(value.substring(1));
		return b.toString();
	}

	/**
	 * 删除 sb中最后一个字符 暂时用于拼SQL时删除最后一个逗号 add by yuwu 2013-11-12
	 * 
	 * @param StringBuffer
	 *            sb 需要删除最后一个字符的StringBuffer,默认以逗号结束
	 */
	public static void deleteEndChar(StringBuilder sb) {
		deleteEndChar(sb, ",");
	}

	/**
	 * 删除 sb中最后一个字符 暂时用于拼SQL时删除最后一个逗号 add by yuwu 2013-11-12
	 * 
	 * @param StringBuffer
	 *            sb 需要删除最后一个字符的StringBuffer
	 * @param String
	 *            endWith 最后一个结尾的字符
	 */
	public static void deleteEndChar(StringBuilder sb, String endWith) {
		if (sb != null && sb.length() > 0 && sb.toString().endsWith(endWith)) {
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	/**
	 * 将字符串转化成int Title:
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param str
	 * @return
	 */
	public static int strToInt(String str) {
		if (RegexUtils.isNull(str)) {
			return 0;
		}
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 将字符串转化成long Title:
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param str
	 * @return
	 */
	public static long strToLong(String str) {
		if (RegexUtils.isNull(str)) {
			return 0;
		}
		try {
			return Long.parseLong(str);
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * 
	 * Title:截取汉子
	 * <p>Description:截取汉子</p>
	 * @param str 需要截取的字符串
	 * @param subSLength 字节数
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String subStr(String str, int subSLength)  
	           { 
		try{
	       if (str == null)  
	           return "";  
	       else{ 
	           int tempSubLength = subSLength;//截取字节数
	           String subStr = str.substring(0, str.length()<subSLength ? str.length() : subSLength);//截取的子串  
	           int subStrByetsL = subStr.getBytes("GBK").length;//截取子串的字节长度 
	           //int subStrByetsL = subStr.getBytes().length;//截取子串的字节长度 
	           // 说明截取的字符串中包含有汉字  
	           boolean isCut = false;
	           while (subStrByetsL > tempSubLength){  
	        	   int subSLengthTemp = --subSLength;
	               subStr = str.substring(0, subSLengthTemp>str.length() ? str.length() : subSLengthTemp);  
	               subStrByetsL = subStr.getBytes("GBK").length;
	               isCut = true;
	               //subStrByetsL = subStr.getBytes().length;
	           } 
	           if(isCut) {
	        	   subStr += "...";
	           }
	           return subStr; 
	       }
		} catch(Exception e) {			
		}
		return str;
	   }

    /**
     * 方法的概要：字符为空
     * 
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (null == str || "".equals(str.trim()) || "null".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 方法的概要：整型为空
     * 
     * @param str
     * @return
     */
    public static boolean isEmpty(Integer str) {
        if (null == str || "0".equals(str.toString())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 方法的概要：数组为空
     * 
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object[] obj) {
        if (null == obj || obj.length == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 方法的概要：List集合为空
     * 
     * @param obj
     * @return
     */
	public static <T> boolean isEmpty(List<T> obj) {
        if (null == obj || obj.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 方法的概要：判断是否为空
     * 
     * @param id
     * @return
     */
    public static boolean isEmpty(UUID id) {
        if (null == id || id.toString().equals("00000000-0000-0000-0000-000000000000")) {
            return true;
        }
        return false;
    }
    
    /**
     * 方法的概要：String转int类型
     * 
     * @param str
     * @return
     */
    public static int stringToInteger(String str) {
        int iChange = 0;
        if (!StringUtil.isEmpty(str)) {
            try {
                iChange = Double.valueOf(str.trim()).intValue();
//                iChange = Integer.parseInt(str.trim());
            } catch (Exception e) {
                iChange = 0;
            }
        }
        return iChange;
    }
    
	/**
	 * <p>Description:格式化数字，将1转换为01,2转换为02，以此类推</p>
	 * @param i
	 * @return
	 * @author tangqiang on 2015年4月8日
	 */
	public static String unitFormat(int i) {
        String retStr = null;  
        if (i >= 0 && i < 10)  
            retStr = "0" + Integer.toString(i);  
        else  
            retStr = "" + i;  
        return retStr;  
    }  
}
