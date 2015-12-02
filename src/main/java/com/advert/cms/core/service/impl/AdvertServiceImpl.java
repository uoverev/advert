package com.advert.cms.core.service.impl;


import org.springframework.stereotype.Service;

import com.advert.cms.core.dao.AdvertDao;
import com.advert.cms.core.domain.Advert;
import com.advert.cms.core.domain.dto.AdvertDto;
import com.advert.cms.core.service.AdvertService;
import com.better.framework.common.service.EntityServiceImpl;

@Service("advertService")
public class AdvertServiceImpl extends EntityServiceImpl<Advert, AdvertDao> implements AdvertService {

	@Override
	public AdvertDto getAdvertDto(String code) {
		
		return null;
	}

}
