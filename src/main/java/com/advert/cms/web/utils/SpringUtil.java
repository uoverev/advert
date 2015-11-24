/**
 * Copyright (c) 2013 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
package com.advert.cms.web.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * <p>Title: SpringUtil.java</p>
 * <p>Description: sping 工具类</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: feinno </p>
 * <p>team: FetionyyTechnicalTeam</p>
 * @date 2013-8-13
 * @version 1.0
 * @author wangyuxin
 */
public class SpringUtil implements ApplicationContextAware {
	
	private static ApplicationContext context;
	
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		SpringUtil.context=context;
	}
	
	public static Object getBean(String beanName){
		return context.getBean(beanName);
	}

    public static <T> T getBean(String beanName, Class<T> clazz){
        return (T)context.getBean(beanName);
    }

}