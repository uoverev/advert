package com.advert.tool.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;


/**
 * 取Properties文件类
 * 
 * <p>Description:</p>
 * Copyright (c) feinno 2013
 * @author lihanpei on 2014-3-31
 */
public class PropertiesUtils {
	
	private static HashMap<String, PropertiesUtils> configuration;

	// 中文说明语句对象
	private HashMap<String, String> message;
	

	/**
	 * 函数名称：getMessage 函数功能描述： 返回:message
	 * 
	 * @return message
	 */
	public HashMap<String, String> getMessage() {
		return message;
	}

	/**
	 * 构造函数:
	 * 
	 * @param configFile
	 *            文件路径
	 */
	private PropertiesUtils(String configFile) {
		init(configFile);
	}
	
	/**
	public static String getMasterMap(String key) {
		if (null == masterMap) {
			masterMap = PropertiesUtils.getInstance(Constants.SYS_CONFIG_PATH).getMessage();
		}
		return masterMap.get(key);
	}
	*/

	/**
	 * 函数名称: getInstance 函数功能描述: 获取实例信息
	 * 
	 * @param configFile
	 * @return Configuration 对象
	 */
	public static synchronized PropertiesUtils getInstance(String configFile) {
		PropertiesUtils conf = null;
		if (configuration == null) {
			configuration = new HashMap<String, PropertiesUtils>();
			conf = new PropertiesUtils(configFile);
			configuration.put(configFile, conf);
		} else {
			PropertiesUtils configrue = configuration.get(configFile);
			if (configrue == null) {
				conf = new PropertiesUtils(configFile);
				configuration.put(configFile, conf);
			} else {
				conf = configrue;
			}
		}
		return conf;
	}

	/**
	 * 函数名称: init 函数功能描述:初始化，读取配置文件
	 * 
	 * @param configFile
	 *            配置文件路径
	 */
	private synchronized void init(String configFile) {
		InputStream in = null;
		Properties propertie = new Properties();

		try {
			InputStream inputFile = new FileInputStream(configFile);
			propertie.load(inputFile);
		} catch (Exception e1) {
			in = this.getClass().getResourceAsStream(configFile);
			try {
				propertie.load(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		setPropertiseValue(propertie);
	}

	public String get(String key) {
		return this.message.get(key);
	}

	/**
	 * 函数名称: setPropertiseValue 函数功能描述: 设置属性文件的所有信息
	 */
	private void setPropertiseValue(Properties propertie) {
		this.message = new HashMap<String, String>();
		Set<Object> keyValue = propertie.keySet();
		for (Iterator<Object> it = keyValue.iterator(); it.hasNext();) {
			String key = (String) it.next();
			String value;
			try {
				value = new String(getValue(key, propertie).getBytes("ISO-8859-1"), "UTF-8");
				// value = getValue(key, propertie);
			} catch (Exception e) {
				value = "";
				e.printStackTrace();
			}
			this.message.put(key, value);
		}
	}

	/**
	 * 函数名称: getValue 函数功能描述: 根据属性文件的键，获取值
	 * 
	 * @param key
	 *            键
	 * @return 对应的值
	 */
	private String getValue(String key, Properties propertie) {
		String value = null;
		if (propertie.containsKey(key)) {
			value = propertie.getProperty(key);
		} else {
			value = "";
		}
		return value;
	}

	public static Properties getProperties(String filePath,String charset) throws Exception {
        if (StringUtil.isEmpty(filePath))
            return null;
        
        Reader in = null;
        Properties result = null;
        try {
            result = new Properties();
            if(StringUtil.isEmpty(charset))
                charset = "UTF-8";
            in = new InputStreamReader(new FileInputStream(new File(filePath)),charset);
            result.load(in);
        }
        finally {
            CloseHelper.close(in);
        }
        
        return result;
    }
	
	public static Properties getProperties(String fileName) throws Exception {
		if (StringUtil.isEmpty(fileName))
			return null;
		
		Reader in = null;
		Properties result = null;
		try {
			result = new Properties();
			in = new InputStreamReader(PropertiesUtils.class.getResourceAsStream(fileName) , "UTF-8");
			result.load(in);
		}
		finally {
			CloseHelper.close(in);
		}
		
		return result;
	}
}	
