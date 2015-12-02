package com.advert.cms.core.domain;


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
 * advert Entity
 *
 * Date: 2015-12-02 13:09:14
 *
 * @author Acooly Code Generator
 */
@Entity
@Table(name = "advert")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Advert extends AbstractEntity {
	/** id */
	private Long id;
	
	/** 类型 */
	private Integer type;
	
	/** 路径http */
	private String url;
	
	/** 名称 */
	private String name;
	
	/** 状态 */
	private Integer status;
	
	/** 备注 */
	private String memo;
	
	/** 视频时长 */
	private Long duration;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public Integer getType(){
		return this.type;
	}

	public void setType(Integer type){
		this.type = type;
	}

	public String getUrl(){
		return this.url;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name = name;
	}

	public Integer getStatus(){
		return this.status;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public String getMemo(){
		return this.memo;
	}

	public void setMemo(String memo){
		this.memo = memo;
	}

	public Long getDuration(){
		return this.duration;
	}

	public void setDuration(Long duration){
		this.duration = duration;
	}

	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
