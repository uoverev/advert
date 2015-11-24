package com.advert.cms.util.fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.advert.cms.util.StringUtil;
import com.better.framework.common.exception.BusinessException;

/**
 * 文件操作助手类
 * 
 * @author bjb-336
 * 
 */
public class FileUtils {
	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

	private static final String LEFT_SLASH = "/";

	/**
	 * 检查目录是否存在，不存在创建目录
	 * 
	 * @param filePath
	 * @return
	 */
	public static File validDirectory(String filePath) {
		File pFile = new File(filePath);
		if (!pFile.exists()) {
			pFile.mkdirs();
		}
		return pFile;
	}

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

	public static class ImageSize {
		public final int width;
		public final int height;

		public ImageSize(int width, int height) {
			this.width = width;
			this.height = height;
		}

		@Override
		public String toString() {
			return width + "x" + height;
		}
	}

	/**
	 * 按给定规格生成对应的图片
	 * 
	 * @param originalFilePath
	 *            - 文件输出保存路径
	 * @param imageSizes
	 *            - 图片规格列表
	 * @throws Exception
	 */
	public static void writeImageFile(String originalFilePath, ImageSize... imageSizes) throws Exception {
		FileUtils.writeImageFile(new File(originalFilePath), imageSizes);
	}

	public static void writeImageFile(File originalFile, ImageSize... imageSizes) throws Exception {
		for (int i = 0; imageSizes != null && i < imageSizes.length; i++) {
			ImageSize imageSize = imageSizes[i];
			Builder<File> builder = Thumbnails.of(originalFile);
			String path = appendSuffix(originalFile.getAbsolutePath(), "_" + imageSize.toString());
			builder.size(imageSize.width, imageSize.height).keepAspectRatio(false).toFile(path);
		}
	}

	public static void writeFile(String originalFilePath, InputStream is) throws Exception {
		FileUtils.writeFile(new File(originalFilePath), is);
	}

	public static void writeFile(File file, InputStream is) throws Exception {
		FileUtils.createNewFile(file);
		org.apache.commons.io.FileUtils.copyInputStreamToFile(is, file);
	}

	/**
	 * 保存图片，并按给定规格生成对应的图片
	 * 
	 * @param originalFilePath
	 *            - 文件输出保存路径
	 * @param stream
	 *            - 输入流
	 * @param imageSizes
	 *            - 图片规格列表
	 * @throws Exception
	 */
	public static void writeImageFile(String originalFilePath, InputStream stream, ImageSize... imageSizes)
			throws Exception {
		File originalFile = new File(originalFilePath);
		FileUtils.writeFile(originalFile, stream);
		FileUtils.writeImageFile(originalFile, imageSizes);
	}

	/**
	 * 保存图片，并按给定规格生成对应的图片
	 * 
	 * @param outFileDirPath
	 *            - 文件输出保存目录
	 * @param uuid
	 *            - 文件重命名名称
	 * @param extensions
	 *            - 文件后缀名
	 * @param stream
	 *            - 输入流
	 * @param imageSizes
	 *            - 图片规格列表
	 * @throws Exception
	 */
	public static void writeImageFile(String outFileDir, String uuid, String extensions, InputStream stream,
			ImageSize... imageSizes) throws Exception {
		File dir = new File(outFileDir);
		if (!dir.isDirectory()) {
			throw new IllegalArgumentException(outFileDir + "不是目录!");
		}
		String originalFilePath = FileUtils.pathHandle(outFileDir, uuid + "." + extensions);
		FileUtils.writeImageFile(originalFilePath, stream, imageSizes);
	}

	public static void main(String[] args) throws FileNotFoundException, Exception {
		String dir = "C:/Users/Administrator/Desktop/images/360/";
		File files = new File(dir);
		for (File file : files.listFiles()) {
			String uuid = FileUtils.getUUID().replace("-", "");
			FileInputStream is = new FileInputStream(file);
			writeImageFile("C:/Users/Administrator/Desktop/images/aaa/", uuid, "png", is);
		}
	}

	public static String appendYearMonth(String subPath) {
		if (!subPath.endsWith(File.separator)) {
			subPath = String.format("%s%s", subPath, File.separator);
		}
		Calendar cal = Calendar.getInstance();
		// 按年月划分文件夹
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1; // +1才是当前月份
		String newPath = String.format("%s%s%s%s%s", subPath, year, File.separator, month, File.separator);
		return newPath;
	}

