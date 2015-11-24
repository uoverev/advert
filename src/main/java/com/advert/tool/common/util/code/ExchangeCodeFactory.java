package com.advert.tool.common.util.code;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 兑换码生成器，此工具只考虑传入种子数，对于种子的竞争问题，在调用时考虑
 * 
 * 兑换码生成规则：
 * 
 * 年月日  20140506 加起来 2+0+1+4+0+5+0+6=18转59进制 [A]
 * 
 * 10000000(1千万开始)递增  转 59进制  [0][1][2][3]...[x]
 * 
 * 产生2个随机码1~59的随机号，转59进制 [B][C]
 * 
 * 组装兑换码 [A][0][B][2][C][3]...[x]
 * 
 * @author ke.zhang
 * 2014-6-13 下午3:36:38
 */
public class ExchangeCodeFactory {
	
	//空字符串
	public static final String NUL  = ""; 

	//补零
	public static final String ZEROFILL  = "0"; 
	
	//日期格式
	public static final String DATEPATTEN  = "yyyyMMdd";
	

	/**
	 * 产生一个兑换码
	 * @param date   日期
	 * @param seed   种子
	 * @return
	 */
	public static String buildExchangeCode(Date date,int seed){
		String date_str = ExchangeCodeFactory.calcYmd(date);
		return buildExchangeCode(date_str,seed);
	}
	
	
	private static String buildExchangeCode(String date,int seed){
		String seedToRadix59_str = Number59Utils.any2any(seed + NUL, Number59Utils.RADIX_10, Number59Utils.RADIX_59);
		String[] seedToRadix59_arr =  seedToRadix59_str.split(NUL);
		StringBuilder sb = new StringBuilder(date);
		for (int i = 1; i < seedToRadix59_arr.length; i++) {
			if(2 == i || 3 == i){
				sb.append(Number59Utils.any2any(((int)(Math.random()*58)+1 )+ NUL, Number59Utils.RADIX_10, Number59Utils.RADIX_59))
				.append(seedToRadix59_arr[i]);
			}else{
				sb.append(seedToRadix59_arr[i]);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 批量产生兑换码
	 * 根据种子和数量自动产生种子，在调用此方法的时候注意对种子进行增加
	 * @param date
	 * @param seed
	 * @param num  num <=1000000 考虑性能问题，最多一次性产生100W兑换码,如果 num > 1000000 return null
	 * @return 
	 */
	public static String[] buildExchangeCode(Date date,int seed,int num){
		if(num > 1000000){
			return null;
		}
		String date_str = ExchangeCodeFactory.calcYmd(date);
		String [] codes = new String[num];
		int temp = 0;
		for (int i = seed; i < seed + num; i++) {
			codes[temp++] =  buildExchangeCode(date_str,i);
		}
		return codes;
		
	}
	
	/**
	 * 计算日期
	 * @param date
	 * @return
	 */
	public static String calcYmd(Date date){
		int temp = 0;
		String date_str = format(date, DATEPATTEN);
		String[] date_arr = date_str.split(NUL);
		for (int i = 1; i < date_arr.length; i++) {
			temp += Integer.parseInt(date_arr[i]);
		}
		String ymd59 = Number59Utils.any2any(temp + NUL, Number59Utils.RADIX_10, Number59Utils.RADIX_59);
		return ymd59.length() >= 2 ? ymd59 : ymd59 + ZEROFILL;
	}
	
	
	private static String format(Date date, String pattern) {
		return date == null ? "" : new SimpleDateFormat(pattern).format(date);
	}

	public static void main(String[] args) {
		System.out.println();
		
/*		for (int i = 10000000; i < 10000100; i++) {
			System.out.println(buildExchangeCode(new Date(), i));
		}
		String[] x = buildExchangeCode(new Date(), 1, 59);
		System.out.println(x.toString());*/
	}
	
	
}
