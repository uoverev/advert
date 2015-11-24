package com.advert.cms.util.page;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

public class SearchFilter {
	public enum Operator {
		/**
		 * Equals
		 */
		EQ,
		/**
		 * NOT Equals
		 */
		NOTEQ,
		/**
		 * Like
		 **/
		LIKE,
		/**
		 * LEFT Like
		 **/
		LLIKE,
		/**
		 * RIGHT Like
		 **/
		RLIKE,
		/**
		 * NOT Like
		 **/
		NLIKE,
		/**
		 * GREAT
		 **/
		GT,
		/**
		 * LESS
		 **/
		LT,
		/**
		 * GREAT Equals
		 */
		GTE,
		/**
		 * LESS Equals
		 */
		LTE,
		/**
		 * IN
		 */
		IN,
		/**
		 * NOT IN
		 */
		NOTIN,
		/**
		 * IS NULL
		 */
		NULL,
		/**
		 * IS NOT NULL
		 */
		NOTNULL;

		public static Operator getOpeartor(String name) {
			for (Operator op : Operator.values()) {
				if (op.toString().equals(name)) {
					return op;
				}
			}
			return null;
		}
	}

	public final String fieldName;
	public final String originalFieldName;
	public Object value;
	public final Object originalValue;
	public final Operator operator;

	public SearchFilter(String fieldName, String originalFieldName, Operator operator, Object value) {
		this.fieldName = fieldName;
		this.originalFieldName = originalFieldName;
		this.value = value;
		this.originalValue = value;
		this.operator = operator;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public static List<SearchFilter> parse(Map<String, Object> searchParams) {
		List<SearchFilter> filters = Lists.newArrayList();
		if (searchParams == null) {
			return Collections.emptyList();
		}
		for (Entry<String, Object> entry : searchParams.entrySet()) {
			String key = entry.getKey();
			if (!StringUtils.contains(key, '_')) {
				throw new BusinessException("查询条件不符合规范!");
			}
			int index = StringUtils.indexOfAny(key, "_");
			String[] names = StringUtils.split(key, "_", (index + 1));
			if (names.length < 2) {
				throw new BusinessException("查询条件截取出错!");
			}
			Operator op = Operator.getOpeartor(names[0]);
			if (op == null) {
				throw new BusinessException("查询方式未定义!");
			}
			String filedName = names[1];
			Object value = entry.getValue();
			if (value == null || StringUtils.isBlank(String.valueOf(value))) {
				continue;
			}
			filters.add(new SearchFilter(filedName, coverdColumName(filedName), op, value));
		}
		return filters;
	}

	public static String coverdColumName(String columName) {
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < columName.length(); i++) {
			char c = columName.charAt(i);
			if (!Character.isLowerCase(c) && c != 46 && c != 95) {
				str.append((char) 95).append((char) (c + 32));
			} else {
				str.append(c);
			}
		}
		return str.toString();
	}
}
