/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
package com.advert.tool.common.util;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.better.framework.common.web.support.JsonResult;


/**
 * Title:使用HTTP请求获取JSON数据
 * JSON数据格式强制为 @see org.feinno.framework.web.JsonResult
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author tangqiang on 2015年5月20日
 */
public class HttpClientJsonResultUtil {
	
	private static Log log = LogFactory.getLog(HttpClientJsonResultUtil.class);
	
	private static JsonResult transform(String content){
		try {
			JsonResult json = JSON.parseObject(content, JsonResult.class);
			return json;
		} catch (Exception e) {
			log.error("Failed to transform JsonResult--->content:"+content);
			return null;
		}
	}
	
	/**
	 * 发送HTTP_GET请求
	 * @see 该方法会自动关闭连接,释放资源
	 * @param requestURL    请求地址(含参数)
	 * @param decodeCharset 解码字符集,解析响应数据时用之,其为null时默认采用UTF-8解码
	 * @return  org.feinno.framework.web.JsonResult
	 */
	public static JsonResult sendGetRequest(String reqURL, String decodeCharset){
		return transform(HttpClientUtil.sendGetRequest(reqURL, decodeCharset));
	}
	
	
	/**
	 * 发送HTTP_POST请求
	 * @see 该方法为<code>sendPostRequest(String,String,boolean,String,String)</code>的简化方法
	 * @see 该方法在对请求数据的编码和响应数据的解码时,所采用的字符集均为UTF-8
	 * @see 当<code>isEncoder=true</code>时,其会自动对<code>sendData</code>中的[中文][|][ ]等特殊字符进行<code>URLEncoder.encode(string,"UTF-8")</code>
	 * @param isEncoder 用于指明请求数据是否需要UTF-8编码,true为需要
	 */
	public static JsonResult sendPostRequest(String reqURL, String sendData, boolean isEncoder){
		return sendPostRequest(reqURL, sendData, isEncoder, null, null);
	}
	
	
	/**
	 * 发送HTTP_POST请求
	 * @see 该方法会自动关闭连接,释放资源
	 * @see 当<code>isEncoder=true</code>时,其会自动对<code>sendData</code>中的[中文][|][ ]等特殊字符进行<code>URLEncoder.encode(string,encodeCharset)</code>
	 * @param reqURL        请求地址
	 * @param sendData      请求参数,若有多个参数则应拼接成param11=value11¶m22=value22¶m33=value33的形式后,传入该参数中
	 * @param isEncoder     请求数据是否需要encodeCharset编码,true为需要
	 * @param encodeCharset 编码字符集,编码请求数据时用之,其为null时默认采用UTF-8解码
	 * @param decodeCharset 解码字符集,解析响应数据时用之,其为null时默认采用UTF-8解码
	 * @return org.feinno.framework.web.JsonResult
	 */
	public static JsonResult sendPostRequest(String reqURL, String sendData, boolean isEncoder, String encodeCharset, String decodeCharset){
		return transform(HttpClientUtil.sendPostRequest( reqURL,  sendData,  isEncoder,  encodeCharset,  decodeCharset));
	}
	
	
	/**
	 * 发送HTTP_POST请求
	 * @see 该方法会自动关闭连接,释放资源
	 * @see 该方法会自动对<code>params</code>中的[中文][|][ ]等特殊字符进行<code>URLEncoder.encode(string,encodeCharset)</code>
	 * @param reqURL        请求地址
	 * @param params        请求参数
	 * @param encodeCharset 编码字符集,编码请求数据时用之,其为null时默认采用UTF-8解码
	 * @param decodeCharset 解码字符集,解析响应数据时用之,其为null时默认采用UTF-8解码
	 * @return org.feinno.framework.web.JsonResult
	 */
	public static JsonResult sendPostRequest(String reqURL, Map<String, String> params, String encodeCharset, String decodeCharset){
		return transform(HttpClientUtil.sendPostRequest( reqURL,  params,  encodeCharset,  decodeCharset));
	}
	
