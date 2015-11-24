package com.advert.tool.common.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.ClassUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.better.framework.common.exception.BusinessException;

public abstract class SpringBeanUtils {
	
	public static <T> T getSpringBean(HttpServletRequest request, String className, Class<T> interfaceClass)
			throws BusinessException {
		try {
			Class<?> clazz = Class.forName(className);
			return getSpringBean(request, clazz, interfaceClass);
		} catch (ClassNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getSpringBean(HttpServletRequest request, Class<?> clazz, Class<T> interfaceClass)
			throws BusinessException {
		try {
			ServletContext sc = request.getSession().getServletContext();
			WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
			Object o = wac.getBean(clazz);
//			Asserts.isNull(o, clazz.getName() + "在Spring中未定义！");
			boolean flag = false;
			for (Class<?> inter : ClassUtils.getAllInterfacesForClass(clazz)) {
				if (interfaceClass.equals(inter)) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				throw new BusinessException("对象未实现" + interfaceClass.getName() + "接口！");
			}
			return (T) o;
		} catch (Exception e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	public static <T> T getSpringBean(String className, Class<T> interfaceClass)
			throws BusinessException {
		try {
			Class<?> clazz = Class.forName(className);
			return getSpringBean(clazz, interfaceClass);
		} catch (ClassNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T getSpringBean(Class<?> clazz, Class<T> interfaceClass)
			throws BusinessException {
		try {
			WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
			if(wac == null) {
				throw new BusinessException("Spring初始化异常！");
			}

			Object o = wac.getBean(clazz);
			if(o == null) {
				throw new BusinessException(clazz.getName() + "在Spring中未定义！");
			}

			boolean flag = false;
			for (Class<?> inter : ClassUtils.getAllInterfacesForClass(clazz)) {
				if (interfaceClass.equals(inter)) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				throw new BusinessException("对象未实现" + interfaceClass.getName() + "接口！");
			}
			return (T) o;
		} catch (Exception e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
}
