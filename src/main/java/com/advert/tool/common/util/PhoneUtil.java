/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
package com.advert.tool.common.util;

import com.better.framework.utils.Regexs;


/**
 * Title:
 * <p>Description:电话号码相关工具类</p>
 * Copyright (c) feinno 2013-2016
 * @author tangqiang on 2015年5月26日
 */
public class PhoneUtil {

	public static final String DEFAULT_PHONE_PATTERN = "^(((1[3|4|5|6|7|8|9][0-9]{1})|(15[0-9]{1}))+\\d{8})$";
	
	/**
	 * <p>Description:验证是否是电话号码</p>
	 * @param phone
	 * @param phonePattern 电话号码正则表达式，如果为空，则使用默认表达式 DEFAULT_PHONE_PATTERN;
	 * @return
	 * @author tangqiang on 2015年5月26日
	 */
	public static boolean validatePhone(String phone,String phonePattern){
		if(StringUtil.isEmpty(phonePattern)){
			phonePattern = DEFAULT_PHONE_PATTERN;
		}
		if(Regexs.matcher(phonePattern,phone)){
			return true;
		}
		return false;
	}
	
	/**
	 * <p>Description:验证是否是电话号码,建议使用validatePhone(String phone,String phonePattern)方法，将phonePattern配置到配置文件以便随时修改</p>
	 * @param phone
	 * @return
	 * @author tangqiang on 2015年5月26日
	 */
	public static boolean validatePhone(String phone){
		return validatePhone(phone,null);
	}
}