	/**
	 * 发送HTTP_POST请求
	 * 
	 * @see 该方法会自动关闭连接,释放资源
	 * @see 该方法会自动对<code>params</code>中的[中文][|][ ]等特殊字符进行
	 *      <code>URLEncoder.encode(string,encodeCharset)</code>
	 * @param reqURL
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @param encodeCharset
	 *            编码字符集,编码请求数据时用之,其为null时默认采用UTF-8解码
	 * @param decodeCharset
	 *            解码字符集,解析响应数据时用之,其为null时默认采用UTF-8解码
	 * @return org.feinno.framework.web.JsonResult
	 */
	public static JsonResult sendPostRequest(String reqURL, Map<String, String> params, String encodeCharset,
			String decodeCharset, Map<String, String> headers) {
		return transform(HttpClientUtil.sendPostRequest( reqURL, params,  encodeCharset,
			 decodeCharset,  headers));
	}
	
	/**
	 * 发送HTTPS_POST请求
	 * @see 该方法为<code>sendPostSSLRequest(String,Map<String,String>,String,String)</code>方法的简化方法
	 * @see 该方法在对请求数据的编码和响应数据的解码时,所采用的字符集均为UTF-8
	 * @see 该方法会自动对<code>params</code>中的[中文][|][ ]等特殊字符进行<code>URLEncoder.encode(string,"UTF-8")</code>
	 */
	public static JsonResult sendPostSSLRequest(String reqURL, Map<String, String> params){
		return sendPostSSLRequest(reqURL, params, null, null);
	}
	
	
	/**
	 * 发送HTTPS_POST请求
	 * @see 该方法会自动关闭连接,释放资源
	 * @see 该方法会自动对<code>params</code>中的[中文][|][ ]等特殊字符进行<code>URLEncoder.encode(string,encodeCharset)</code>
	 * @param reqURL        请求地址
	 * @param params        请求参数
	 * @param encodeCharset 编码字符集,编码请求数据时用之,其为null时默认采用UTF-8解码
	 * @param decodeCharset 解码字符集,解析响应数据时用之,其为null时默认采用UTF-8解码
	 * @return org.feinno.framework.web.JsonResult
	 */
	public static JsonResult sendPostSSLRequest(String reqURL, Map<String, String> params, String encodeCharset, String decodeCharset){
		return transform(HttpClientUtil.sendPostSSLRequest( reqURL, params,  encodeCharset,  decodeCharset));
	}
	
	
	/**
	 * 发送HTTP_POST请求
	 * @see 若发送的<code>params</code>中含有中文,记得按照双方约定的字符集将中文<code>URLEncoder.encode(string,encodeCharset)</code>
	 * @see 本方法默认的连接超时时间为30秒,默认的读取超时时间为30秒
	 * @param reqURL 请求地址
	 * @param params 发送到远程主机的正文数据,其数据类型为<code>java.util.Map<String, String></code>
	 * @return org.feinno.framework.web.JsonResult
	 */
	public static JsonResult sendPostRequestByJava(String reqURL, Map<String, String> params){
		return transform(HttpClientUtil.sendPostRequestByJava( reqURL,  params));
	}
	
	
	/**
	 * 发送HTTP_POST请求
	 * @see 若发送的<code>sendData</code>中含有中文,记得按照双方约定的字符集将中文<code>URLEncoder.encode(string,encodeCharset)</code>
	 * @see 本方法默认的连接超时时间为30秒,默认的读取超时时间为30秒
	 * @param reqURL   请求地址
	 * @param sendData 发送到远程主机的正文数据
	 * @return org.feinno.framework.web.JsonResult
	 */
	public static JsonResult sendPostRequestByJava(String reqURL, String sendData){
		return transform(HttpClientUtil.sendPostRequestByJava( reqURL,  sendData));
	}

	
}
