package com.advert.cms.core.service.impl;


import org.springframework.stereotype.Service;

import com.advert.cms.core.dao.UserAgentRoleDao;
import com.advert.cms.core.domain.UserAgentRole;
import com.advert.cms.core.service.UserAgentRoleService;
import com.better.framework.common.service.EntityServiceImpl;

@Service("userAgentRoleService")
public class UserAgentRoleServiceImpl extends EntityServiceImpl<UserAgentRole, UserAgentRoleDao> implements UserAgentRoleService {

}
