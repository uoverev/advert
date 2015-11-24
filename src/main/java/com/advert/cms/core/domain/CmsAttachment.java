package com.advert.cms.core.domain;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.better.framework.common.domain.AbstractEntity;

/**
 * 附件列表 Entity
 *
 * Date: 2014-12-02 13:39:35
 *
 * @author Feinno Code Generator
 */
@Entity
@Table(name = "cms_attachment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CmsAttachment extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8455744871501663057L;

	/** 主键 */
	private Long id;
	
	/** 用户ID */
	private Long userId;
	
	/** 源文件名 */
	private String sourceName;
	
	/** 文件名 */
	private String fileName;
	
	/** 附件类型 */
	private String fileType;
	
	/** 磁盘地址 */
	private String filePath;
	
	/** url地址，不带域名 */
	private String attachUrl;
	
	/** 关联模块（自定义） */
	private String relationKey;
	
	/** 文件大小byte */
	private Long fileSize;
	
	/** 创建时间 */
	private Date createTime;

    /** 系统标识 */
    private String sysKey;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSourceName(){
		return this.sourceName;
	}
	
	public void setSourceName(String sourceName){
		this.sourceName = sourceName;
	}
	public String getFileName(){
		return this.fileName;
	}
	
	public void setFileName(String fileName){
		this.fileName = fileName;
	}
	public String getFileType(){
		return this.fileType;
	}
	
	public void setFileType(String fileType){
		this.fileType = fileType;
	}
	public String getFilePath(){
		return this.filePath;
	}
	
	public void setFilePath(String filePath){
		this.filePath = filePath;
	}
	public String getAttachUrl(){
		return this.attachUrl;
	}
	
	public void setAttachUrl(String attachUrl){
		this.attachUrl = attachUrl;
	}
	public String getRelationKey(){
		return this.relationKey;
	}
	
	public void setRelationKey(String relationKey){
		this.relationKey = relationKey;
	}
	public Long getFileSize(){
		return this.fileSize;
	}
	
	public void setFileSize(Long fileSize){
		this.fileSize = fileSize;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

    public String getSysKey() {
        return sysKey;
    }

    public void setSysKey(String sysKey) {
        this.sysKey = sysKey;
    }

    @Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
