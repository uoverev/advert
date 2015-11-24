package com.advert.cms.util;
/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author fuxiaofeng on 2015年8月3日
 */
public class Condition {
	private String sql;
	private Object[] params;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}
}
