package com.advert.cms.web.common;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: SessionFace.java</p>
 * <p>Description: session 门面类</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: feinno </p>
 * <p>team: FetionyyTechnicalTeam</p>
 * @date 2014-3-26
 * @version 1.0
 * @author wangyuxin
 */

public class SessionFace {

    public static Map<String, SessionUser> userMap = new HashMap<String, SessionUser>();

    /**
     * 获取 session Id
     * @param request
     * @return String
     */
    public static String getSessionId(HttpServletRequest request){
        return request.getSession().getId();
    }

    /**
     * 获取session属性
     * @param request
     * @param key
     * @return Object
     */
    public static Object getAttribute(HttpServletRequest request, String key){
        return request.getSession().getAttribute(key);
    }

    /**
     * 设置session 属性
     * @param request
     * @param key
     * @param value
     */
    public static void setAttribute(HttpServletRequest request, String key, String value){
        request.getSession().setAttribute(key, value);
    }
    
    /**
     * 设置session 属性
     * @param request
     * @param key
     * @param obj
     */
    public static void setAttribute(HttpServletRequest request, String key, Object obj){
    	request.getSession().setAttribute(key, obj);
    }

    
    
    /**
     * 删除session属性
     * @param request
     * @param key
     */
    public static void removeAttribute(HttpServletRequest request, String key){
        request.getSession().removeAttribute(key);
    }

    /**
     * 设置当前会话用户
     * @param request
     * @param sessionUser
     * @return
     */
    public static void setSessionUser(HttpServletRequest request, SessionUser sessionUser){
        setAttribute(request,SessionUser.SESSION_USER_OBJECT_KEY, sessionUser);
    }

    /**
     * 获取当前会话用户
     * @param request
     * @return
     */
    public static SessionUser getSessionUser(HttpServletRequest request){
        return (SessionUser) getAttribute(request,SessionUser.SESSION_USER_OBJECT_KEY);
    }

    
    /**
     * 获取当前会话用户是否登录
     * @param request
     * @return
     */
    public static Boolean isUserLogin(HttpServletRequest request){
        return getSessionUser(request)!=null;
    }

    
    /**
     * 获取当前会话用户所在权限组
     * @param request
     * @return
     */    
    public static Long getGroupId(HttpServletRequest request){
        return Long.valueOf(getSessionUser(request).getUsertype());
    }

    /**
     * 获取在线的用户
     * @param userName - 用户名
     * @return
     */
    public static SessionUser getOnlineSessionUser(String userName){
        return userMap.get(userName);
    }
}
