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
 * advert_group_agent Entity
 *
 * Date: 2015-12-02 13:09:55
 *
 * @author Acooly Code Generator
 */
@Entity
@Table(name = "advert_group_agent")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AdvertGroupAgent extends AbstractEntity {
	/** id */
	private Long id;
	
	/** 分组id */
	private Long groupId;
	
	/** 机构id */
	private Long agentId;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public Long getGroupId(){
		return this.groupId;
	}

	public void setGroupId(Long groupId){
		this.groupId = groupId;
	}

	public Long getAgentId(){
		return this.agentId;
	}

	public void setAgentId(Long agentId){
		this.agentId = agentId;
	}

	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
