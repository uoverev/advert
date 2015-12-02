package com.advert.cms.core.service.impl;


import org.springframework.stereotype.Service;

import com.advert.cms.core.dao.AgentRoleDao;
import com.advert.cms.core.domain.AgentRole;
import com.advert.cms.core.service.AgentRoleService;
import com.better.framework.common.service.EntityServiceImpl;

@Service("agentRoleService")
public class AgentRoleServiceImpl extends EntityServiceImpl<AgentRole, AgentRoleDao> implements AgentRoleService {

}
