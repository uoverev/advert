package com.advert.cms.web.common;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import com.advert.cms.security.domain.SysRole;
import com.advert.cms.security.domain.SysUser;

/**
 * User: wangyx
 * Date: 14-3-26
 * Time: 下午7:20
 */

public class SessionUser implements HttpSessionBindingListener{

    public static final String SESSION_USER_OBJECT_KEY="session_user_obj";

    private SessionUser(){}

    public static SessionUser bulider(SysUser sysUser){
        return new SessionUser().update(sysUser);
    }

    public SessionUser update(SysUser sysUser){
        this.setUserId(sysUser.getId());
        this.setUsername(sysUser.getUsername());
        this.setNickname(sysUser.getNickname());
        this.setUsertype(sysUser.getUsertype());
        this.setRole(sysUser.getRole());
        return this;
    }

	/** 用户ID*/
	private Long userId;

	/** 登录用户名 */
	private String username;
	
	/** 操作员姓名 */
    private String nickname;
	
	/** 用户类型 */
	private Integer usertype;

    /** 角色 */
    private SysRole role;
	


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        if(SESSION_USER_OBJECT_KEY.equals(event.getName())
                && event.getValue() instanceof SessionUser){
            SessionUser sessionUser = (SessionUser) event.getValue();
            SessionFace.userMap.put(sessionUser.getUsername(), sessionUser);
        }
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        if(SESSION_USER_OBJECT_KEY.equals(event.getName())
                && event.getValue() instanceof SessionUser){
            SessionUser sessionUser = (SessionUser) event.getValue();
            SessionFace.userMap.remove(sessionUser.getUsername());
        }
    }
}
