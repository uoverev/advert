package com.advert.cms.security.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advert.cms.security.cache.SecurityCache;
import com.advert.cms.security.dao.SysRoleDao;
import com.advert.cms.security.domain.SysRole;
import com.advert.cms.security.service.SysRoleService;
import com.better.framework.common.exception.BusinessException;
import com.better.framework.common.service.EntityServiceImpl;

@Service("sysRoleService")
public class SysRoleServiceImpl extends EntityServiceImpl<SysRole, SysRoleDao> implements SysRoleService {

    private Logger logger = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    @Autowired
    private SecurityCache securityCache;

    @Override
    public void update(SysRole o) throws BusinessException {
        super.update(o);
        securityCache.clearAllAuthorization();
        securityCache.clearMenu(o.getId());
    }
}
