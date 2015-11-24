package com.advert.tool.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class TemplateUtil {
	private static Log log = LogFactory.getLog(TemplateUtil.class);
	
	private static TemplateUtil instance;

	private boolean isInit = false;
	private String templateDirPath;
	private Configuration conf = new Configuration();
	private String charset = "UTF-8";
	
	/**
	 * 获取模板实例
	 * @return
	 * @auther <a href="mailto:lihanpei@feinno.com">Li Hanpei</a>
	 * 2011-9-21 下午2:50:46
	 */
	public static synchronized TemplateUtil getInstance(){
		if(instance == null)
			instance = new TemplateUtil("UTF-8");
		
		return instance;
	}
	
	/**
	 *　获取模板实例
	 * @param templateDirPath 模板所在目录
	 * @return
	 * @auther <a href="mailto:lihanpei@feinno.com">Li Hanpei</a>
	 * 2011-9-21 下午2:50:43
	 */
	public static synchronized TemplateUtil getInstance(String templateDirPath){
		return getInstance(templateDirPath, "UTF-8");
	}
	

	/**
	 *　获取模板实例
	 * @param templateDirPath 模板所在目录
	 * @return
	 * @auther <a href="mailto:lihanpei@feinno.com">Li Hanpei</a>
	 * 2011-9-21 下午2:50:43
	 */
	public static synchronized TemplateUtil getInstance(String templateDirPath,String charset){
		if(instance == null){
			instance = new TemplateUtil(charset);
			instance.templateDirPath = templateDirPath;
		}
		
		instance.initial();
		
		return instance;
	}
	
	public boolean initial(){
		if(isInit)
			return isInit;
		
		boolean result = false;
		
		File file = new File(templateDirPath);
		
		try {
			conf.setDirectoryForTemplateLoading(file);
			conf.setDefaultEncoding(charset);
			//conf.setEncoding(Locale.CHINESE, charset);
			conf.setObjectWrapper(new DefaultObjectWrapper());
			isInit = result = true;
		}
		catch (IOException e) {
			if(log.isErrorEnabled())
				log.error("加载模板异常",e);
		}
		
		return result;
	}
	
	/**
	 * 生成模板内容
	 * @param templateName
	 * @param data
	 * @return
	 * @auther <a href="mailto:lihanpei@feinno.com">Li Hanpei</a>
	 * 2011-6-16 下午05:25:31
	 */
	public String buildContent(String templateName,Map<String,Object> data){
		

		if(!isInit)
			throw new RuntimeException("未设置模板目录或未初始化模板环境。");
		
		String result = null;
		if(templateName == null || templateName.trim().equals(""))
			return result;
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Writer out = null;
		try {
			out = new OutputStreamWriter(baos);
			
			Template template = conf.getTemplate(templateName,this.charset);
			template.process(data,out);
			out.flush();
			result = baos.toString();
		}
		catch (Exception e) {
			if(log.isErrorEnabled())
				log.error("生成模板异常",e);
		}
		finally{
			
			try {
				if (out != null) {
					out.close();
					out = null;
				}
			}
			catch (Exception ex) {
				if (log.isErrorEnabled())
					log.error("关闭Writer异常", ex);
			}
			try {
				if (baos != null) {
					baos.close();
					baos = null;
				}
			}
			catch (Exception ex) {
				if (log.isErrorEnabled())
					log.error("关闭输入流异常", ex);
			}
		}
		
		return result;
	}
	
	/**
	 * 
	 * 设置字符集，默认为UTF-8
	 */
	public void setCharset(String charSet) {
		this.charset = charSet;
	}
	
	
	private TemplateUtil(String charSet){
		this.charset = charSet;
	}
	private TemplateUtil( ){
		
	}
	
	public String getTemplateDirPath() {
		return templateDirPath;
	}

	public void setTemplateDirPath(String templateDirPath) {
		this.templateDirPath = templateDirPath;
		//初始化环境
		initial();
	}
}
