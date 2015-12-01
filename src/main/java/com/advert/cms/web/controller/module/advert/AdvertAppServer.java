package com.advert.cms.web.controller.module.advert;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.advert.cms.core.utils.JsonResult;
import com.advert.cms.web.controller.common.CommonController;

/**
 * 广告接口
 * @author fengshi on 2015.12.01
 *
 */
@Controller
public class AdvertAppServer extends CommonController {
	
	/**
	 * 心跳接口
	 * @param code
	 * @return
	 */
	@RequestMapping("heartBeat")
	public JsonResult<Object> heartBeat(String code){
		JsonResult<Object> result = new JsonResult<Object>();
		return result;
	}
	
	 

}
