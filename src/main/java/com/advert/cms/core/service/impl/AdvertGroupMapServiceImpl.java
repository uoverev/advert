package com.advert.cms.core.service.impl;


import org.springframework.stereotype.Service;

import com.advert.cms.core.dao.AdvertGroupMapDao;
import com.advert.cms.core.domain.AdvertGroupMap;
import com.advert.cms.core.service.AdvertGroupMapService;
import com.better.framework.common.service.EntityServiceImpl;

@Service("advertGroupMapService")
public class AdvertGroupMapServiceImpl extends EntityServiceImpl<AdvertGroupMap, AdvertGroupMapDao> implements AdvertGroupMapService {

}
