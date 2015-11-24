package com.advert.cms.web.controller.module.security;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import com.advert.cms.core.utils.RequestUtil;
import com.advert.cms.security.domain.SysRole;
import com.advert.cms.security.domain.SysUser;
import com.advert.cms.security.service.SysRoleService;
import com.advert.cms.security.service.SysUserService;
import com.advert.cms.web.common.Constant;
import com.advert.cms.web.common.SessionFace;
import com.advert.cms.web.controller.common.CommonController;
import com.better.framework.common.exception.BusinessException;
import com.better.framework.common.web.support.JsonResult;
import com.better.module.security.support.Cryptos;



@Controller
@RequestMapping(value = "/module/security")
public class SysUserController extends CommonController {

    
	@Autowired
    private SysUserService sysUserService;
    
    @Autowired
    private SysRoleService sysRoleService;

	int HASH_INTERATIONS = 1024;
	int SALT_SIZE = 8;
	
    //列表
    @RequestMapping(value ="sys_user_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
                                    @RequestParam(value="id", required=false) Long id,
                                    @RequestParam(value="username", required=false) String username
                                    ) {


        Map<String, Object> searchMap = new HashMap<String, Object>();
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();

        //主键ID
        if(id!=null){
            searchMap.put("EQ_id", id);
        }
        //登录用户名
        if(StringUtils.isNotBlank(username)){
            searchMap.put("LIKE_username", username);
        }

        map.put("pageInfo", sysUserService.query(getPageInfo(request), searchMap, sortMap));
        return "module.security.sys_user_list";
    }

    //增加-查看
    @RequestMapping(value ="sys_user_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
    	List<SysRole> roles = sysRoleService.getAll();
    	map.put("roles", roles);
    	
        return "module.security.sys_user_detail";
    }


