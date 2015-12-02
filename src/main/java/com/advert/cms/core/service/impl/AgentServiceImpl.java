package com.advert.cms.core.service.impl;


import org.springframework.stereotype.Service;

import com.advert.cms.core.dao.AgentDao;
import com.advert.cms.core.domain.Agent;
import com.advert.cms.core.service.AgentService;
import com.better.framework.common.service.EntityServiceImpl;

@Service("agentService")
public class AgentServiceImpl extends EntityServiceImpl<Agent, AgentDao> implements AgentService {

}
