package com.advert.cms.task;

/**
 * Title:
 * <p>
 * Description:
 * </p>
 * Copyright (c) feinno 2013
 * 
 * @author wuyuanjie on 2014年10月13日
 */
public abstract class TaskScheduler {
	// 默认批量扫描数据列表大小
	protected static final int DEFAULT_BATCH_SIZE = 100;

	/**
	 * 定时执行方法
	 */
	public abstract void execute();

}
