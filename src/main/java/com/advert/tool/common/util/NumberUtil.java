package com.advert.tool.common.util;

import java.util.Random;

import com.advert.tool.common.util.code.Number36Utils;


public class NumberUtil {
	
	/**
	 * <p>Description:获取随机数字的字符串</p>
	 * @param numLen 随机数字的长度
	 * @return
	 * @author tangqiang on 2015年4月9日
	 */
	public static String randomNum(int numLen) {
		int ranNum = 1;
		for(int i=0;i<numLen;i++){
			ranNum *=10;
		}
		Random random = new Random();
		while(true){
			int rnum = random.nextInt(ranNum);
			if(rnum>(ranNum/10)){
				return rnum+"";
			}
		}
	}
	
	/**
	 * <p>Description:向上取整的除法</p>
	 * @param n 除数
	 * @param dividend 被除数
	 * @author tangqiang on 2015年4月13日
	 */
	public static int ceilDivision(int n,int dividend){
		return (int)Math.ceil((double)n/dividend);
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

	/**
	 *  商铺编号
	 * 
	 * @param userId 用户Id
	 * @param shoptype 商铺类型   1 个人 2 机构
	 * @return
	 */
	public static String getShopNo(Long userId, int shoptype) {
		StringBuffer buffer = new StringBuffer();
		// 年月日的36进制获取		
		buffer.append(Number36Utils.any2any(System.currentTimeMillis()+"", Number36Utils.RADIX_10, Number36Utils.RADIX_36));
		// 商铺类型
		buffer.append(shoptype);
		// 商铺ID
		buffer.append(userId);
		return buffer.toString();
	}

	/**
	 * 订单编号
	 * 
	 * @param userId
	 * @return
	 */
	public static String getOrderNo(Long userId) {
		// 59位进制转时间，
		return System.currentTimeMillis() + randomNum(2) + userId;
	}

	/**
	 * 课程编号
	 * 
	 * @param shopId
	 * @return
	 */
	public static String getCourseNo(Long shopId) {
		return Number36Utils.any2any(System.currentTimeMillis()+"", Number36Utils.RADIX_10, Number36Utils.RADIX_36) + shopId;
	}
	
	/**
	 * 视频编号
	 * 
	 * @param shopId
	 * @return
	 */
	public static String getVideoNo(Long shopId) {
		return shopId+Number36Utils.any2any(System.currentTimeMillis()+"", Number36Utils.RADIX_10, Number36Utils.RADIX_36);
	}

	/**
	 * 商品编号
	 * 
	 * @param shopId
	 * @return
	 */
	public static String getGoodsNo(Long shopId, int shoptype) {
		StringBuffer buffer = new StringBuffer();
		// 年月日的59进制获取
		buffer.append(Number36Utils.any2any(System.currentTimeMillis()+"", Number36Utils.RADIX_10, Number36Utils.RADIX_36));
		// 商铺类型
		buffer.append(shoptype);
		// 商铺ID
		buffer.append(shopId);

		return buffer.toString();
	}

}
