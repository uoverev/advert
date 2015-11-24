package com.advert.cms.task;

public interface LifeCycle {

	/**
	 * 
	 * <p>
	 * Description: 初始化配置
	 * </p>
	 */
	public void init();

	/**
	 * <p>
	 * Description: 关闭
	 * </p>
	 */
	public void close();

}
