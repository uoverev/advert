package com.advert.cms.core.service.impl;


import org.springframework.stereotype.Service;

import com.advert.cms.core.dao.AdvertGroupAgentDao;
import com.advert.cms.core.domain.AdvertGroupAgent;
import com.advert.cms.core.service.AdvertGroupAgentService;
import com.better.framework.common.service.EntityServiceImpl;

@Service("advertGroupAgentService")
public class AdvertGroupAgentServiceImpl extends EntityServiceImpl<AdvertGroupAgent, AdvertGroupAgentDao> implements AdvertGroupAgentService {

}
