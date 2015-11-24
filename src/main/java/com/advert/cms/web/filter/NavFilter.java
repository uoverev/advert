package com.advert.cms.web.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advert.cms.security.service.SysMenuService;
import com.advert.cms.web.common.SessionFace;
import com.advert.cms.web.common.SessionUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 菜单高亮设置
 */
@Service
public class NavFilter implements Filter {

	private Logger log = LoggerFactory.getLogger(NavFilter.class);

    @Autowired
    private SysMenuService sysMenuService;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

        //加载菜单
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        if(sessionUser!=null){
            request.setAttribute("authorisedMenus", sysMenuService.queryAuthorisedMenus(sessionUser.getRole().getId()));
        }
        //导航高亮控制
        String lm=request.getParameter("lm");
        //左面菜单
        if(StringUtils.isNotBlank(lm)){
            SessionFace.setAttribute(request, "lm", lm);
        }

		chain.doFilter(request, response);
	}

    @Override
    public void destroy() {

    }

}



