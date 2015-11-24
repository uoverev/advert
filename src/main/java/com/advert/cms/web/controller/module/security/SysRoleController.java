package com.advert.cms.web.controller.module.security;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContext;

import com.advert.cms.security.domain.SysResource;
import com.advert.cms.security.domain.SysRole;
import com.advert.cms.security.service.SysResourceService;
import com.advert.cms.security.service.SysRoleService;
import com.advert.cms.web.common.Constant;
import com.advert.cms.web.controller.common.CommonController;
import com.better.framework.utils.mapper.JsonMapper;



@Controller
@RequestMapping(value = "/module/security")
public class SysRoleController extends CommonController {

    @Autowired
    private SysRoleService sysRoleService;
    
    @Autowired
    private SysResourceService sysResourceService;

    //列表
    @RequestMapping(value ="sys_role_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
                                    @RequestParam(value="id", required=false) Long id
                                    ) {


        Map<String, Object> searchMap = new HashMap<String, Object>();
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();

        //主键ID
        if(id!=null){
            searchMap.put("EQ_id", id);
        }

        map.put("pageInfo", sysRoleService.query(getPageInfo(request), searchMap, sortMap));
        return "module.security.sys_role_list";
    }

    //增加-查看
    @RequestMapping(value ="sys_role_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){

        //获取资源
        map.put("rescJson",
                JsonMapper.nonDefaultMapper().toJson(
                        sysResourceService.queryByTypeAndStatus(null, 1)));
    	
        return "module.security.sys_role_detail";
    }


    //增加-保存
    @RequestMapping(value ="sys_role_add", method = RequestMethod.POST)
    public String postAdd(HttpServletRequest request, Map<String, Object> map,
                                SysRole sysRole,
                                @RequestParam(value = "resIds[]", required = false) Long[] resIds){
        try{
            //主键ID
            sysRole.setId(null);

            //拥有的资源
            sysRole.setRescs(new HashSet<SysResource>());
            if(resIds!=null) {
                for (Long resid : resIds) {
                    SysResource res = sysResourceService.get(resid);
                    sysRole.getRescs().add(res);
                }
            }
            
            sysRoleService.save(sysRole);
        }catch (Exception ex){
            logger.error("Save Method (inster) SysRole Error : " + sysRole.toString(), ex);
            //增加失败
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE), map);
        }
        //操作提示
        return super.operSuccess(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS),
                                                    "/module/security/sys_role_add.html", map);
    }

    //修改-查看
    @RequestMapping(value ="sys_role_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,
                                    @RequestParam(value = "id") Long id){
        SysRole sysRole = sysRoleService.get(id);
        if(sysRole==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", sysRole);

        //获取资源
        map.put("rescJson",
                JsonMapper.nonDefaultMapper().toJson(
                        sysResourceService.queryByTypeAndStatus(null, 1)));
    	
    	return "module.security.sys_role_detail";
    }

    //修改-保存
    @RequestMapping(value ="sys_role_update", method = RequestMethod.POST)
    public String postUpdate(HttpServletRequest request, Map<String, Object> map,
                                SysRole sysRole,
                                @RequestParam(value = "resIds[]", required = false) Long[] resIds){
        if(sysRole==null ||
                sysRole.getId()==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        try{
            SysRole sourceSysRole = sysRoleService.get(sysRole.getId());
            if(sourceSysRole==null){
                //没有记录
                return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
            }

            //角色名称
            sourceSysRole.setRoleName(sysRole.getRoleName());
            //描述信息
            sourceSysRole.setDescn(sysRole.getDescn());
            
            //拥有的资源
            sourceSysRole.setRescs(new HashSet<SysResource>());
            if(resIds!=null) {
                for (Long resid : resIds) {
                    SysResource res = sysResourceService.get(resid);
                    sourceSysRole.getRescs().add(res);
                }
            }
            
            sysRoleService.update(sourceSysRole);
            sysRole = sourceSysRole;
        }catch (Exception ex){
            logger.error("Save Method (Update) SysRole Error : " + sysRole.toString(), ex);
            //修改失败
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE), map);
        }
        //操作提示
        return super.operSuccess(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS),
        String.format("/module/security/sys_role_update.html?id=%s", sysRole.getId()), map);
    }

    //查看
    @RequestMapping(value ="sys_role_view")
    public String show(HttpServletRequest request, Map<String, Object> map,
                                    @RequestParam(value = "id") Long id){
        SysRole sysRole = sysRoleService.get(id);
        if(sysRole==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", sysRole);
        return "module.security.sys_role_view";
    }


}
