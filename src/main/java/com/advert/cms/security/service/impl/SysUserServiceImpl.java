package com.advert.cms.security.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.advert.cms.security.cache.SecurityCache;
import com.advert.cms.security.dao.SysUserDao;
import com.advert.cms.security.domain.SysResource;
import com.advert.cms.security.domain.SysUser;
import com.advert.cms.security.service.SysUserService;
import com.advert.cms.web.common.SessionFace;
import com.advert.cms.web.common.SessionUser;
import com.better.framework.common.exception.BusinessException;
import com.better.framework.common.service.EntityServiceImpl;
import com.better.module.security.api.SubjectInfo;
import com.better.module.security.api.SubjectService;

@Service("sysUserService")
public class SysUserServiceImpl extends EntityServiceImpl<SysUser, SysUserDao>
		implements SysUserService, SubjectService {

	private Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

	@Autowired
	private SecurityCache securityCache;

	@Override
	public void update(SysUser o) throws BusinessException {
		super.update(o);
		try {
			securityCache.clearAuthorization(o.getUsername());
			SessionUser sessionUser = SessionFace.getOnlineSessionUser(o
					.getUsername());
			if (sessionUser != null) {
				sessionUser.update(o);
			}
		} catch (Exception ex) {
			logger.error("", ex);
		}
	}

	@Override
	@Transactional
	public int updateStatusById(Long id, Integer status) {
		return super.getEntityDao().updateStatusById(id, status);
	}

	@Override
	public SysUser findByUsername(String username) {
		return super.getEntityDao().findByUsername(username);
	}

	@Override
	@Transactional
	public int updateUserpassById(Long id, String userpass, String salt) {
		return super.getEntityDao().updateUserpassById(id, userpass, salt);
	}

	@Override
	@Transactional
	public int updateUserLoginById(Long id, Date lastLoginDate,
			String lastLoginIp) {
		return super.getEntityDao().updateUserLoginById(id, lastLoginDate,
				lastLoginIp);
	}

	@Override
	public boolean checkUsername(String username) {
		if (super.getEntityDao().findByUsername(username) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public SubjectInfo getSubject(String userName) {
		SubjectInfo subjectInfo = new SubjectInfo();
		//查询用户状态为启用用户
		SysUser sysUser = this.findByUsernameAndStatus(userName,
				SysUser.STATUS_START);
		subjectInfo.setPassword(sysUser.getPassword());
		subjectInfo.setPwdSalt(sysUser.getSalt());
		return subjectInfo;
	}

	@Override
	public List<String> listRole(String userName) {
		SysUser sysUser = this.findByUsername(userName);
		if (sysUser != null && sysUser.getRole() != null) {
			return Arrays.asList(sysUser.getRole().getRoleName());
		}
		return null;
	}

	@Override
	public List<String> listPermission(String userName) {
		SysUser sysUser = this.findByUsername(userName);
		if (sysUser != null && sysUser.getRole() != null
				&& sysUser.getRole().getRescs() != null) {
			List<String> resList = new ArrayList<String>();
			for (SysResource resource : sysUser.getRole().getRescs()) {
				resList.add(resource.getPermissionValue());
			}
			return resList;
		}
		return null;
	}

	/**
	 * 通过用户名和用户状态查询用户
	 */
	@Override
	public SysUser findByUsernameAndStatus(String username, Integer status) {
		return super.getEntityDao().findByUsernameAndStatus(username, status);
	}
}
