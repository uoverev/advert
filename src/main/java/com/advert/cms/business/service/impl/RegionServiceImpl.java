package com.advert.cms.business.service.impl;


import org.springframework.stereotype.Service;

import com.advert.cms.business.dao.RegionDao;
import com.advert.cms.business.domain.Region;
import com.advert.cms.business.service.RegionService;
import com.better.framework.common.service.EntityServiceImpl;

@Service("regionService")
public class RegionServiceImpl extends EntityServiceImpl<Region, RegionDao> implements RegionService {

}
