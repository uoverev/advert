/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
package com.advert.cms.web.controller.common;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.advert.cms.business.domain.Region;
import com.advert.cms.core.service.BasicService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author tangqiang on 2015年11月6日
 */
public class ProvinceCityController extends CommonController {

	@Autowired
	protected BasicService basicService;
	
	/**
	 * <p>Description:获取省市区数据，并放在request里</p>
	 * @param request
	 * @param defaultCode 默认展示的一级区域
	 * @author tangqiang on 2015年11月6日
	 */
	public void buildPcdData(HttpServletRequest request){
		List<Region> listRegion = basicService.getOneLevelRegionList();
		request.setAttribute("_regions", listRegion);
	}
	
	public List<Map<String, Object>> getNextLevel(String pcode){
		List<Region> listRegion = basicService.getRegionListByCode(pcode);
		List<Map<String, Object>> simplify = Lists.newArrayList();
		for(Region region : listRegion){
			Map<String, Object> map = Maps.newHashMap();
			map.put("code", region.getCode());
			map.put("name", region.getName());
			simplify.add(map);
		}
		return simplify;
	}
}
