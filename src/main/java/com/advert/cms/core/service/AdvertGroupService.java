package com.advert.cms.core.service;

import com.advert.cms.core.domain.AdvertGroup;
import com.better.framework.common.service.EntityService;

/**
 * advert_group Service
 *
 * Date: 2015-12-02 13:09:49
 *
 * @author Code Generator
 *
 */
public interface AdvertGroupService extends EntityService<AdvertGroup> {

	/**
	 * 通过客户端唯一编码查询广告分组信息
	 * @param code
	 * @return
	 */
	public AdvertGroup getAdvertGroupByCode(String code);
}
