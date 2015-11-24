package com.advert.cms.util;

import org.apache.commons.lang3.StringUtils;

import com.better.framework.common.exception.BusinessException;

public class Asserts {

	public static void isNull(Object object, String message) {
		if (object == null) {
			throw new BusinessException(message);
		}
		if (object instanceof String) {
			if (StringUtils.isBlank((String) object)) {
				throw new BusinessException(message);
			}
		}
	}

	public static void notNull(Object object, String message) {
		if (object != null) {
			throw new BusinessException(message);
		}
		if (object instanceof String) {
			if (StringUtils.isNotBlank((String) object)) {
				throw new BusinessException(message);
			}
		}
	}
}
