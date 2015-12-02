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
 * advert_group Entity
 *
 * Date: 2015-12-02 13:09:49
 *
 * @author Acooly Code Generator
 */
@Entity
@Table(name = "advert_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AdvertGroup extends AbstractEntity {
	/** id */
	private Long id;
	
	/** 分组名称 */
	private String name;
	
	/** 类型 */
	private Integer type;
	
	/** 图片播放间隔时间,秒 */
	private Long imgInterval;
	
	/** 播放开始时间22:00 */
	private String startTime;
	
	/** 播放结束时间23:00 */
	private String entTime;
	
	/** 循环次数 */
	private Integer cycle;
	
	/** 最后更新时间 */
	private Date updateTime;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name = name;
	}

	public Integer getType(){
		return this.type;
	}

	public void setType(Integer type){
		this.type = type;
	}

	public Long getImgInterval(){
		return this.imgInterval;
	}

	public void setImgInterval(Long imgInterval){
		this.imgInterval = imgInterval;
	}

	public String getStartTime(){
		return this.startTime;
	}

	public void setStartTime(String startTime){
		this.startTime = startTime;
	}

	public String getEntTime(){
		return this.entTime;
	}

	public void setEntTime(String entTime){
		this.entTime = entTime;
	}

	public Integer getCycle(){
		return this.cycle;
	}

	public void setCycle(Integer cycle){
		this.cycle = cycle;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	
}
