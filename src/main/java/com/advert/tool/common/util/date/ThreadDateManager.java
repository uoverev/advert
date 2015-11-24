/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
package com.advert.tool.common.util.date;

import java.util.Date;

/**
 * Title:保持时间一致的类
 * <p>Description:数据库表中有许多时间需要保持一致，而保存操作又是分步进行</p>
 * <p>为了使同一事务类最终保存到数据库各表中的时间相同，使用ThreadLocal来保存</p>
 * <p>在当前线程结束使用前需要调用remove方法，防止线程在线程池中被重复使用导致时间错误</p>
 * Copyright (c) feinno 2013-2016
 * @author tangqiang on 2015年1月21日
 */
public class ThreadDateManager {

	private static ThreadLocal<Date> dateHolder = new ThreadLocal<Date>();
	
	public static Date getDate(){
		Date date = dateHolder.get();
		if(date == null){
			Date now = new Date();
			dateHolder.set(now);
			return now;
		}
		return dateHolder.get();
	}
	
	public static void setDate(Date date){
		dateHolder.set(date);
	}
	
	public static void setNow(){
		dateHolder.set(new Date());
	}
	
	public static void remove(){
		dateHolder.remove();
	}
}
