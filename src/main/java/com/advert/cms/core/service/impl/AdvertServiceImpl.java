package com.advert.cms.core.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.advert.cms.core.dao.AdvertDao;
import com.advert.cms.core.domain.Advert;
import com.advert.cms.core.domain.AdvertGroup;
import com.advert.cms.core.domain.dto.AdvertDto;
import com.advert.cms.core.service.AdvertGroupService;
import com.advert.cms.core.service.AdvertService;
import com.better.framework.common.service.EntityServiceImpl;

@Service("advertService")
public class AdvertServiceImpl extends EntityServiceImpl<Advert, AdvertDao> implements AdvertService {

	@Resource
	private AdvertGroupService advertGroupService;
	
	@Override
	public AdvertDto getAdvertDto(String code) {
		if(StringUtils.isBlank(code)){
			return null;
		}
		//通过code查询客户端所在分组
		AdvertGroup advertGroup = advertGroupService.getAdvertGroupByCode(code);
		if(null != advertGroup){
			AdvertDto dto = new AdvertDto();
			//放入dto
			BeanUtils.copyProperties(advertGroup, dto);
			//通过分组查询分组下的广告分组
			List<Advert> list = queryAdvertByGroupId(advertGroup.getId());
			if(null != list){
				dto.setList(list);
			}
			return dto;
		}
		//通过广告分组查询当前时间下的广告列表
		return null;
	}

	@Override
	public List<Advert> queryAdvertByGroupId(Long groupId) {
		return this.getEntityDao().queryAdvertByGroupId(groupId);
	}

}
