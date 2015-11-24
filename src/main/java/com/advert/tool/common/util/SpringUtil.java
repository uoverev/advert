package com.advert.tool.common.util;

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
 */
public class SpringUtil implements ApplicationContextAware {
	
	private static ApplicationContext context;
	
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		SpringUtil.context=context;
	}
	
	/**
	 * <p>Description:根据bean name 获取由spring管理的bean</p>
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName){
		return context.getBean(beanName);
	}
	
	/**
	 * <p>Description:根据类全路径获取由spring管理的该类的实例</p>
	 * @param classPath
	 * @return
	 */
	public static Object getBeanByClass(String classPath){
		try {
			Class clazz = Class.forName(classPath);
			return context.getBean(clazz);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
