package com.advert.cms.core.service.impl;


import org.springframework.stereotype.Service;

import com.advert.cms.core.dao.AgentRoleMapDao;
import com.advert.cms.core.domain.AgentRoleMap;
import com.advert.cms.core.service.AgentRoleMapService;
import com.better.framework.common.service.EntityServiceImpl;

@Service("agentRoleMapService")
public class AgentRoleMapServiceImpl extends EntityServiceImpl<AgentRoleMap, AgentRoleMapDao> implements AgentRoleMapService {

}
