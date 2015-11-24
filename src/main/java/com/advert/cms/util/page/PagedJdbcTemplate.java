/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
package com.advert.cms.util.page;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.advert.cms.util.DateUtil;
import com.advert.cms.util.StringUtil;
import com.better.framework.common.dao.support.PageInfo;
import com.google.common.collect.Lists;

/**
 * Title:提供了分页查询的模板类
 * <p>Description:提供自定义SQL分页查询功能</p>
 * <p></p>
 * Copyright (c) feinno 2013-2016
 * @author tangqiang on 2014年12月11日
 */
@SuppressWarnings("deprecation")
public class PagedJdbcTemplate extends MysqlExtendJdbcTemplate{
	
	/**
	 * <p>Description:分页查询</p>
	 * @param transClass 查询结果类型
	 * @param sql 
	 * @param pageInfo 主要接收currentPage和countOfCurrentPage参数
	 * @param object 查询条件
	 * @return
	 * @author tangqiang on 2015年1月13日
	 */
	public <T> Page<T> queryForPage(Class<T> transClass, String sql, PageInfo<T> pageInfo, Object... object) {
		Pageable pageable = new PageRequest(pageInfo.getCurrentPage(),pageInfo.getCurrentPage());
		return this.queryForPage(transClass, sql, pageable, object);
	}

	/**
	 * <p>Description:分页查询</p>
	 * @param transClass 查询结果类型
	 * @param sql
	 * @param conditions 查询条件
	 * @param pageInfo 主要接收currentPage和countOfCurrentPage参数
	 * @return
	 * @author tangqiang on 2015年1月13日
	 */
	public <T> Page<T> queryForPage(Class<T> transClass, String sql, Map<String, Object> conditions, PageInfo<T> pageInfo) {
		Pageable pageable = new PageRequest(pageInfo.getCurrentPage(),pageInfo.getCurrentPage());
		return queryForPage(transClass, sql, conditions, pageable, null);
	}

	/**
	 * <p>Description:分页查询</p>
	 * @param transClass 查询结果类型
	 * @param sql
	 * @param conditions 查询条件
	 * @param pageInfo 主要接收currentPage和countOfCurrentPage参数
	 * @param orders 排序对象，key为要排序的列名，当VALUE为true时升序排列，VALUE为false的时候降序排列
	 * @return
	 * @author tangqiang on 2015年1月13日
	 */
	public <T> Page<T> queryForPage(Class<T> transClass, String sql, Map<String, Object> conditions, PageInfo<T> pageInfo,
			Map<String, Boolean> orders) {
		Pageable pageable = new PageRequest(pageInfo.getCurrentPage(),pageInfo.getCurrentPage());
		return queryForPage(transClass, sql, conditions, pageable, orders);
	}
	