	/**
	 * 文件路径拼接
	 * 
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
	 * 在图片路径中添加图片规格<br>
	 * 如：abc12351_23x32.jpg
	 * 
	 * @param fileName
	 *            - 图片名称或图片路径
	 * @param suffix
	 *            - 图片规格字符串
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
	 * 生成UUID
	 * 
	 * @return
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replaceAll("-", "");
		return uuid;
	}

	/**
	 * 获取文件后缀名称
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getExtensions(String fileName) {
		return StringUtils.substringAfterLast(fileName, ".");
	}

	public static void downloadFile(InputStream stream, String excelName, HttpServletResponse response)
			throws BusinessException {
		byte[] buffer = new byte[1024 * 2];
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			response.reset();
			// 下载
			response.setContentType("application/x-download;charset=UTF-8");
			String fileName = new String(excelName.getBytes("gb2312"), "iso8859-1");
			response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName);
			// 输出流
			int len;
			while ((len = stream.read(buffer)) > 0) {
				os.write(buffer, 0, len);
			}
			stream.close();
			os.flush();
		} catch (Exception e) {
			throw new BusinessException("exportExcel method error:" + e.getMessage());
		} finally {
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					throw new BusinessException("exportExcel method error:" + e.getMessage());
				}
			}

		}
	}

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

	public static void uploadSingleFile(MultipartFile multipartFile, String managerUploadPath, String relativePath,
			StringBuilder builder) throws Exception {
		uploadSingleFile(multipartFile, managerUploadPath, relativePath, builder,
				new UploadFileCallback<StringBuilder>() {
					@Override
					public void setValue(StringBuilder entity, String filePath) {
						entity.append(filePath);
					}
				});
	}

	/**
	 * Title:
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param multipartFile
	 * @param managerUploadPath
	 * @param relativePath
	 * @param entity
	 * @param callback
	 * @throws Exception
	 */
	public static <T> void uploadSingleFile(MultipartFile multipartFile, String managerUploadPath, String relativePath,
			T entity, UploadFileCallback<T> callback) throws Exception {
		if (multipartFile == null || multipartFile.isEmpty()) {
			return;
		}
		String fileRename = FileUtils.getUuidFileName(multipartFile.getOriginalFilename());
		String[] absolutePath = new String[] { managerUploadPath, relativePath, fileRename };
		String outDirPath = StringUtil.wapperValue(absolutePath, "/");
		FileUtils.writeFile(outDirPath, multipartFile.getInputStream());
		String imagePath = StringUtil.wapperValue(new String[] { relativePath, fileRename }, "/");
		if (callback != null && entity != null) {
			callback.setValue(entity, imagePath);
		}
	}
	
	/**
	 * <p>
	 * Description:检查上传文件流
	 * <li>当文件流文件为null或为空时，不做检查
	 * <li>当fileType==null或空字符串时，不做检查
	 * </p>
	 * 
	 * @param file
	 *            - 上传文件信息
	 * @param limitMaxSize
	 *            - 上传文件最大限制（字节）
	 * @param fileType
	 *            - 上传文件类型数组(例如：["image/jpeg","image/jpg","image/png"])
	 * @throws Exception
	 */
	public static void checkMultipartFile(MultipartFile file, long limitMaxSize, String[] fileType) throws Exception {
		if (file != null && !file.isEmpty()) {
			if (file.getSize() > limitMaxSize) {
				throw new IOException("上传失败：文件大小不能超过" + fileSizeToUnit(limitMaxSize) + "！");
			}
			if ((fileType) != null && fileType.length > 0) {
				List<String> types = Arrays.asList(fileType);
				if (!types.contains(file.getContentType())) {
					throw new IOException("上传失败：文件类型与指定类型不匹配！");
				}
			}
		}
	}
	
	/**
	 * <p>
	 * Description: 将文件大小转换成文件大小描述字符串
	 * </p>
	 * 
	 * @param size
	 *            -
	 * @return
	 */
	public static String fileSizeToUnit(long size) {
		for (int i = 0; i < sizeUnits.length; i++) {
			long l = (size > 1024) ? size >>> (i * 10) : size;
			if (l < 1024) {
				return (l + sizeUnits[i]);
			}
		}
		return null;
	}
	
	static final String[] sizeUnits = new String[] { "B", "KB", "MB", "GB", "TB", "PB" };

	/**
	 * 批量删除文件 Title:
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param filePaths
	 *            相对文件路径数组
	 * @param rootPath
	 *            根路径
	 */
	public static void deleteFiles(String rootPath, String[] filePaths) {
		for (int i = 0; i < filePaths.length; i++) {
			File file = new File(rootPath + LEFT_SLASH + filePaths[i]);
			if (file.exists()) {
				file.delete();
			}
		}
	}

	public interface UploadFileCallback<T> {

		/**
		 * <p>
		 * Description:文件上传后回调
		 * </p>
		 * 
		 * @param entity
		 *            - 设置对象
		 * @param filePath
		 *            - 文件上传后保存的相对路径
		 */
		public void setValue(T entity, String filePath);
	}

}
