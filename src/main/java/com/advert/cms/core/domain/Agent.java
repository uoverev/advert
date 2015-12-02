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
 * agent Entity
 *
 * Date: 2015-12-02 13:10:17
 *
 * @author Acooly Code Generator
 */
@Entity
@Table(name = "agent")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Agent extends AbstractEntity {
	/** id */
	private Long id;
	
	/** 机构名称 */
	private String name;
	
	/** 机构编码 */
	private String code;
	
	/** 开机时间 22:00 */
	private String powerOn;
	
	/** 关机时间23:00 */
	private String powerOff;
	
	/** 更新时间 */
	private String updateTime;
	
	
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

	public String getCode(){
		return this.code;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getPowerOn(){
		return this.powerOn;
	}

	public void setPowerOn(String powerOn){
		this.powerOn = powerOn;
	}

	public String getPowerOff(){
		return this.powerOff;
	}

	public void setPowerOff(String powerOff){
		this.powerOff = powerOff;
	}

	public String getUpdateTime(){
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime){
		this.updateTime = updateTime;
	}

	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
