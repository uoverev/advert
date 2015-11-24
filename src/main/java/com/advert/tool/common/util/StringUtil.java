package com.advert.tool.common.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.JoinColumn;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

/**
 * <p>Description:字符串操作工具</p>
 * Copyright (c) feinno 2013-2016
 * @author tangqiang on 2014年12月4日
 */
public class StringUtil {

    protected static Pattern pattern_Number = Pattern.compile("(?:-?[0-9]+\\.?[0-9]+)|(?:\\d)", Pattern.DOTALL);
    
    /** 特殊字符对应转义编码*/
    public static String[] regExpArr = new String[]{"&amp;","&nbsp;","&lt;","&gt;","&#039;","&quot;"}; 
    public static String[] replaceArr = new String[]{"&"," ","<",">","'","\""};
    /**				  */
    
    /* 列表，数组 */
    public static final int ARRAY = 0;
    /* 普通数据 */
    public static final int NOMAL = 1;

    /**
     * <pre>
     * 方法的概要：获取实体所有字段
     * @param clazz 实体类型
     * @param strs 需要排除的字段
     * 
     * @return Map<String, Method> 其中key为数据库字段名称，value为字段对应的get方法
     * 
     * </pre>
     */
    public static Map<String, Method> getFields(Class<?> clazz, List<String> strs) {
        Map<String, Method> map = new HashMap<String, Method>();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            Column c = method.getAnnotation(Column.class);
            if (null != strs && strs.contains(c.name())) {
                continue;
            }
            if (null != c) {
                map.put(c.name(), method);
            } else {
                JoinColumn jc = method.getAnnotation(JoinColumn.class);
                if (null != strs && strs.contains(jc.name())) {
                    continue;
                }
                if (null != jc) {
                    map.put(jc.name(), method);
                }
            }
        }
        return map;
    }

    private final static String[] ARRAY_INT = { "int", "java.lang.Integer" };
    private final static String[] ARRAY_LONG = { "long", "java.lang.Long" };
    private final static String[] ARRAY_DOUBLE = { "double", "java.lang.Double" };
    private final static String[] ARRAY_FLOAT = { "float", "java.lang.Float" };
    private final static String[] ARRAY_BOOLEAN = { "boolean", "java.lang.Boolean" };

    /**
     * <pre>
     * 方法的概要：基本类型转对象
     * 
     * @param val
     * @param paramType
     * 
     * @return
     * </pre>
     */
    public static Object str2Obj(String val, String paramType) {

        // 对其他简单类型的处理。如String、int、Integer、Date
        Object obj = null;
        if (Arrays.binarySearch(ARRAY_INT, paramType) > -1) {
            obj = Integer.valueOf(val);
        } else if (Arrays.binarySearch(ARRAY_BOOLEAN, paramType) > -1) {
            obj = Boolean.valueOf(val);
        } else if (Arrays.binarySearch(ARRAY_LONG, paramType) > -1) {
            obj = Long.valueOf(val);
        } else if (paramType.equals("java.util.Date")) {
            obj = DateUtil.toDate(val);
        } else if (Arrays.binarySearch(ARRAY_DOUBLE, paramType) > -1) {
            obj = Double.valueOf(val);
        } else if (Arrays.binarySearch(ARRAY_FLOAT, paramType) > -1) {
            obj = Float.valueOf(val);
        } else if (String.class.getName().equals(paramType)) {
            obj = val;
        }
        return obj;
    }

    /**
     * <p>测试内容是否为数值(包括数字，小数点，负数号)</p>
     * 
     * @param val
     * @return
     *         2011-10-11 下午4:07:55
     */
    public static boolean isNumber(String val) {
        return pattern_Number.matcher(val).matches();
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
     * 方法的概要: String转long型
     * 
     * @param str
     * @return
     */
    public static long stringToLong(String str) {
        long lChanged = 0;
        if (!StringUtil.isEmpty(str)) {
            try {
                lChanged = new BigDecimal(str).longValue();
            } catch (Exception e) {
                lChanged = 0;
            }
        }
        return lChanged;
    }

    /**
     * 方法的概要：字符串转bool型
     * 
     * @param str
     * @return
     */
    public static boolean stringToBoolean(String str) {
        boolean iChange = false;
        if (!StringUtil.isEmpty(str)) {
            try {
                iChange = Boolean.parseBoolean(str);
            } catch (Exception e) {
            }
        }
        return iChange;
    }

    /**
     * 方法概要:获取数组对象的STRING串
     * 
     * @param args
     * @return
     */
    public static String getToString(int type, Object... args) {
        String value = "";
        if (type == ARRAY) {
            if (args != null && args.length > 0) {
                for (Object obj : args) {
                    value = value + toString(obj) + ",";
                }
            }
        } else {
            value = toString(args[0]);
        }
        return value;
    }

    /**
     * 方法概要:将列表转换成字组
     * 
     * @param str
     * @param regex
     * @return
     */
    public static Integer[] stringToArray(String str, String regex) {
        try {
            String[] s = str.split(regex);
            Integer[] ite = new Integer[s.length];
            for (int i = 0; i < s.length; i++) {
                ite[i] = stringToInteger(s[i]);
            }
            return ite;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 
     * 将字符串转换为map<key, value>键值对
     * 
     * @param str
     *            需要转换的字符串：格式要求(key=value)
     * @param separator
     *            各个键值对的分隔符：一般为","
     * @auther <a href="mailto:lihanpei@feinno.com">lihanpei</a>
     * @since 2011-9-16 下午10:30:26
     */
    public static Map<String, String> str2Map(String str, String separator) {
        return str2Map(str, separator, "=");
    }

    /**
     * 
     * 将字符串转换为map<key, value>键值对
     * 
     * @param str
     *            需要转换的字符串：格式要求(key=value)
     * @param separator
     *            各个键值对的分隔符：一般为","
     * @param keySignValue
     *            各个键与值的分隔符：一般为"="
     * @auther <a href="mailto:lihanpei@feinno.com">lihanpei</a>
     * @since 2011-9-16 下午10:30:26
     */
    public static Map<String, String> str2Map(String str, String separator, String keySignValue) {
        Map<String, String> map = new HashMap<String, String>();
        String[] strs = str.split(separator);
        for (String tem : strs) {
            map.put(tem.split(keySignValue)[0].trim(), tem.split(keySignValue)[1].trim());
        }
        return map;
    }

    /**
     * 方法概要:位运算判断语句
     * 
     * @param type
     *            获取的类型
     * @param detailtype
     *            传入的类型
     * @return
     */
    public static boolean existtype(int type, int detailtype) {
        boolean exist = (type | detailtype) == detailtype;
        return exist;
    }

    /**
     * 给字符串前后添加逗号
     * 
     * @param str
     * 
     * @return
     */
    public static String addComma(String str) {
        if (!StringUtil.isEmpty(str)) {
            str = str.trim();
            if (!str.startsWith(",")) {
                str = "," + str;
            }
            if (!str.endsWith(",")) {
                str = str + ",";
            }
        }
        return str;
    }
    
    

    /**
     * 删除字符串前后逗号
     * 
     * @param str
     * 
     * @return
     */
    public static String removeComma(String str) {
        if (StringUtil.isEmpty(str)) {
            return "";
        } else {
            if (str.startsWith(",")) {
                str = str.substring(1);
            }
            if (str.endsWith(",")) {
                str = str.substring(0, str.length() - 1);
            }
        }
        return str;
    }

    /**
     * 方法的概要：根据异常获取到异常说明
     * 
     * @param e
     *            异常
     * 
     * @return
     */
    public static String getStackTraceStrFromException(Throwable e) {
        StringBuilder logStrBuilder = new StringBuilder();
        StackTraceElement[] traces = e.getStackTrace();

        logStrBuilder.append(e.getMessage());
        logStrBuilder.append("\n");
        for (int i = 0; i < traces.length; i++) {
            logStrBuilder.append(traces[i].toString());
            logStrBuilder.append("\n");
        }

        return logStrBuilder.toString();
    }

    /**
     * 方法的概要：获取seftid
     * 
     * @param id
     * 
     * @return
     */
    public static String getSeftId(String id) {
        long tmp = Long.valueOf(id);
        if (tmp % 100 == 0) {
            tmp = tmp / 100;
            return getSeftId(String.valueOf(tmp));
        } else {
            return String.valueOf(tmp);
        }
    }

    /**
     * 方法概要: 对象转成String
     * 
     * @param obj
     * @return
     */
    private static String toString(Object obj) {
        String value = null;
        if (obj != null)
            value = String.valueOf(obj);
        return value;
    }

    /**
     *<pre>
     * 隐藏手机号后四位
     * 
     * @param phone
     * @return
     * @auther <a href="mailto:lihanpei@feinno.com">Hanpei Li</a> 2012-2-6 下午04:47:15
     *</pre>
     */
    public static String getHidePhone(String phone) {
        if (!StringUtil.isEmpty(phone) && phone.length() >= 5) {
            phone = phone.substring(0, phone.length() - 4) + "****";
        }

        return phone;
    }

    /**
     *<pre>
     * 生成UUID
     * @return
     * @auther <a href="mailto:lihanpei@feinno.com">Hanpei Li</a> 2012-2-10 下午04:44:32
     *</pre>
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static float stringToFloat(String str) {
        try {
            if (StringUtil.isEmpty(str))
                return Float.valueOf(0);
            else
                return Float.valueOf(str);
        } catch (Exception e) {
            return Float.valueOf(0);
        }
    }

    /**
     * 获取Eth0,即网卡0的IP
     * @return
     */
    public static String getEth0IP() {
        String localeth0 = "127.0.0.1";
        Enumeration<NetworkInterface> allNetInterfaces;
        try {
            allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                if ("eth0".equals(netInterface.getName())) {
                    Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = (InetAddress) addresses.nextElement();
                        localeth0 = ip.getHostAddress();
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localeth0;
    }
    /**
     * <p>Description:将string转换为dobule类型</p>
     * @param str
     * @return
     */
    public static double stringToDouble(String str) {
        try {
            if (StringUtil.isEmpty(str))
                return Double.valueOf(0.0);
            else
                return Double.valueOf(str);
        } catch (Exception e) {
            return Double.valueOf(0.0);
        }
    }
    
    /**
     * 密码解密
     * 
     * @param str
     * 
     * @return
     */
    public static String pwdDeCrypt(String str){
    	String result = "";
    	if(!StringUtil.isEmpty(str)){
    		 if (str.startsWith("#")){
                 str = str.substring(1);
             }
    		 
    		 String[] arr = str.split("#");
    		 for(String s : arr){
    			 result=result+(char)Integer.parseInt(s);
    		 }
    	}
    	
    	return result;
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
		if (idss != null && idss.length > 0) {
			for (String id : idss) {
				if (StringUtils.isNotBlank(id)) {
					if (StringUtils.contains(id, ",")) {
						String[] subIds = StringUtils.split(id, ",");
						for (String subId : subIds) {
							idList.add(Long.valueOf(subId));
						}
					} else {
						idList.add(Long.valueOf(id));
					}
				}
			}
		}
		return idList;
	}
	
	/**
	 * <p>Description:判断字符是否为中文</p>
	 * @param c
	 * @return
	 */
	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
			return true;
		}
		return false;
	}
	
	/**
	 * <p>Description:将1个中文汉字替换成两个占位符</p>
	 * @param value
	 * @return
	 * @author tangqiang on 2015年4月13日
	 */
	public static String replaceChinese(String value){
		if(StringUtil.isEmpty(value)) return value;
		StringBuilder r = new StringBuilder();
		char[] chars = value.toCharArray();
		for(int i=0;i<chars.length;i++){
			if(isChinese(chars[i])){
				r.append("00");
			}else{
				r.append(chars[i]);
			}
		}
		return r.toString();
	}
	
	/**
	 * <p>Description:截字处理，以中文字长度为准</p>
	 * @param value
	 * @param length 1单位长度代表一个中文字，两个字符
	 * @return
	 * @author tangqiang on 2015年4月8日
	 */
	public static String cutString(String value,int length){
		return cutStringEn(value, length*2);
	}
	
	/**
	 * <p>Description:截字处理，以中文字长度为准</p>
	 * @param value
	 * @param length 1单位长度代表一个字符
	 * @return
	 * @author tangqiang on 2015年4月13日
	 */
	private static String cutStringEn(String value,int length){
		if(StringUtil.isEmpty(value)) return value;
//		if(value.length()<=length) return value;
		
		char[] chars = value.toCharArray();
		int k=0,i=0;//单位是字节
		for(;i<chars.length && k<=length;i++){
			if(StringUtil.isChinese(chars[i])){
				k+=2;
			}else{
				k++;
			}
		}
		value = value.substring(0,i-1);
		return value;
	}

	/**
	 * <p>
	 * Description:计算字符串长度，汉字一个算2个长度，包括汉字标点
	 * </p>
	 * 
	 * @param str需要计算的字符串
	 * @return
	 * @author huxiaoping on 2015年5月26日
	 */
	public static int countLength(String str) {
		int len = 0;
		if (StringUtils.isNotBlank(str)) {
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if (StringUtil.isChinese(c)) {
					len += 2;
				} else {
					len++;
				}
			}
		}
		return len;
	}

	/**
	 * <p>
	 * Description:校验字符串是否为汉字，数字，或字母（不包含任何字符）
	 * </p>
	 * 
	 * @param str需要校验的字符串
	 * @return
	 * @author huxiaoping on 2015年5月26日
	 */
	public static boolean CNChara_Letter_Number(String str) {
		if (StringUtils.isNotBlank(str)) {
			String regex = "^[a-zA-Z0-9\u4E00-\u9FA5]+$";
			Pattern pattern = Pattern.compile(regex);
			Matcher match = pattern.matcher(str);
			return match.matches();
		} else {
			return false;
		}
	}

	/***
	 * <p>Description:截字处理，以中文字长度为准,并对特殊字符进行转义</p>
	 * @param value
	 * @param length 1单位长度代表一个中文字符
	 * @param escape 特殊字符例如&nbsp;等是否需要转义处理
	 * @return
	 * @author tangqiang on 2015年4月13日
	 */
	public static String cutString(String value,int length,boolean escape){
		length = length*2;
		if(!escape){
			return cutStringEn(value, length);
		}
		if(StringUtil.isEmpty(value)) return value;
		
		Map<String, Integer> data = new HashMap<String, Integer>();
		data.put("startIndex", 0);
		data.put("addLength", 0);
		String temp = replaceChinese(value);
		for(int i=0 ; i< regExpArr.length ; i++){
			data.put("startIndex", 0);
			getAddLength(temp,regExpArr[i],replaceArr[i],length,data);
		}
//		return cutString(value, length+NumberUtil.ceilDivision(data.get("addLength"), 2));
		return cutStringEn(value, length+ (data.get("addLength")));
	}

	/**
	 * <p>Description:在截字前，先算最终要截多少字,例如：替换前('&amp;&nbsp;&nbsp;d&nbsp;',4),替换前需要截4个字符，实际想获取这4个（&  d），经过这个方法计算，最后需要截取的字符串长度应该是18位</p>
	 * @param value 替换字符串，示例：&amp;&nbsp;&nbsp;d&nbsp;
	 * @param oldSubValue 需要替换的子串，示例：&nbsp;(即转义后的空格)
	 * @param newSubValue 替换后的子串，示例：' '(空格)
	 * @param paramLength 需要载取的长度
	 * @param data {startIndex：查找子串的位置,addLength:替换后增加的长度}
	 * @author tangqiang on 2015年4月13日
	 */
	private static void getAddLength(String value,String oldSubValue,String newSubValue,int paramLength,Map<String, Integer> data){
		data.put("startIndex", value.indexOf(oldSubValue,data.get("startIndex")));
		
		if(data.get("startIndex")<0 || data.get("startIndex")>=paramLength+data.get("addLength")){
			return;
		}
		data.put("addLength", data.get("addLength") + (oldSubValue.length() - newSubValue.length()));
		data.put("startIndex", data.get("startIndex") + oldSubValue.length());
		if(data.get("startIndex") >= paramLength + data.get("addLength")){
			return ;
		}
		getAddLength(value,oldSubValue,newSubValue,paramLength,data);
	}

	/**
	 * <p>Description:将字符串对应位置的字符，转换成*号</p>
	 * @param value
	 * @param begin
	 * @param end
	 * @param fakeNum 星号的数量
	 * @return
	 * @author tangqiang on 2015年4月8日
	 */
	public static String replaceToAsterisk (String value,int begin,int end,int fakeNum){
		return replaceToCustomStr(value, begin, end, fakeNum, "*");
	}
	
	public static String replaceToCustomStr(String value,int begin,int end,int fakeNum,String fake){
		if(StringUtil.isEmpty(value)) return value;
		StringBuilder temp = new StringBuilder();
		for(int i=0;i<fakeNum;i++){
			temp.append(fake);
		}
		StringBuilder v = new StringBuilder(value);
		return v.replace(begin, end, temp.toString()).toString();
//		return value.replace(value.substring(begin, end), temp);
	}
	
	/**
	 * <p>Description:将\n转换成页面识别的br</p>
	 * @param text
	 * @return
	 * @author tangqiang on 2015年3月21日
	 */
	public static String transEnterToBr(String text){
		Pattern CRLF = Pattern.compile("(\r\n|\r|\n|\n\r)");  
        Matcher m = CRLF.matcher(text);  
        if (m.find()) {  
          return m.replaceAll("<br>");
        }  
        return text;
	}
	
	/**
	 * <p>Description:将逗号分隔的string转换为int</p>
	 * @param str
	 * @return
	 * @author tangqiang on 2015年1月23日
	 */
	public static int[] StringtoInt(String s) {
		String str[] = s.split(",");  
		int array[] = new int[str.length];  
		for(int i=0;i<str.length;i++){  
		    array[i]=Integer.parseInt(str[i]);   
	 }  
		return array;
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
	public static String subStr(String str, int subSLength){ 
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
}