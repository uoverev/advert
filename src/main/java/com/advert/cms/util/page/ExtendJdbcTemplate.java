package com.advert.cms.util.page;



import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcOperations;

public interface ExtendJdbcTemplate extends JdbcOperations {

	public long queryForCount(String sql, Object... object);

	
	public long queryForCount(String sql, Map<String, Object> conditions);

	public <T> List<T> queryForList(Class<T> transClass, String sql, Object... object);

	
	public <T> List<T> queryForList(Class<T> transClass, String sql, Map<String, Object> conditions);

	
	public <T> List<T> queryForList(Class<T> transClass, String sql, Map<String, Object> conditions,
			Map<String, Boolean> orders);

	public <T> Page<T> queryForPage(Class<T> transClass, String sql, Pageable pageable, Object... object);

	public <T> Page<T> queryForPage(Class<T> transClass, String sql, Pageable pageable, Map<String, Boolean> orderBys,
			Object... object);

	public <T> Page<T> queryForPage(Class<T> transClass, String sql, Map<String, Object> conditions, Pageable pageable);

	public <T> Page<T> queryForPage(Class<T> transClass, String sql, Map<String, Object> conditions, Pageable pageable,
			Map<String, Boolean> orders);

}
