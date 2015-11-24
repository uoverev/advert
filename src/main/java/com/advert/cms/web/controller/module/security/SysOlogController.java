package com.advert.cms.web.controller.module.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContext;

import com.advert.cms.security.domain.SysOlog;
import com.advert.cms.security.service.SysOlogService;
import com.advert.cms.web.common.Constant;
import com.advert.cms.web.controller.common.CommonController;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;



@Controller
@RequestMapping(value = "/module/security")
public class SysOlogController extends CommonController {

    @Autowired
    private SysOlogService sysOlogService;

    //列表
    @RequestMapping(value ="sys_olog_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
                                    @RequestParam(value="module", required=false) String module,
                                    @RequestParam(value="moduleName", required=false) String moduleName,
                                    @RequestParam(value="action", required=false) String action,
                                    @RequestParam(value="operateUser", required=false) String operateUser,
                                    @RequestParam(value="operateUserId", required=false) Long operateUserId
                                    ) {


        Map<String, Object> searchMap = new HashMap<String, Object>();
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();

        //模块
        if(StringUtils.isNotBlank(module)){
            searchMap.put("LIKE_module", module);
        }
        //模块名称
        if(StringUtils.isNotBlank(moduleName)){
            searchMap.put("LIKE_moduleName", moduleName);
        }
        //操作
        if(StringUtils.isNotBlank(action)){
            searchMap.put("LIKE_action", action);
        }
        //操作员
        if(StringUtils.isNotBlank(operateUser)){
            searchMap.put("LIKE_operateUser", operateUser);
        }
        //操作员ID
        if(operateUserId!=null){
            searchMap.put("EQ_operateUserId", operateUserId);
        }

        map.put("pageInfo", sysOlogService.query(getPageInfo(request), searchMap, sortMap));
        return "module.security.sys_olog_list";
    }

    //查看
    @RequestMapping(value ="sys_olog_view")
    public String show(HttpServletRequest request, Map<String, Object> map,
                                    @RequestParam(value = "id") Long id){
        SysOlog sysOlog = sysOlogService.get(id);
        if(sysOlog==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", sysOlog);
        return "module.security.sys_olog_view";
    }



}
