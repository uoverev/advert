package com.advert.cms.web.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.advert.cms.security.service.SysUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

/**
 * com.feinno.entrepreneurship.web.controller.
 * User: wangyx
 * Date: 14-11-7
 * Time: 下午4:25
 */
@Controller
public class IndexController extends CommonController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) throws IOException{
        return "login";
    }

}
