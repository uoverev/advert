package com.advert.cms.security.service.impl;


import org.springframework.stereotype.Service;

import com.advert.cms.security.dao.SysOlogDao;
import com.advert.cms.security.domain.SysOlog;
import com.advert.cms.security.service.SysOlogService;
import com.better.framework.common.service.EntityServiceImpl;

@Service("sysOlogService")
public class SysOlogServiceImpl extends EntityServiceImpl<SysOlog, SysOlogDao> implements SysOlogService {


}