    //增加-保存
    @RequestMapping(value ="sys_user_add", method = RequestMethod.POST)
    public String postAdd(HttpServletRequest request, Map<String, Object> map,
                                SysUser sysUser){
        try{
        	
        	boolean checkExists = sysUserService.checkUsername(sysUser.getUsername());
        	if (checkExists){
        		//已有同名记录存在
        		return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.CHECK_RECORD_EXISTS), map);
        	}
			if (StringUtils.isNotBlank(sysUser.getPassword())) {
				entryptPassword(sysUser);
			}else{
				return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.NEED_ENTER_PASSWORD), map);
			}
        	//用户所属角色
        	sysUser.setRole(this.loadRoleFormRequest(request));
        	
            //主键ID
            sysUser.setId(null);
            //创建时间
            sysUser.setCreateDate(new Date());
            //最后登录时间
            sysUser.setLastLoginDate(new Date());
            //最后登录IP
            sysUser.setLastLoginIp(RequestUtil.getRequestIpAddr(request));

            sysUser.setUsertype(1);
            
            sysUserService.save(sysUser);
        }catch (Exception ex){
            logger.error("Save Method (inster) SysUser Error : " + sysUser.toString(), ex);
            //增加失败
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE), map);
        }
        //操作提示
        return super.operSuccess(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS),
                                                    "/module/security/sys_user_add.html", map);
    }

    //修改-查看
    @RequestMapping(value ="sys_user_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,
                                    @RequestParam(value = "id") Long id){
        SysUser sysUser = sysUserService.get(id);
        if(sysUser==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", sysUser);
    	
        List<SysRole> roles = sysRoleService.getAll();
    	map.put("roles", roles);
    	
    	return "module.security.sys_user_detail";
    }

    //修改-保存
    @RequestMapping(value ="sys_user_update", method = RequestMethod.POST)
    public String postUpdate(HttpServletRequest request, Map<String, Object> map,
                                SysUser sysUser){
        if(sysUser==null ||
                sysUser.getId()==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        try{
            SysUser sourceSysUser = sysUserService.get(sysUser.getId());
            if(sourceSysUser==null){
                //没有记录
                return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
            }
        	//用户所属角色
            sourceSysUser.setRole(this.loadRoleFormRequest(request));
            //操作员姓名
            //sourceSysUser.setNickname(sysUser.getNickname());
            //电子邮件
            sourceSysUser.setEmail(sysUser.getEmail());
            //状态
            sourceSysUser.setStatus(sysUser.getStatus());
            //描述
            sourceSysUser.setDescn(sysUser.getDescn());
            //过期时间
            sourceSysUser.setExpiredDate(sysUser.getExpiredDate());
            //解锁时间
            sourceSysUser.setUnlockDate(sysUser.getUnlockDate());
            sysUserService.update(sourceSysUser);
            sysUser = sourceSysUser;
        }catch (Exception ex){
            logger.error("Save Method (Update) SysUser Error : " + sysUser.toString(), ex);
            //修改失败
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE), map);
        }
        //操作提示
        return super.operSuccess(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS),
        String.format("/module/security/sys_user_update.html?id=%s", sysUser.getId()), map);
    }

    //查看
    @RequestMapping(value ="sys_user_view")
    public String show(HttpServletRequest request, Map<String, Object> map,
                                    @RequestParam(value = "id") Long id){
        SysUser sysUser = sysUserService.get(id);
        if(sysUser==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", sysUser);
        return "module.security.sys_user_view";
    }



    //修改状态
    @RequestMapping(value ="sys_user_status")
    public String status(HttpServletRequest request, Map<String, Object> map,
                                @RequestParam(value = "id") Long id,
                                @RequestParam(value="status", required=true) Integer status){

        try{
            if(0==sysUserService.updateStatusById(id, status)){
                //没有记录
                return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
            }
        }catch (Exception ex){
            logger.error("Del Method (Del) SysUser Error : " + id, ex);
            //删除失败提示
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.DEL_FAILURE), map);
        }
        //返回页
        String ref = request.getHeader("referer");
        if(StringUtils.isBlank(ref)){
            ref = "/module/security/sys_user_list.html";
        }
        //操作提示
        return super.operSuccess(new RequestContext(request).getMessage(Constant.I18nMessage.DEL_SUCCESS), ref, map);
    }


    //重置密码-查看
    @RequestMapping(value ="sys_user_pass", method = RequestMethod.GET)
    public String resetPass(HttpServletRequest request, Map<String, Object> map, 
    		@RequestParam(value = "id", required=true) Long id){
        SysUser sysUser = sysUserService.get(id);
        if(sysUser==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", sysUser);
    	
        return "module.security.sys_user_pass";
    }


    //重置密码-修改
    @RequestMapping(value ="sys_user_pass", method = RequestMethod.POST)
    public String resetPassSave(HttpServletRequest request, Map<String, Object> map,
    		@RequestParam(value = "id", required=true) Long id){
	  	String password = request.getParameter("newpassword");
	  	String password2 = request.getParameter("newpassword2");
	  	if (password == null || password2 == null || !password.equals(password2)) {
	  		return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.USER_PASS_FORMAT_ERR), map);
	  	}else{
	  		
	        SysUser sysUser = sysUserService.get(id);
	        if(sysUser==null){
	            //没有记录
	            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
	        }
	        sysUser.setPassword(password);
	        entryptPassword(sysUser);
	        sysUserService.save(sysUser);
	        
			String location = "sys_user_list.html";
	        return super.operSuccess(sysUser.getUsername()+" "+new RequestContext(request).getMessage(Constant.I18nMessage.USER_PASS_UPDATE_SUCCESS),location, map);
	  	}
    }

    //修改密码-查看
    @RequestMapping(value ="change_pass", method = RequestMethod.GET)
    public String changePass(HttpServletRequest request, Map<String, Object> map){
        SysUser sysUser = sysUserService.get(1L);
        if(sysUser==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", sysUser);
    	
        return "module.security.change_pass";
    }
    
    //修改密码-保存
    @RequestMapping(value ="change_pass", method = RequestMethod.POST)
    public String changePassSave(HttpServletRequest request, Map<String, Object> map){
    	String orginalPassword = request.getParameter("oldpassword");
    	String password = request.getParameter("newpassword");
	  	String password2 = request.getParameter("newpassword2");
	  	if (StringUtils.isBlank(password) || StringUtils.isBlank(password2) || !password.equals(password2)) {
	  		return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.USER_PASS_FORMAT_ERR), map);
	  	}else{
            SysUser sysUser = sysUserService.get(SessionFace.getSessionUser(request).getUserId());
	        if(sysUser==null){
	            //没有记录
	            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
	        }else{
	        	boolean result = validatePassword(sysUser, orginalPassword);
	        	if (result){
	    	        sysUser.setPassword(password);
	    	        entryptPassword(sysUser);
	    	        sysUserService.save(sysUser);
	    			String location = "sys_user_list.html";
	    	        return super.operSuccess(sysUser.getUsername()+" "+new RequestContext(request).getMessage(Constant.I18nMessage.USER_PASS_UPDATE_SUCCESS),location, map);
	        	}else{
	        		return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.USER_PASS_ORIGINAL_CHECK_ERR), map);
	        	}
	        }
	  	}
    }  
    
    /**
	 * 
	 * Title:检查是否用户已经存在
	 * <p>Description:</p>
	 * @param request
	 * @return
	 */
	@RequestMapping("check_name")
	@ResponseBody
	public JsonResult checkWaitress(HttpServletRequest request)
	{
		String username = request.getParameter("username");
		JsonResult result = new JsonResult();
		boolean status = sysUserService.checkUsername(username);
		if(status)
		{
			result.setMessage(Constant.I18nMessage.CHECK_RECORD_EXISTS);
			result.setCode("0");
			result.setSuccess(true);
		}else{
			result.setMessage(Constant.I18nMessage.CHECK_RECORD_NOT_EXISTS);
			result.setSuccess(false);
			result.setCode("-1");
		}
		return result;
	}

	private SysRole loadRoleFormRequest(HttpServletRequest request) {
//		String roleStr = request.getParameter("roleId");
//		roleStr = roleStr.substring(0, roleStr.length() - 1);
//		String[] roleArray = roleStr.split("\\|");
		
		Long roleId = Long.valueOf(request.getParameter("roleId"));
		SysRole role = sysRoleService.get(roleId);

		return role;
	}

	/**
	 * 验证加密后的密码是否相同
	 * @param user
	 * @param plaintPassword
	 * @return
	 * @throws BusinessException
	 */
	public boolean validatePassword(SysUser user, String plaintPassword)
			throws BusinessException {
		return entryptPassword(plaintPassword, user.getSalt()).equals(
				user.getPassword());
	}
	
	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 * 
	 * @param user
	 */
	private void entryptPassword(SysUser user) {
        user.setSalt(Cryptos.generatorSalt(SALT_SIZE));
        user.setPassword(Cryptos.sha1(user.getPassword(), user.getSalt(), HASH_INTERATIONS));
	}

	private String entryptPassword(String plainPassword, String salt) {
        return Cryptos.sha1(plainPassword, salt, HASH_INTERATIONS);
	}

}
