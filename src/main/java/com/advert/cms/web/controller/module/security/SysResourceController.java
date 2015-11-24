package com.advert.cms.web.controller.module.security;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import com.advert.cms.security.domain.SysResource;
import com.advert.cms.security.service.SysResourceService;
import com.advert.cms.web.common.Constant;
import com.advert.cms.web.controller.common.CommonController;
import com.better.framework.common.web.support.JsonResult;
import com.better.framework.utils.mapper.JsonMapper;



@Controller
@RequestMapping(value = "/module/security")
public class SysResourceController extends CommonController {

    @Autowired
    private SysResourceService sysResourceService;

    //列表
    @RequestMapping(value ="sys_resource_list")
    public String list(HttpServletRequest request, Map<String, Object> map){
        Map<String, Object> seachMap = new HashMap<String, Object>();
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        seachMap.put("EQ_parentId", 0);

        sortMap.put("sortNum", true);

        List<SysResource> list = sysResourceService.query(seachMap, sortMap);

        map.put("resources", JsonMapper.nonDefaultMapper().toJson(list));
    	
        return "module.security.sys_resource_list";
    }


    //增加-保存
    @RequestMapping(value ="sys_resource_add", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult postAdd(HttpServletRequest request, Map<String, Object> map,
                                SysResource sysResource){
        try{
            //主键ID
            sysResource.setId(null);
            sysResourceService.save(sysResource);
        }catch (Exception ex){
            logger.error("Save Method (inster) SysResource Error : " + sysResource.toString(), ex);
            //增加失败
            return new JsonResult("failure", new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        //操作提示
        return new JsonResult(true).appendData("id", sysResource.getId());
    }

    //修改-保存
    @RequestMapping(value ="sys_resource_update", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult postUpdate(HttpServletRequest request, Map<String, Object> map,
                                SysResource sysResource){
        if(sysResource==null ||
                sysResource.getId()==null){
            //没有记录
            return new JsonResult("failure", new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            SysResource sourceSysResource = sysResourceService.get(sysResource.getId());
            if(sourceSysResource==null){
                //没有记录
                return new JsonResult("failure", new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }
            
            //资源名称
            sourceSysResource.setTitle(sysResource.getTitle());
            //资源类型
            sourceSysResource.setRestype(sysResource.getRestype());
            //资源值
            sourceSysResource.setResString(sysResource.getResString());
            //权限
            sourceSysResource.setPermissionValue(sysResource.getPermissionValue());
            //描述
            sourceSysResource.setDescn(sysResource.getDescn());
            //状态
            sourceSysResource.setStatus(sysResource.getStatus());
            //排序
            sourceSysResource.setSortNum(sysResource.getSortNum());

            sysResourceService.update(sourceSysResource);
            sysResource = sourceSysResource;
        }catch (Exception ex){
            logger.error("Save Method (Update) SysResource Error : " + sysResource.toString(), ex);
            //修改失败
            return new JsonResult("failure", new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        //操作提示
        return new JsonResult(true);
    }

    //删除
    @RequestMapping(value ="sys_resource_del")
    @ResponseBody
    public JsonResult status(HttpServletRequest request, Map<String, Object> map,
                                @RequestParam(value = "ids[]") Long[] ids){
        try{
            sysResourceService.removes(ids);
        }catch (Exception ex){
            logger.error("Del Method (Del) SysResource Error : " + Arrays.toString(ids), ex);
            //删除失败提示
            return new JsonResult("failure", new RequestContext(request).getMessage(Constant.I18nMessage.DEL_FAILURE));
        }
        //操作提示
        return new JsonResult(true);
    }

    //修改排序和结构
    @RequestMapping(value ="sys_resource_sort")
    @ResponseBody
    public JsonResult sort(HttpServletRequest request, Map<String, Object> map,
                           @RequestParam(value = "ids[]") Long[] ids,
                           @RequestParam(value = "sortNums[]") Long[] sortNums,
                           @RequestParam(value = "parentIds[]") Long[] parentIds){
        try{
            sysResourceService.sort(ids, sortNums, parentIds);
        }catch (Exception ex){
            logger.error("Save Method (sort) SysResource Error ids : " + Arrays.toString(ids), ex);
            //修改排序失败提示
            return new JsonResult("failure", new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        //操作提示
        return new JsonResult(true);
    }

	
}
