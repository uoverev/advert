package com.advert.cms.core.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * Created by Administrator on 2015/4/15.
 */
public final class MoneyUtil {

	private static final BigDecimal decimal = new BigDecimal(100);

	/**
	 * 将字符串转换为按分为单位的金额
	 * 
	 * @param picre
	 *            - 金额字符串（单位元）
	 * @return long 单位(元)
	 */
	public static final Long transformPicre(String picre) {
		return new BigDecimal(picre).multiply(decimal).longValue();
	}

	/**
	 * 将金额格式化按元为单位
	 * 
	 * @param picre
	 *            - 金额（单位分）
	 * @return String 单位（元）
	 */
	public static final String formatPicre(Long picre) {
		long price = ((picre != null) ? picre : 0L);
		DecimalFormat format = new DecimalFormat("0.00");
		return format.format(new BigDecimal(price).divide(decimal));

	}

	public static void main(String[] args) throws ParseException {
		System.out.println(MoneyUtil.transformPicre("12.3456789"));
		System.out.println(MoneyUtil.formatPicre(null));
	}

}
