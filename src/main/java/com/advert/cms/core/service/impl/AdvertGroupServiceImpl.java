package com.advert.cms.core.service.impl;


import org.springframework.stereotype.Service;

import com.advert.cms.core.dao.AdvertGroupDao;
import com.advert.cms.core.domain.AdvertGroup;
import com.advert.cms.core.service.AdvertGroupService;
import com.better.framework.common.service.EntityServiceImpl;

@Service("advertGroupService")
public class AdvertGroupServiceImpl extends EntityServiceImpl<AdvertGroup, AdvertGroupDao> implements AdvertGroupService {

	@Override
	public AdvertGroup getAdvertGroupByCode(String code) {
		return this.getEntityDao().getAdvertGroupByCode(code);
	}

}
