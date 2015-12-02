package com.advert.cms.core.service.impl;


import org.springframework.stereotype.Service;

import com.advert.cms.core.dao.AdvertLogDao;
import com.advert.cms.core.domain.AdvertLog;
import com.advert.cms.core.service.AdvertLogService;
import com.better.framework.common.service.EntityServiceImpl;

@Service("advertLogService")
public class AdvertLogServiceImpl extends EntityServiceImpl<AdvertLog, AdvertLogDao> implements AdvertLogService {

}
