/**
 * RequestUtil.java
 * User: junsansi
 * Date: 2015-1-9
 */
package com.advert.cms.core.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author junsansi Date: 2015-1-9
 */
public class RequestUtil {

	public static String getRequestIpAddr(HttpServletRequest request) {

		// 保存用户最后登录时间和登录IP
		String loginIp = request.getHeader("X-Forwarded-For");
		if (null == loginIp || loginIp.length() == 0
				|| "unknown".equalsIgnoreCase(loginIp))
			loginIp = request.getHeader("Proxy-Client-lastLoginIp");
		if (loginIp == null || loginIp.length() == 0
				|| "unknown".equalsIgnoreCase(loginIp)) {
			loginIp = request.getHeader("Proxy-Client-IP");
		}
		if (loginIp == null || loginIp.length() == 0
				|| "unknown".equalsIgnoreCase(loginIp)) {
			loginIp = request.getHeader("WL-Proxy-Client-IP");
		}
		if (loginIp == null || loginIp.length() == 0
				|| "unknown".equalsIgnoreCase(loginIp))
			loginIp = request.getHeader("WL-Proxy-Client-lastLoginIp");
		if (loginIp == null || loginIp.length() == 0
				|| "unknown".equalsIgnoreCase(loginIp))
			loginIp = request.getHeader("HTTP_CLIENT_lastLoginIp");
		if (loginIp == null || loginIp.length() == 0
				|| "unknown".equalsIgnoreCase(loginIp))
			loginIp = request.getHeader("HTTP_X_FORWARDED_FOR");
		if (loginIp == null || loginIp.length() == 0
				|| "unknown".equalsIgnoreCase(loginIp)) {
			loginIp = request.getRemoteAddr();
		}
	    if ("127.0.0.1".equals(loginIp) || "0:0:0:0:0:0:0:1".equals(loginIp))
	        try {
	        	loginIp = InetAddress.getLocalHost().getHostAddress();
	        }
	        	catch (UnknownHostException unknownhostexception) {
	        }
		return loginIp;
	}

}
