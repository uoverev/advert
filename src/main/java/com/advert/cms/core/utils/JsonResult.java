/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
package com.advert.cms.core.utils;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Maps;

/**
 * JSON Response Basic data
 * 
 * @author zhangpu
 * 
 */
@JsonIgnoreProperties(value = { "hibernateLazyInitializer",
		"javassistLazyInitializer" })
public class JsonResult<T> {

	private boolean success = true;

	private String code = "0";

	private String message = "";

	private Map<Object, Object> data;
	
	private T obj;
	
	public static <T> JsonResult<T> newJson(){
		return new JsonResult<T>();
	}
	
	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}

	public JsonResult() {
		super();
	}
	
	public JsonResult(boolean success) {
		super();
		this.success = success;
	}

	public JsonResult(String code, String message) {
		super();
		this.code = code;
		this.message = message;
		this.success = false;
	}
	
	public void initDataIfNull(){
		if(data == null)  data = Maps.newHashMap();
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<Object, Object> getData() {
		return data;
	}

	public void setData(Map<Object, Object> data) {
		this.data = data;
	}

	public JsonResult appendData(Object key, Object value) {
		initDataIfNull();
		this.data.put(key, value);
        return this;
	}

	public JsonResult appendData(Map<?, ?> map) {
		initDataIfNull();
		this.data.putAll(map);
        return this;
	}
}
