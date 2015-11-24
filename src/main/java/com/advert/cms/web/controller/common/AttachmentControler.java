package com.advert.cms.web.controller.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.advert.cms.core.domain.CmsAttachment;
import com.advert.cms.core.service.CmsAttachmentService;
import com.advert.cms.web.common.SessionFace;
import com.advert.cms.web.common.SessionUser;
import com.better.framework.common.dao.support.PageInfo;
import com.better.framework.utils.mapper.JsonMapper;

/**
 * com.feinno.eord.web.controller.cms.basic.
 * User: wangyx
 * Date: 14-12-2
 * Time: 下午2:59
 */
@Controller
public class AttachmentControler extends CommonController {

    private Logger logger = LoggerFactory.getLogger(AttachmentControler.class);

    @Resource
    private CmsAttachmentService cmsAttachmentService;

    @RequestMapping(value="/global/upload/save")
    //@ResponseBody
    public String upload(HttpServletRequest request, HttpServletResponse response){
    	SessionUser su = SessionFace.getSessionUser(request);
    	
    	if (su != null){
    		Long userId = su.getUserId();
            String sysKey="sys";
            logger.debug("referer:{}", request.getHeader("referer"));

            CommonsMultipartResolver mutilpartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            CmsAttachment attach=null;
            //request如果是Multipart类型、
            if (mutilpartResolver.isMultipart(request)) {
                //强转成 MultipartHttpServletRequest
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                //获取MultipartFile类型文件
                Iterator<String> it = multiRequest.getFileNames();
                if(it!=null && it.hasNext()){
                    MultipartFile fileDetail = multiRequest.getFile(it.next());
                    if (fileDetail != null){
                        attach = attachService.upload(userId, sysKey, "", fileDetail);
                    }
                }
            }
            if(attach != null){
	            Map<String, String> map = new HashMap<String, String>();
	            map.put("url", attach.getAttachUrl());
	            map.put("filename", attach.getAttachUrl());
	            StringBuffer strBuff = new StringBuffer("<script>").
	                    append("window.parent.uploaderImageComplete(").append(JsonMapper.nonEmptyMapper().toJson(map)).append(");").
	                    append("</script>");
	            try {
	                response.getWriter().print(strBuff.toString());
	            } catch (IOException e) {
	                logger.error("file upload error", e);
	            }
            }
    	}else{
    		logger.error("userId is null!!! check user login process.");
    	}
        return null;
    }

    @RequestMapping(value="/global/upload/delete")
    @ResponseBody
    public String list(HttpServletRequest request,
                       @RequestParam(value="url", required=false) String url){
        logger.debug("referer:{}", request.getHeader("referer"));
        try {
            attachService.delete(url);
            return "success";
        } catch (Exception e) {
            return "";
        }
    }

    @RequestMapping(value="/global/upload/list")
    public String list(HttpServletRequest request,
                       @RequestParam(value="callback", required=false) String callback,
                       @RequestParam(value="options", required=false) String opts,
                       Map<String, Object> map){
    	SessionUser su = SessionFace.getSessionUser(request);
    	if (su != null){
    		Long userId = su.getUserId();
	        String sysKey="sys";
	        logger.debug("referer:{}", request.getHeader("referer"));
	
	        Map<String, Object> searchMap = new HashMap<String, Object>();
	        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
	        searchMap.put("EQ_userId", userId);
	        searchMap.put("EQ_sysKey", sysKey);
	        sortMap.put("createTime", false);
	        PageInfo pageInfo = new PageInfo(24, 1);
	        pageInfo = cmsAttachmentService.query(pageInfo, searchMap, sortMap);
	        map.put("list", pageInfo.getPageResults());
	        map.put("callback", callback);
	        map.put("url", opts);
	
	        return "common/file_browser";
    	}else{
    		logger.error("userId is null!!! check user login process.");
    		return null;
    	}
    	
    }

    @RequestMapping(value="/global/upload/ajax/list")
    public String ajaxList(HttpServletRequest request,
                       @RequestParam(value="callback", required=false) String callback,
                       @RequestParam(value="options", required=false) String opts,
                       Map<String, Object> map){
    	SessionUser su = SessionFace.getSessionUser(request);
    	if (su != null){
    		Long userId = su.getUserId();
            String sysKey="sys";
            logger.debug("referer:{}", request.getHeader("referer"));

            Map<String, Object> searchMap = new HashMap<String, Object>();
            Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
            searchMap.put("EQ_userId", userId);
            searchMap.put("EQ_sysKey", sysKey);
            sortMap.put("createTime", false);

            PageInfo pageInfo = cmsAttachmentService.query(super.getPageInfo(request, 24), searchMap, sortMap);
            map.put("list", pageInfo.getPageResults());
            map.put("callback", callback);
            map.put("url", opts);

            return "common/image_waterfall";
    	}else{
    		logger.error("userId is null!!! check user login process.");
    		return null;
    	}

    }
}
