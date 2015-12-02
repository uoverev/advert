package com.advert.cms.core.service;

import com.advert.cms.core.domain.Advert;
import com.advert.cms.core.domain.dto.AdvertDto;
import com.better.framework.common.service.EntityService;

/**
 * advert Service
 *
 * Date: 2015-12-02 13:09:15
 *
 * @author Code Generator
 *
 */
public interface AdvertService extends EntityService<Advert> {
	
	/**
	 * 通过code和系统时间，返回该时间段应该播放的广告列表
	 * @param code
	 * @return
	 */
	public AdvertDto getAdvertDto(String code);

}
