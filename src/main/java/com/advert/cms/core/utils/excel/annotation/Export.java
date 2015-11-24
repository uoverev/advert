package com.advert.cms.core.utils.excel.annotation;

import java.lang.annotation.*;

/**
 * <p>
 * Description:数据导出注解
 * </p>
 * Copyright (c) feinno 2013
 *
 * @author wuyuanjie on 2015年4月10日
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
@Documented
public @interface Export {
	/**
	 * <p>导出字段名</p>
	 */
	public String name();
	
	/**
	 * <p>格式化字段输出，默认为时间：yyyy-MM-dd HH:mm:ss </p>
	 * 例如：日期格式化:yyyy-MM-dd、yyyy-MM-dd HH:mm:ss
	 * 数字格式化:#.00
	 */
	public String pattern() default "yyyy-MM-dd HH:mm:ss";

	/**
	 * <p>状态字段描述 json字符串格式</p>
	 */
	public String json() default "";

	/**
	 * <p>导出列排序(升序排列)</p>
	 */
	public int order();

}
