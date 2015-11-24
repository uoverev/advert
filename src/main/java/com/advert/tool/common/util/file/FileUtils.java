package com.advert.tool.common.util.file;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.better.framework.common.exception.BusinessException;

/**
 * 
 * Title:文件操作工具类
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author tangqiang on 2014年12月10日
 */
public class FileUtils {
	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
//	private static final String[] ALLOW_IMAGE_TYPE= new String[]{"jpg","jpeg","png"};
	private static final String LEFT_SLASH = "/";

	/**
	 * <p>Description:检查目录是否存在，不存在可以选择创建目录</p>
	 * @param filePath 目录路径
	 * @param isCreate 不存在时是否需要创建该目录
	 * @return 该目录File
	 */
	public static File validDirectory(String filePath,boolean isCreate) {
		File pFile = new File(filePath);
		if (!pFile.exists()) {
			if(isCreate){
				pFile.mkdirs();
			}
		}
		return pFile;
	}

	/**
	 * <p>Description:根据文件路径创建一个新文件</p>
	 * @param file 要创建的文件
	 */
	public static void createNewFile(File file) {
		File pFile = file.getParentFile();
		if (!pFile.exists()) {
			pFile.mkdirs();
		}
		try {
			file.createNewFile();
		} catch (Exception e) {
			logger.error("创建文件出错", e);
		}
	}

	/**
	 * <p>Description:根据InputStream保存文件</p>
	 * @param originalFilePath 文件路径
	 * @param is InputStream
	 * @throws Exception
	 */
	public static void writeFile(String originalFilePath, InputStream is) throws Exception {
		writeFile(new File(originalFilePath), is);
	}

	/**
	 * <p>Description:根据InputStream保存文件</p>
	 * @param file 要保存的文件
	 * @param is InputStream
	 * @throws Exception
	 */
	public static void writeFile(File file, InputStream is) throws Exception {
		createNewFile(file);
		org.apache.commons.io.FileUtils.copyInputStreamToFile(is, file);
	}

	/**
	 * <p>Description:文件路径拼接,/aa/+/bb/ = /aa/bb/</p>
	 * @param basePath
	 * @param subPath
	 * @return
	 */
	public static String pathHandle(String basePath, String subPath) {
		if (StringUtils.isBlank(basePath)) {
			return null;
		}
		if (!subPath.startsWith(LEFT_SLASH)) {
			if (!basePath.endsWith(LEFT_SLASH)) {
				basePath = String.format("%s%s", basePath, LEFT_SLASH);
			}
		} else {
			if (basePath.endsWith(LEFT_SLASH)) {
				basePath = basePath.substring(0, basePath.length() - 1);
			}
		}
		return String.format("%s%s", basePath, subPath);
	}

	/**
	 * <p>Description:在文件名后面拼接指定字符串，如果文件名为带有后缀的形式，如1.jpg，则将字符串拼接到“.”前面.如：1.jpg + _80*80 = 1_80*80.jpg</p>
	 * <p>如：abc12351_23x32.jpg</p>
	 * @param fileName 文件名称
	 * @param suffix 需要拼接的字符串
	 * @return
	 */
	public static String appendSuffix(String fileName, String suffix) {
		StringBuilder newFileName = new StringBuilder();
		int indexOfDot = fileName.lastIndexOf('.');
		if (indexOfDot != -1) {
			newFileName.append(fileName.substring(0, indexOfDot));
			newFileName.append(suffix);
			newFileName.append(fileName.substring(indexOfDot));
		} else {
			newFileName.append(fileName);
			newFileName.append(suffix);
		}
		return newFileName.toString();
	}

	/**
	 * <p>Description:生成UUID</p>
	 * @return
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replaceAll("-", "");
		return uuid;
	}

	/**
	 * <p>Description:获取文件后缀名称</p>
	 * @param fileName
	 * @return
	 */
	public static String getExtensions(String fileName) {
		return StringUtils.substringAfterLast(fileName, ".");
	}

	/**
	 * <p>Description:根据文件名生成uuidName</p>
	 * @param fileName
	 * @return
	 * @author tangqiang on 2015年4月9日
	 */
	public static String getUuidFileName(String fileName) {
		if (StringUtils.isBlank(fileName)) {
			throw new NullPointerException("文件名不能为空！");
		}
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replaceAll("-", "");
		String suffix = StringUtils.substringAfterLast(fileName, ".");
		suffix = uuid + "." + suffix;
		return suffix;
	}
	
	/**
	 * <p>Description:根据输入流得到md5值</p>
	 * @param in InputStream
	 * @return
	 */
	public static String getFileMD5(InputStream in) {  
        MessageDigest digest = null;  
        //FileInputStream in = null;  
        byte buffer[] = new byte[1024];  
        int len;  
        try {  
            digest = MessageDigest.getInstance("MD5");  
            //in = new FileInputStream(file);  
            while ((len = in.read(buffer, 0, 1024)) != -1) {  
                digest.update(buffer, 0, len);  
            }  
            //in.close();  
        } catch (Exception e) {  
            return null;  
        }  
        BigInteger bigInt = new BigInteger(1, digest.digest());  
        return bigInt.toString(16);  
    }
	
	@Deprecated
	public static void downloadFile(InputStream stream, String fileName, HttpServletResponse response)
			throws BusinessException {
		byte[] buffer = new byte[1024 * 32];
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			response.reset();
			// 下载
			response.setContentType("application/x-download;charset=UTF-8");
			String nFileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
			response.addHeader("Content-Disposition", "attachment; filename=\"" + nFileName);
			// 输出流
			int len;
			while ((len = stream.read(buffer)) > 0) {
				os.write(buffer, 0, len);
			}
			os.flush();
		} catch (Exception e) {
			throw new BusinessException("exportExcel method error:" + e.getMessage());
		} finally {
			if (stream != null)
				IOUtils.closeQuietly(stream);
			if (null != os) {
				IOUtils.closeQuietly(os);
			}

		}
	}
}
