package com.advert.cms.core.service.impl;


import org.springframework.stereotype.Service;

import com.advert.cms.core.dao.AdvertClentSideDao;
import com.advert.cms.core.domain.AdvertClentSide;
import com.advert.cms.core.service.AdvertClentSideService;
import com.better.framework.common.service.EntityServiceImpl;

@Service("advertClentSideService")
public class AdvertClentSideServiceImpl extends EntityServiceImpl<AdvertClentSide, AdvertClentSideDao> implements AdvertClentSideService {

}
