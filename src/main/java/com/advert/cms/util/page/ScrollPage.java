package com.advert.cms.util.page;

/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */

import java.util.List;
/**
 * Title:滚动分页类
 * <p>Description:配合实现滚动分页功能，与传统分页不同</p>
 * <p>使用方法：需要设置此对象的各个参数，然后使用 @see {@link PagedJdbcTemplate#queryPage(Class, ScrollPage, String, Object...)}进行查询</p>
 * <p>第一页不用设置value值，从第二页开始，需要将value值设置为上一页最后一条数据，且排序列字段的值</p>
 * <p>传统分页对象请使用@see com.feinno.framework.common.dao.support.PageInfo</p>
 * Copyright (c) feinno 2013-2016
 * @author tangqiang on 2014年12月19日
 */
public class ScrollPage<T> {
	
	public static final String ASC = "ASC";
	public static final String DESC = "DESC";
	
	/** 查询分页结果 */
	private List<T> pageResults;
	
	/** 分页条件的列名，默认为id*/
	private String whereColumn = "id";
	
	private List<String> whereColumns;
	
	/** 设置分页前查询结果是升序还是降序*/
	private String order  ;
	private List<String> orders  ;
	
	/** 上一页最后一条数据的order字段的值 如：order 为id,则为上一页最后条数据id的值*/
	private Object value;
	
	private List<Object> values;
	
	/** 每页显示多少条*/
	private int pageSize;

	public List<T> getPageResults() {
		return pageResults;
	}
	public void setPageResults(List<T> pageResults) {
		this.pageResults = pageResults;
	}
	public String getWhereColumn() {
		return whereColumn;
	}
	public void setWhereColumn(String whereColumn) {
		this.whereColumn = whereColumn;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<String> getWhereColumns() {
		return whereColumns;
	}
	public void setWhereColumns(List<String> whereColumns) {
		this.whereColumns = whereColumns;
	}
	public List<Object> getValues() {
		return values;
	}
	public void setValues(List<Object> values) {
		this.values = values;
	}
	public List<String> getOrders() {
		return orders;
	}
	public void setOrders(List<String> orders) {
		this.orders = orders;
	}
}