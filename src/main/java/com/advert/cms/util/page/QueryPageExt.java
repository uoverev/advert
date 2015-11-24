/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
package com.advert.cms.util.page;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.advert.cms.util.Condition;
import com.better.framework.common.dao.support.PageInfo;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author fuxiaofeng on 2015年8月3日
 */
@Repository
public class QueryPageExt {
	public <T> void query(String sql, Map<String, Object> keyword, Map<String, Boolean> orderby, PageInfo<T> pageinfo,
			Class<T> dtoEntity, PagedJdbcTemplate onlinePagedJdbcTemplate) {

		Condition conditon = getQuery(sql, keyword, orderby);
		String sqls = "select count(*) from (" + conditon.getSql() + ") AS A";
		Object[] params = conditon.getParams();

		// 总记录数
		long totalCount = onlinePagedJdbcTemplate.queryForCount(sqls, params);
		long totalPage = (totalCount % pageinfo.getCountOfCurrentPage() == 0 ? totalCount
				/ pageinfo.getCountOfCurrentPage() : totalCount / pageinfo.getCountOfCurrentPage() + 1);

		String corentsql = conditon.getSql();
		/* 分页 */
		int pagesize = pageinfo.getCountOfCurrentPage();
		
		long currentPage = pageinfo.getCurrentPage();
		if(currentPage > totalPage){
			currentPage = totalPage;
		}
		if(currentPage <= 0){
			currentPage = 1;
		}
		
		long startIndex = (currentPage - 1) * pagesize;
		
		if (pageinfo.getCurrentPage() != 0) {
			corentsql = corentsql + " limit " + startIndex + " , " + pagesize;
		}

		// 查询结果列表
		List<T> result = query(corentsql, params, dtoEntity, onlinePagedJdbcTemplate);

		// 设置分布对象
		pageinfo.setPageResults(result);
		pageinfo.setTotalCount(totalCount);
		pageinfo.setTotalPage(totalPage);
		pageinfo.setCurrentPage((int)currentPage);

	}

	private <T> List<T> query(String corentsql, Object[] params, Class<T> dtoEntity,
			PagedJdbcTemplate onlinePagedJdbcTemplate) {
		return onlinePagedJdbcTemplate.query(corentsql, params, BeanPropertyRowMapper.newInstance(dtoEntity));
	}

	/**
	 * Title:
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param sql
	 * @param keyword
	 * @param orderby
	 * @return
	 */
	private Condition getQuery(String sql, Map<String, Object> keyword, Map<String, Boolean> orderby) {
		Condition conditon = new Condition();
		try {
			StringBuffer querysql = new StringBuffer();
			querysql.append(" SELECT * FROM ( ").append(sql).append(" ) AS B ");
			querysql.append(" WHERE 1 = 1 ");
			List<Object> params = new ArrayList<Object>();
			// 封裝SQL
			if(null !=keyword && !keyword.isEmpty()){
				for (String key : keyword.keySet()) {
					String[] keys = key.split("_");
					String columName = coverdColumName(keys[1]);
					// 操作符
					String oparation = keys[0];
					Object columValue = keyword.get(key);
					
					if ("EQ".equals(oparation)) {
						querysql.append(" and ").append(columName).append(" = ? ");
						params.add(columValue);
					} else if ("LT".equals(oparation)) {
						querysql.append(" and ").append(columName).append(" < ? ");
						params.add(columValue);
					} else if ("GT".equals(oparation)) {
						querysql.append(" and ").append(columName).append(" > ? ");
						params.add(columValue);
					} else if ("LE".equals(oparation)) {
						querysql.append(" and ").append(columName).append(" <= ? ");
						params.add(columValue);
					} else if ("GE".equals(oparation)) {
						querysql.append(" and ").append(columName).append(" >= ? ");
						params.add(columValue);
					} else if ("LIKE".equals(oparation)) {
						querysql.append(" and ").append(columName).append(" like ? ");
						params.add("%" + columValue + "%");
					} else if ("LLIKE".equals(oparation)) {
						querysql.append(" and ").append(columName).append(" like  ? ");
						params.add("%" + columValue);
					} else if ("RLIKE".equals(oparation)) {
						querysql.append(" and ").append(columName).append(" like  ? ");
						params.add(columValue + "%");
					}
				}
				
			}
			// 做排序
			if (!orderby.isEmpty() && orderby.size() > 0) {
				querysql.append(" order by ");
				int i = 1;
				for (String key : orderby.keySet()) {
					boolean orders = orderby.get(key);
					querysql.append(coverdColumName(key) + " ");
					querysql.append(orders ? "ASC" : "DESC");
					if (i < orderby.size()) {
						querysql.append(",");
					}
					i++;
				}
			}

			Object[] paramso = params.toArray();
			conditon.setSql(querysql.toString());
			conditon.setParams(paramso);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return conditon;
	}

	/**
	 * Title:將字符串轉成數據庫字段
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param columName
	 * @return
	 */
	public String coverdColumName(String columName) {
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < columName.length(); i++) {
			char c = columName.charAt(i);
			if (!Character.isLowerCase(c) && c!='.') {
				str.append("_");
			}
			str.append(c);
		}
		String strco = str.toString().toLowerCase();
		return strco;
	}

}
