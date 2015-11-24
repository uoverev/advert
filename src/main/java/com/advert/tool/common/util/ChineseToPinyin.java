/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
package com.advert.tool.common.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author fuxiaofeng on 2015年6月12日
 */
public class ChineseToPinyin {

	static Logger logger = LoggerFactory.getLogger(ChineseToPinyin.class);
	private static HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
	static {
		outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		outputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
	}
	public static String chineseToPinyin(String str){
		StringBuilder pinyinBuilder = new StringBuilder();
		for(int i=0;i<str.length();i++){
			char ch = str.charAt(i);
			pinyinBuilder.append(coverOneStr(ch,outputFormat));
		}
		return pinyinBuilder.toString();
	};
	
	private static String coverOneStr(char ch,HanyuPinyinOutputFormat outputFormat){
		String[] pinyinArray;
		try {
			if(StringUtil.isChinese(ch)){
				pinyinArray = PinyinHelper.toHanyuPinyinStringArray(ch, outputFormat);
			}else{
				pinyinArray = new String[]{String.valueOf(ch)};
			}
			return pinyinArray[0];
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			System.out.println("<<<<<<<<<--------------char>>>>>pinyinString异常------------>>>>>>>>>>");
			logger.info("<<<<<<<<<--------------char>>>>>pinyinString异常------------>>>>>>>>>>",e);
		}
		return null;
	}
	
	public static void main(String[] args) {
		String  str = "!ss重庆女人";
		System.out.println(chineseToPinyin(str));
	}
}
