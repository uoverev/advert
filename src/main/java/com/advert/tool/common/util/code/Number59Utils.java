package com.advert.tool.common.util.code;

import java.util.HashMap;
import java.util.Map;

public class Number59Utils {
	
    private static final String[]   SEED_NUM_ARRAY = { "0", "1", "2", "3", "4", "5", "6","7", "8", "9", 
    	    "a", "b", "c", "d", "e","f","g","h","i", "j", "k",      "m", "n",     "p", "q", "r", "s", "t", "u", "v", "w", "x", "y",
    	    "A", "B", "C", "D", "E","F","G","H","I", "J", "K", "L", "M", "N","O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"                           };
    
    //10进制
    public static final int RADIX_10 = 10;
    
    //50进制
    public static final int RADIX_59 = 59;
    
    //初始化数组
    private static final Map<String, Integer> SEED_NUM_MAP   = initSEED_NUM_MAP();

    /**
     * 返回将十进制数转换为指定进制的数的字符串形式
     * @param dec       需要转换的十进制数字
     * @param devider   输出进制
     * @return          输出进制的数字
     * @throws Exception 
     */
    public static String dec2any(String dec, int targetDevider) {
        if (targetDevider == 10)
            return dec;
        if (targetDevider > 59 || targetDevider < 2 || null == dec || "".equals(dec.trim()))
            return null;
        try {
            long devider = Long.parseLong(String.valueOf(targetDevider));//进制    
            long dividend = Long.parseLong(dec);//被除数
            long quotient = 0L;//轮商
            long remainder = 0L;//轮余
            StringBuffer remainderAdd = new StringBuffer();//余数累加
            while (true) {
                remainder = dividend % devider;
                quotient = dividend / devider;
                remainderAdd.append(SEED_NUM_ARRAY[(int) remainder]);
                if (quotient < devider) {
                	remainderAdd.append(SEED_NUM_ARRAY[(int) quotient]);
                    break;
                }
                dividend = quotient;
            }
            String ret =  remainderAdd.reverse().toString();
            if(ret.length() > 1 && "0".equals(String.valueOf(ret.charAt(0)))){
                ret = ret.replaceFirst("0", "");
            }
            return ret;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 进制的任意转换
     * 返回一个已知进制的数的其他进制的值
     * @param sourceNum 需转换数的字符串
     * @param soruceDevider 需转换数的进制
     * @param targetDevider 目标进制
     * @return 目标进制的字符串
     */
    public static String any2any(String sourceNum, int soruceDevider, int targetDevider) {
        if (targetDevider > 59 || targetDevider < 2 || soruceDevider > 59 || soruceDevider < 2)
            return null;
        if (null == sourceNum || "".equals(sourceNum.trim()))
            return null;
        if (soruceDevider == targetDevider)
            return sourceNum;
        return dec2any(any2dec(sourceNum, soruceDevider), targetDevider);
    }

    /**
     * 返回一个已知进制的数的十进制的值
     * @param String 需转换数的字符串
     * @param int 需转换数的进制
     * @return 转换后的数的字符串
     */
    public static String any2dec(String sourceNum, int soruceDevider) {
        if (soruceDevider == 10)
            return sourceNum;
        if (soruceDevider > 59 || soruceDevider < 2 || null == sourceNum
            || "".equals(sourceNum.trim()))
            return null;
        try {
            long sum = 0L;
            for (int i = 0; i < sourceNum.length(); i++) {
                int k = SEED_NUM_MAP.get(String.valueOf(sourceNum.charAt(i)));
                if (k >= soruceDevider)
                    return null;
                sum += k * Math.pow(soruceDevider, sourceNum.length() - (i + 1));
            }
            return String.valueOf(sum);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Map<String, Integer> initSEED_NUM_MAP() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < 59; i++) {
            map.put(SEED_NUM_ARRAY[i], i);
        }
        return map;
    }
    
    public static void main(String[] args) {
//    	Map m = new HashMap();
//    	System.out.println(m.size());
//    	long t1 =System.currentTimeMillis();
//		for (int i = 10000000; i < 10999999; i++) {
//			m.put(i, any2any(i+"", 10, 58));
//		}
//    	long t2=System.currentTimeMillis();
//    	System.out.println(t2-t1);
//    	System.out.println(m.size());
//    	0
//    	2126
//    	999999
/*    	for (int i = 0; i <= 59; i++) {
    		System.out.println(i+ "==");
    		System.out.println(any2any(i+"", 10, 59));
    	}*/
//    	Map m = new HashMap();
//    	System.out.println(m.size());
//    	long t1 =System.currentTimeMillis();
//    	for (int i = 10000000; i < 10999999; i++) {
//			m.put(i, any2any(i+"", 10, 36));
//		}
//    	long t2=System.currentTimeMillis();
//    	System.out.println(t2-t1);
//    	System.out.println(m.size());
    	
    	System.out.println(Number59Utils.any2any(System.currentTimeMillis()+"", Number59Utils.RADIX_10, Number59Utils.RADIX_59));
    	System.out.println(any2any(System.currentTimeMillis()+"", RADIX_10, RADIX_59));
    	System.out.println(any2any(System.currentTimeMillis()+"", RADIX_10, RADIX_59));
	}
}

