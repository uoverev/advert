package com.advert.cms.web.controller.module.security;

import java.util.Arrays;
import java.util.Date;
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

import com.advert.cms.security.domain.SysMenu;
import com.advert.cms.security.service.SysMenuService;
import com.advert.cms.security.service.SysResourceService;
import com.advert.cms.web.common.Constant;
import com.advert.cms.web.controller.common.CommonController;
import com.advert.cms.web.utils.PinyinUtil;
import com.better.framework.common.web.support.JsonResult;
import com.better.framework.utils.mapper.JsonMapper;



@Controller
@RequestMapping(value = "/module/security")
public class SysMenuController extends CommonController {

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysResourceService sysResourceService;

    //列表
    @RequestMapping(value ="sys_menu_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
                                    @RequestParam(value="id", required=false) Long id
                                    ) {
        Map<String, Object> seachMap = new HashMap<String, Object>();
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        seachMap.put("EQ_parentId", 0);

        sortMap.put("sortNum", true);
        List<SysMenu> list = sysMenuService.query(seachMap, sortMap);

        map.put("menuJson", JsonMapper.nonDefaultMapper().toJson(list));

        //获取资源
        map.put("rescJson",
                JsonMapper.nonDefaultMapper().toJson(
                        sysResourceService.queryByTypeAndStatus("URL", 1)));
        return "module.security.sys_menu_list";
    }

    //增加-保存
    @RequestMapping(value ="sys_menu_add", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult postAdd(HttpServletRequest request, Map<String, Object> map,
                                SysMenu sysMenu){
        try{
            //主键ID
            sysMenu.setId(null);
            //拼音
            sysMenu.setTitleFirstSpell(PinyinUtil.cn2FirstSpell(sysMenu.getTitle()));
            sysMenu.setCreateDate(new Date());
            sysMenuService.save(sysMenu);
        }catch (Exception ex){
            logger.error("Save Method (inster) SysMenu Error : " + sysMenu.toString(), ex);
            //增加失败
            return new JsonResult("failure", new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        //操作提示
        return new JsonResult(true).appendData("id", sysMenu.getId());
    }

    //修改-保存
    @RequestMapping(value ="sys_menu_update", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult postUpdate(HttpServletRequest request, Map<String, Object> map,
                                SysMenu sysMenu){
        if(sysMenu==null ||
                sysMenu.getId()==null){
            //没有记录
            return new JsonResult("failure", new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            SysMenu sourceSysMenu = sysMenuService.get(sysMenu.getId());
            if(sourceSysMenu==null){
                //没有记录
                return new JsonResult("failure", new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            //菜单名称
            sourceSysMenu.setTitle(sysMenu.getTitle());
            //菜单图标
            sourceSysMenu.setIcon(sysMenu.getIcon());
            //显示方式
            sourceSysMenu.setShowMode(sysMenu.getShowMode());
            //描述
            sourceSysMenu.setDescn(sysMenu.getDescn());
            //状态
            sourceSysMenu.setStatus(sysMenu.getStatus());
            //资源
            sourceSysMenu.setResourceId(sysMenu.getResourceId());
            //拼音
            sourceSysMenu.setTitleFirstSpell(PinyinUtil.cn2FirstSpell(sysMenu.getTitle()));

            sysMenuService.update(sourceSysMenu);
            sysMenu = sourceSysMenu;
        }catch (Exception ex){
            logger.error("Save Method (Update) SysMenu Error : " + sysMenu.toString(), ex);
            //修改失败
            return new JsonResult("failure", new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        //操作提示
        return new JsonResult(true);
    }


    //删除
    @RequestMapping(value ="sys_menu_del")
    @ResponseBody
    public JsonResult delete(HttpServletRequest request, Map<String, Object> map,
                         @RequestParam(value = "id") Long id){

        try{

            if(0==sysMenuService.deleteTree(id)){
                //没有记录
                return new JsonResult("failure", new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }
        }catch (Exception ex){
            logger.error("Del Method (Del) SysMenu Error : " + id, ex);
            //删除失败提示
            return new JsonResult("failure", new RequestContext(request).getMessage(Constant.I18nMessage.DEL_FAILURE));
        }
        //操作提示
        return new JsonResult(true);
    }


    //修改排序和结构
    @RequestMapping(value ="sys_menu_sort")
    @ResponseBody
    public JsonResult sort(HttpServletRequest request, Map<String, Object> map,
                             @RequestParam(value = "ids[]") Long[] ids,
                             @RequestParam(value = "sortNums[]") Long[] sortNums,
                             @RequestParam(value = "parentIds[]") Long[] parentIds){
        try{
            sysMenuService.sort(ids, sortNums, parentIds);
        }catch (Exception ex){
            logger.error("Save Method (sort) SysMenu Error ids : " + Arrays.toString(ids), ex);
            //修改排序失败提示
            return new JsonResult("failure", new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        //操作提示
        return new JsonResult(true);
    }



}
