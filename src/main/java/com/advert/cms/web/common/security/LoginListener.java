package com.advert.cms.web.common.security;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.advert.cms.security.domain.SysUser;
import com.advert.cms.security.service.SysUserService;
import com.advert.cms.web.common.Constant;
import com.advert.cms.web.common.SessionFace;
import com.advert.cms.web.common.SessionUser;
import com.better.module.security.exception.AuthenticationException;
import com.better.module.security.listener.LoginLogoutListener;

/**
 * Created by Administrator on 2015/5/26.
 */
public class LoginListener implements LoginLogoutListener {

    private Logger logger = LoggerFactory.getLogger(LoginListener.class);

    @Autowired
    private SysUserService sysUserService;

    @Override
    public void beforeLogin(String userName, HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        logger.debug("用户 {} 准备登陆", userName);
    }

    @Override
    public void onLoginSuccess(String userName, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("用户 {} 登陆成功", userName);
        afterRememberMe(userName, request, response);
    }

    @Override
    public void onLoginFailure(String userName, AuthenticationException e, HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("loginError",
                ResourceBundle.getBundle("i18n/message", request.getLocale()).getString(Constant.I18nMessage.LOGIN_ERROR));
        logger.debug("用户 {} 登陆失败", userName);
    }

    @Override
    public void afterRememberMe(String userName, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("用户 {} 记住我 登陆", userName);
        //设置 sessionUser
        SysUser sysUser = sysUserService.findByUsername(userName);
        SessionFace.setSessionUser(request, SessionUser.bulider(sysUser));
    }

    @Override
    public void beforeLogout(String userName, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("用户 {} 注销", userName);
    }
}
