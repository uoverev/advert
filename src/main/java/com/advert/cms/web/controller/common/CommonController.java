package com.advert.cms.web.controller.common;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.util.WebUtils;

import com.advert.cms.core.service.CmsAttachmentServiceFacade;
import com.advert.cms.web.common.Constant;
import com.better.framework.common.dao.support.PageInfo;
import com.better.framework.common.exception.BusinessException;
import com.better.framework.common.web.support.JsonResult;

/**
 * com.feinno.eord.web.controller. User: wangyx Date: 14-11-22 Time: 上午10:33
 */
public class CommonController {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	protected CmsAttachmentServiceFacade attachService;

	/**
	 * 初始化绑定数据，加入字符串转日期
	 * 
	 * @param request
	 * @param binder
	 */
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor dateEditor = new CustomDateEditor(format, true);
		binder.registerCustomEditor(Date.class, dateEditor);
	}

	/**
	 * 分页
	 * 
	 * @param request
	 * @param pageSize
	 * @return
	 */
	protected PageInfo getPageInfo(HttpServletRequest request, int pageSize) {
		int currentPage = 1;
		if (StringUtils.isNotBlank(request.getParameter("pageNo"))) {
			try {
				currentPage = Integer.parseInt(request.getParameter("pageNo"));
			} catch (Exception ex) {
			}
		}
		PageInfo pageInfo = new PageInfo(pageSize, currentPage);
		return pageInfo;
	}

	/**
	 * 分页
	 * 
	 * @param request
	 * @return
	 */
	protected PageInfo getPageInfo(HttpServletRequest request) {
		return getPageInfo(request, 10);
	}

	/**
	 * <p>
	 * Description: 分页对象
	 * </p>
	 * 
	 * @param request
	 * @param pageSize
	 * @return
	 */
	protected Pageable getPageable(HttpServletRequest request, int pageSize) {
		int currentPage = 1;
		if (StringUtils.isNotBlank(request.getParameter("pageNo"))) {
			try {
				currentPage = Integer.parseInt(request.getParameter("pageNo"));
			} catch (Exception ex) {
			}
		}
		return new PageRequest((currentPage - 1), pageSize);
	}

	/**
	 * <p>
	 * Description:分页对象
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	protected Pageable getPageable(HttpServletRequest request) {
		return getPageable(request, 10);
	}

	protected <T> PageInfo<T> asPageInfo(Page<T> page) {
		PageInfo<T> pageInfo = new PageInfo<T>();
		pageInfo.setCountOfCurrentPage(page.getSize());
		pageInfo.setCurrentPage(page.getNumber() + 1);
		pageInfo.setTotalCount(page.getTotalElements());
		pageInfo.setPageResults(page.getContent());
		return pageInfo;
	}

	/**
	 * 操作成功提示
	 * 
	 * @param msg
	 *            - 提示信息
	 * @param location
	 *            - 跳转页面
	 * @param map
	 * @return
	 */
	protected String operSuccess(String msg, String location, Map<String, Object> map) {
		map.put("alertinfo", Constant.Application.COMMON_SUCCESS_ALERTINFO);
		map.put("msg", msg);
		map.put("location", location);
		return "module.common.message";
	}

	/**
	 * 操作失败提示
	 * 
	 * @param msg
	 *            - 提示信息
	 * @param map
	 * @return
	 */
	protected String operFailure(String msg, Map<String, Object> map) {
		map.put("alertinfo", Constant.Application.COMMON_DANGER_ALERTINFO);
		map.put("msg", msg);
		return "module.common.message";
	}
	
	protected void handleException(String action, Exception e,
            HttpServletRequest request) {
        String message = getExceptionMessage(action, e);
        saveMessage(request, message);
        logger.warn(message);
    }
	
	/**
     * 错误处理重载( for json )
     * 
     * @param result
     * @param action
     * @param e
     */
    protected void handleException(JsonResult result, String action, Exception e) {
        String message = getExceptionMessage(action, e);
        logger.debug(message, e);
        result.setSuccess(false);
        if(e instanceof BusinessException){
            BusinessException be = (BusinessException)e;
            result.setCode(be.getErrorCode());
            result.setMessage(be.getResourceMessage());
        }else{
            result.setCode(e.getClass().toString());
            result.setMessage(message);
        }

    }
    
    /**
     * 向View层传递message时将message放入httpSession的messages变量中.
     * 放在session中能保证message即使Redirect也不会消失。 需配合
     * {@link com.feinno.framework.common.web.support.framwork.common.web.acooly.common.util.filer.MessageFilter
     * MessageFilter}使用, MessageFilter实现从Session中读取message并放入到request中
     * 然后，从session中删除message
     */
    @SuppressWarnings("unchecked")
    protected void saveMessage(HttpServletRequest request, String message) {
        if (StringUtils.isNotBlank(message)) {
            List<Object> messages = (List<Object>) WebUtils
                    .getOrCreateSessionAttribute(request.getSession(),
                            "messages", ArrayList.class);
            messages.add(message);
        }
    }
    
    protected String getExceptionMessage(String action, Exception e) {
        Throwable source = getSqlException(e);
        return action
                + " -> "
                + e.getMessage()
                + (source != null ? " --> DB_ERROR:" + source.getMessage() : "");
    }

    protected Throwable getSqlException(Exception e) {
        Throwable source = e;
        // 简单查询异常的8层Cause
        for (int i = 0; i < 8; i++) {
            if (source.getCause() == null) {
                return null;
            } else {
                source = source.getCause();
            }
            if (source.getClass().toString()
                    .equals(SQLException.class.toString())) {
                return source.getCause();
            }
        }
        return source;
    }

}
