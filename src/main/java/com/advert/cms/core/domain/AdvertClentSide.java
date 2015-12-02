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
 * advert_clent_side Entity
 *
 * Date: 2015-12-02 13:09:41
 *
 * @author Acooly Code Generator
 */
@Entity
@Table(name = "advert_clent_side")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AdvertClentSide extends AbstractEntity {
	/** id */
	private Long id;
	
	/** 客户端名称 */
	private String name;
	
	/** 对应agent表id */
	private Long agentId;
	
	/** 状态 */
	private Integer status;
	
	/** 客户端唯一标识码 */
	private String code;
	
	
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

	public Long getAgentId(){
		return this.agentId;
	}

	public void setAgentId(Long agentId){
		this.agentId = agentId;
	}

	public Integer getStatus(){
		return this.status;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public String getCode(){
		return this.code;
	}

	public void setCode(String code){
		this.code = code;
	}

	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
