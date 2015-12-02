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
 * advert_log Entity
 *
 * Date: 2015-12-02 13:10:11
 *
 * @author Acooly Code Generator
 */
@Entity
@Table(name = "advert_log")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AdvertLog extends AbstractEntity {
	/** id */
	private Long id;
	
	/** 广告id */
	private Long advertId;
	
	/** 客户端id */
	private Long clientId;
	
	/** 分组id */
	private Long groupId;
	
	/** 播放时间 */
	private Date playTime;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public Long getAdvertId(){
		return this.advertId;
	}

	public void setAdvertId(Long advertId){
		this.advertId = advertId;
	}

	public Long getClientId(){
		return this.clientId;
	}

	public void setClientId(Long clientId){
		this.clientId = clientId;
	}

	public Long getGroupId(){
		return this.groupId;
	}

	public void setGroupId(Long groupId){
		this.groupId = groupId;
	}

	public Date getPlayTime(){
		return this.playTime;
	}

	public void setPlayTime(Date playTime){
		this.playTime = playTime;
	}

	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
