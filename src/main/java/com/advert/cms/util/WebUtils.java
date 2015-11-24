package com.advert.cms.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.advert.cms.util.fileupload.FileUtils;
import com.advert.cms.util.fileupload.FileUtils.UploadFileCallback;
import com.better.framework.common.exception.BusinessException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Web工具类
 * 
 * @author yuanjie.wu
 */
public abstract class WebUtils {

	private static final String[] DATE_PATTERNS = { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd" };

	private static final DateConverter dateConvert = new DateConverter();

	static {
		dateConvert.setPatterns(DATE_PATTERNS);
		ConvertUtils.register(dateConvert, Date.class);
	}

	/**
	 * 将HttpServletRequest请求参数值设置到对象对应属性中。
	 * <ul>
	 * <li>注意：request.getParameterValues(name) 与source中属性名称必须相同</li>
	 * <li>目前只支持：java原始类型和{@link java.util.Data}类型
	 * </ul>
	 * 
	 * @param source
	 *            - 需要设置值的对象
	 * @param request
	 *            - ServletRequest请求
	 * @throws Exception
	 * @author yuanjie.wu
	 */
	public static void requstToBean(Object source, ServletRequest request) throws Exception {
		requstToBean(source, request, null);
	}

	/**
	 * 将HttpServletRequest请求参数值设置到对象对应属性中。
	 * <ul>
	 * <li>注意：request.getParameterValues(name) 与source中属性名称必须相同</li>
	 * <li>目前只支持：java原始类型和{@link java.util.Data}类型
	 * </ul>
	 * 
	 * @param source
	 *            - 需要设置值的对象
	 * @param request
	 *            - ServletRequest请求
	 * @param ignoreProperties
	 *            - 过滤属性
	 * @throws Exception
	 * @author yuanjie.wu
	 * @see HttpServletRequest#getParameterValues(String)
	 * @see BeanUtils#setProperty(Object, String, Object)
	 */
	public static void requstToBean(Object source, ServletRequest request, String[] ignoreProperties) throws Exception {
		Asserts.isNull(source, "需要设置值的对象不能为空！");
		Asserts.isNull(request, "ServletRequest请求不能为空！");
		try {
			Enumeration<?> parameterNames = request.getParameterNames();
			while (parameterNames.hasMoreElements()) {
				String name = (String) parameterNames.nextElement();
				if (ignoreProperties != null && Arrays.binarySearch(ignoreProperties, name) >= 0)
					continue;
				String[] value = request.getParameterValues(name);
				BeanUtils.setProperty(source, name, value);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static Map<String, Object> getParametersStartingWith(ServletRequest request, String prefix) {
		Validate.notNull(request, "Request must not be null");
		Map<String, Object> params = new HashMap<String, Object>();
		for (Enumeration<?> paramNames = request.getParameterNames(); paramNames.hasMoreElements();) {
			String paramName = (String) paramNames.nextElement();
			String[] values = request.getParameterValues(paramName);
			if (StringUtils.isNotBlank(prefix)) {
				int index = StringUtils.lastIndexOf(paramName, prefix);
				if (index > -1) {
					paramName = StringUtils.substring(paramName, index + 1);
				}
			}
			if (values.length > 1) {
				params.put(paramName, values);
			} else {
				params.put(paramName, values[0]);
			}
		}
		return params;
	}

	private static MultipartHttpServletRequest checkHttpServletRequest(HttpServletRequest request) {
		if (!(request instanceof MultipartHttpServletRequest)) {
			throw new BusinessException("不能将" + request.getClass() + "对象转换成"
					+ MultipartHttpServletRequest.class.getName());
		}
		return (MultipartHttpServletRequest) request;
	}

	public static MultipartFile getSingleFile(HttpServletRequest request, String filed) {
		MultipartHttpServletRequest req = WebUtils.checkHttpServletRequest(request);
		MultipartFile file = req.getFile(filed);
		return file;
	}

	public static MultipartFile getSingleFile(HttpServletRequest request) {
		MultipartHttpServletRequest req = WebUtils.checkHttpServletRequest(request);
		Map<String, MultipartFile> files = req.getFileMap();
		MultipartFile file = null;
		for (Map.Entry<String, MultipartFile> entry : files.entrySet()) {
			file = entry.getValue();
		}
		return file;
	}

	public static Map<String, MultipartFile> getMultipartFile(HttpServletRequest request) {
		MultipartHttpServletRequest req = WebUtils.checkHttpServletRequest(request);
		Map<String, MultipartFile> files = req.getFileMap();
		return files;
	}

	public static List<Long> toLongList(String[] ids) {
		List<Long> idList = new ArrayList<Long>();
		if (ids != null && ids.length > 0) {
			for (String id : ids) {
				if (StringUtils.isBlank(id)) {
					continue;
				}
				if (!StringUtils.contains(id, ",")) {
					idList.add(Long.valueOf(id));
					continue;
				}
				String[] subIds = StringUtils.split(id, ",");
				for (String subId : subIds) {
					idList.add(Long.valueOf(subId));
				}
			}
		}
		return idList;
	}

	public static Set<Long> toLongSet(String[] ids) {
		Set<Long> idSet = new HashSet<Long>();
		if (ids != null && ids.length > 0) {
			for (String id : ids) {
				if (StringUtils.isBlank(id)) {
					continue;
				}
				if (!StringUtils.contains(id, ",")) {
					idSet.add(Long.valueOf(id));
					continue;
				}
				String[] subIds = StringUtils.split(id, ",");
				for (String subId : subIds) {
					idSet.add(Long.valueOf(subId));
				}
			}
		}
		return idSet;
	}
	
	/**
	 * 上传文件，并返回文件路径map Title:
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param request
	 * @param managerUploadPath
	 * @param saveFilePath
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, Object>> getUploadFilePathMap(HttpServletRequest request, String paramName,
			String managerUploadPath, String saveFilePath) throws Exception {
		List<Map<String, Object>> mapLists = Lists.newArrayList();
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
			List<MultipartFile> files = null;
			if(!StringUtils.isBlank(paramName)) {
				files = req.getFiles(paramName);
			} else {
				files = Lists.newArrayList(req.getFileMap().values());
			}
			
			for (MultipartFile file : files) {
				Map<String, Object> entity = Maps.newHashMap();
				FileUtils.uploadSingleFile(file, managerUploadPath, saveFilePath, entity,
						new UploadFileCallback<Map<String, Object>>() {
							@Override
							public void setValue(Map<String, Object> entity, String filePath) {
								entity.put("filePath", filePath);
							}
						});
				entity.put("fileSize", file.getSize() / 1024);
				mapLists.add(entity);
			}
		}
		return mapLists;
	}

	public static String getRemoteAddrIp(HttpServletRequest request) {
		String ipFromNginx = request.getHeader("X-Real-IP");
		return StringUtils.isBlank(ipFromNginx) ? request.getRemoteAddr() : ipFromNginx;
	}

}
