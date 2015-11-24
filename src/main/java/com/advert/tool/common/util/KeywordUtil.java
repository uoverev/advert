package com.advert.tool.common.util;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.advert.cms.core.utils.ac.AhoCorasick;
import com.advert.tool.common.util.ac.SearchResult;

/**
 * com.feinno.eord.core.utils.
 * User: wangyx
 * Date: 14-12-10
 * Time: 上午11:22
 *
 * 关键词过滤类，默认将关键词替换为*号
 * 注：使用关键词替换方法[replaceKeyword(string)]前必须先设置关键词列表[setKeyWord(list<Keyword>)]
 */
public class KeywordUtil {

    private static AhoCorasick AC;

    /**
     * 设置关键词列表
     * @param keywordList
     */
    public synchronized static void setKeyWord(List<Keyword> keywordList) {
        AhoCorasick corasick = new AhoCorasick();
        for(Keyword keyword : keywordList){
            corasick.add(keyword.getWord().getBytes(), keyword);
        }
        corasick.prepare();
        AC = corasick;
    }

    /**
     * 替换关键词，默认替换为*号
     * @param content
     * @return
     */
    public static String replaceKeyword(String content){
        if(AC==null){
            throw new RuntimeException("Initialization is not completed");
        }
        if(StringUtils.isBlank(content)){
            return content;
        }
        String filterContent = content;
        //替换处理
        Iterator iterator = AC.search(content.getBytes());
        while(iterator.hasNext()){
            SearchResult result = (SearchResult) iterator.next();
            for(Object o : result.getOutputs()){
                if(o instanceof Keyword){
                    Keyword keyword = (Keyword) o;
                    String word = keyword.getWord();
                    String replace = keyword.getReplace();
                    if(StringUtils.isBlank(replace)) {
                        replace="*";
                        for(int j=1; j<word.length();j++){
                            replace+="*";
                        }
                    }
                    filterContent = filterContent.replace(word, replace);
                }
            }
        }

        return filterContent;
    }

    public static class Keyword{

        private String word;

        private String replace;

        public Keyword(String word, String replace) {
            this.word = word;
            this.replace = replace;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public String getReplace() {
            return replace;
        }

        public void setReplace(String replace) {
            this.replace = replace;
        }
    }
}