	/**
	 * <p>Description:自定义瀑布流分页查询</p>
	 * @param resultClass 查询结果封装类
	 * @param scrollPage 分页对象 @see {@link ScrollPage}
	 * @param sql 
	 * @param conditions key为LIKE、RLIKE、LLIKE
	 * @return
	 */
	public <T> ScrollPage<T> queryPage(Class<T> resultClass,ScrollPage<T> scrollPage,String sql,Map<String, Object> conditions){
		StringBuffer pageSql = new StringBuffer( "select * from (" + sql + " ) t ");
		if(scrollPage.getValue()!=null){
			pageSql.append(" where ").append(scrollPage.getWhereColumn());
			if(scrollPage.getOrder().equalsIgnoreCase(ScrollPage.ASC)){
				pageSql.append(" >'");
			}else{
				pageSql.append(" <'");
			}
			Object value = scrollPage.getValue();
			if(value instanceof Date){
				Date v = (Date)value;
				pageSql.append(DateUtil.formateSimpleDate(v));
			}else{
				String v = String.valueOf(value);
				pageSql.append(v);
			}
			pageSql.append("'");
		}
		pageSql.append(" limit ").append("0,").append(scrollPage.getPageSize());
		List<T> result = null;
		List<Object> args = Lists.newArrayList();
		if(conditions != null && !conditions.isEmpty()){
			for(String key : conditions.keySet()){
				if(key.indexOf("LIKE")!=-1){
					args.add("%"+conditions.get(key)+"%");
				}else if(key.indexOf("RLIKE")!=-1){
					args.add("%"+conditions.get(key));
				}else if(key.indexOf("LLIKE")!=-1){
					args.add(conditions.get(key)+"%");
				}else{
					args.add(conditions.get(key));
				}
			}
		}
		if(StringUtil.isEmpty(args)){
			result = queryForList(pageSql.toString(),resultClass);
		}else{
			result = queryForList(resultClass, pageSql.toString(), args.toArray());
		}
		scrollPage.setPageResults(result);
		return scrollPage;
	}
	
/**
 * 重载分页查询使其支持多字段排序
 * <p>Description:</p>
 * @param resultClass
 * @param scrollPage
 * @param sql
 * @param conditions
 * @param type 完全是为了区别开来，无意义
 * @author fengshi on 2015年6月11日
 */
	public <T> ScrollPage<T> queryPage(Class<T> resultClass,ScrollPage<T> scrollPage,String sql,Map<String, Object> conditions,int type){
		StringBuffer pageSql = new StringBuffer( "select * from (" + sql + " ) t ");
		pageSql.append(" where 1=1 ");
		String whereColumn = null;
		Object value =null;
		List<Object> values = scrollPage.getValues();
		for (int i = 0; i < scrollPage.getWhereColumns().size(); i++) {
			whereColumn = scrollPage.getWhereColumns().get(i);
			if(StringUtils.isNotBlank(whereColumn)){
				if(null!=values&&values.size()>0){
					value = values.get(i);
					if(null!=value){
						pageSql.append(" and ").append(whereColumn);
						if(scrollPage.getOrders().get(i).equalsIgnoreCase(ScrollPage.ASC)){
							if(i < scrollPage.getWhereColumns().size() -1){
								pageSql.append(" >= '");
							}else{
								pageSql.append(" > '");
							}
						}else{
							if(i < scrollPage.getWhereColumns().size() -1){
								pageSql.append(" <= '");
							}else{
								pageSql.append(" < '");
							}
						}
						if(value instanceof Date){
							Date v = (Date)value;
							pageSql.append(DateUtil.formateSimpleDate(v));
						}else{
							String v = String.valueOf(value);
							pageSql.append(v);
						}
						pageSql.append("'");
					}
				}
			}
		}
		pageSql.append(" limit ").append("0,").append(scrollPage.getPageSize());
		List<T> result = null;
		List<Object> args = Lists.newArrayList();
		if(conditions != null && !conditions.isEmpty()){
			for(String key : conditions.keySet()){
				if(key.indexOf("LIKE")!=-1){
					args.add("%"+conditions.get(key)+"%");
				}else if(key.indexOf("RLIKE")!=-1){
					args.add("%"+conditions.get(key));
				}else if(key.indexOf("LLIKE")!=-1){
					args.add(conditions.get(key)+"%");
				}else{
					args.add(conditions.get(key));
				}
			}
		}
		if(StringUtil.isEmpty(args)){
			result = queryForList(pageSql.toString(),resultClass);
		}else{
			result = queryForList(resultClass, pageSql.toString(), args.toArray());
		}
		scrollPage.setPageResults(result);
		return scrollPage;
	}
	
	/**
	 * 通过原始QL转换后查询Count
	 * 
	 * @param nativeSql
	 * @param ql
	 * @param args
	 * @return
	 */
	@SuppressWarnings("unused")
	private long queryCount( String sql, Object... args) {
		String countQueryString = "select count(*) " + removeSelect(removeOrders(sql));
		return this.queryForCount(countQueryString, args);
	}
	
	/**
	 * 去除select 子句，未考虑union的情况
	 */
	private static String removeSelect(String sql) {
		int beginPos = sql.toLowerCase().indexOf("from");
		return sql.substring(beginPos);
	}
	
	/**
	 * 去除orderby 子句
	 */
	private static String removeOrders(String sql) {
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(sql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}
	
	/**
	 * list 查询
	 */
	@Override
	public <T> List<T> queryForList(String sql, Class<T> elementType) throws DataAccessException {
		return query(sql, BeanPropertyRowMapper.newInstance(elementType));
	}
}
