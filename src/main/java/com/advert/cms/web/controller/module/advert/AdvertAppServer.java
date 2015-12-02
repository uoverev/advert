package com.advert.cms.web.controller.module.advert;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.advert.cms.core.domain.AdvertLog;
import com.advert.cms.core.domain.dto.AdvertDto;
import com.advert.cms.core.service.AdvertLogService;
import com.advert.cms.core.service.AdvertService;
import com.advert.cms.core.utils.JsonResult;
import com.advert.cms.web.controller.common.CommonController;

/**
 * 广告接口
 * @author fengshi on 2015.12.01
 *
 */
@Controller
public class AdvertAppServer extends CommonController {
	
	//日志
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private AdvertService advertService;
	
	@Resource
	private AdvertLogService advertLogService;
	
	/**
	 * 心跳接口
	 * @param code
	 * @return
	 */
	@ResponseBody
	@RequestMapping("heartBeat")
	public JsonResult<Object> heartBeat(@RequestParam(value="code",required=true)String code){
		JsonResult<Object> result = new JsonResult<Object>();
		
		return result;
	}
	
	/**
	 * 播放列表
	 * @param code
	 * @return
	 */
	@ResponseBody
	@RequestMapping("playList")
	public JsonResult<AdvertDto> playList(@RequestParam(value="code",required=true)String code){
		JsonResult<AdvertDto> result = new JsonResult<AdvertDto>();
		
		return result;
	}
	
	/**
	 * 播放次数统计
	 * @param code
	 * @param groupId
	 * @param advertId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("statistics")
	public JsonResult<Object> statistics(@RequestParam(value="code",required=true)String code,
										@RequestParam(value="groupId",required=true)Long groupId,
										@RequestParam(value="advertId",required=true)Long advertId){
		JsonResult<Object> result = new JsonResult<Object>();
		AdvertLog log = new AdvertLog();
		log.setAdvertId(advertId);
		log.setGroupId(groupId);
		log.setPlayTime(new Date());
		try {
			advertLogService.save(log);
			result.setSuccess(true);
			result.setCode("0");
			result.setMessage("统计次数成功！");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setCode("-1");
			result.setMessage("统计次数失败！");
			logger.error("保存统计失败：{}",e);
		}
		return result;
	}
	 
}
