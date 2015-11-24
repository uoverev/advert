/**
 * ZipUtil.java
 * User: junsansi
 * Date: 2015-4-14
 */
package com.advert.cms.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tools.zip.ZipEntry;

import com.better.framework.common.exception.BusinessException;
  
/**
 * @author junsansi
 * Date: 2015-4-14
 */
public class ZipUtil {
      
    /** 
     * 压缩文件列表到某ZIP文件 
     * @param zipFilename 要压缩到的ZIP文件 
     * @param paths 文件列表，多参数 
     * @throws Exception 
     */  
    public static void compress(String zipFilename, String... paths)  
            throws Exception {  
        compress(new FileOutputStream(zipFilename), paths);  
    }  
 
    
    /** 
     * 压缩文件列表到输出流 
     * @param os 要压缩到的流 
     * @param paths 文件列表，多参数 
     * @throws Exception 
     */  
    public static void compress(OutputStream os, String... paths)  
            throws Exception {  
        ZipOutputStream zos = new ZipOutputStream(os);  
        for (String path : paths) {  
            if (path.equals(""))  
                continue;  
            java.io.File file = new java.io.File(path);  
            if (file.exists()) {  
                if (file.isDirectory()) {  
                    zipDirectory(zos, file.getPath(), file.getName()  
                            + File.separator);  
                } else {  
                    zipFile(zos, file.getPath(), "");  
                }  
            }  
        }  
        zos.close();  
    }  
    
    private static void compress(ZipOutputStream zos, List<String> paths) throws Exception{
        for (int i=0; i<paths.size(); i++) {  
            java.io.File file = new java.io.File(paths.get(i));  
            if (file.exists()) {  
                if (file.isDirectory()) {  
                    zipDirectory(zos, file.getPath(), file.getName()  
                            + File.separator);  
                } else {  
                    zipFile(zos, file.getPath(), "");  
                } 
            }  
        } 
    }
    
    private static void zipDirectory(ZipOutputStream zos, String dirName,  
            String basePath) throws Exception {  
        File dir = new File(dirName);  
        if (dir.exists()) {  
            File files[] = dir.listFiles();  
            if (files.length > 0) {  
                for (File file : files) {  
                    if (file.isDirectory()) {  
                        zipDirectory(zos, file.getPath(), basePath  
                                + file.getName().substring(  
                                        file.getName().lastIndexOf(  
                                                File.separator) + 1)  
                                + File.separator);  
                    } else  
                        zipFile(zos, file.getPath(), basePath);  
                }  
            } else {  
                ZipEntry ze = new ZipEntry(basePath);  
                zos.putNextEntry(ze);  
            }  
        }  
    }  
  
    private static void zipFile(ZipOutputStream zos, String filename,  
            String basePath) throws Exception {  
        File file = new File(filename);  
        if (file.exists()) {  
            FileInputStream fis = new FileInputStream(filename);  
            ZipEntry ze = new ZipEntry(basePath + file.getName());  
            zos.putNextEntry(ze);  
            byte[] buffer = new byte[8192];  
            int count = 0;  
            while ((count = fis.read(buffer)) > 0) {  
                zos.write(buffer, 0, count);  
            }  
            fis.close();  
        }  
    }  
    
    
    

	private static <T> void doExportZip(HttpServletResponse response, String fileName,
			List<String> filepaths) {
		OutputStream output = null;
		try {
			StringBuilder builder = new StringBuilder();
			if (StringUtils.isNotBlank(fileName)) {
				builder.append(new String(fileName.getBytes("gb2312"), "iso8859-1"));
			} else {
				builder.append(System.nanoTime());
			}
			builder.append(".zip");

			output = response.getOutputStream();
			response.reset();
			response.setContentType("bin");

			response.setContentType("application/zip");
			response.setHeader("Content-Disposition", "attachment; filename=" + builder.toString());
			ZipOutputStream zos = new ZipOutputStream(output);
			compress(zos, filepaths);
			zos.close();
			output.flush();
		} catch (Exception e) {
			throw new BusinessException(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(output);
		}
	}
	
	public static <T> void doExportZipFile(HttpServletResponse response, String fileName,
			List<String> filepaths){
		doExportZip(response,fileName,filepaths);
	}
    
    
}  