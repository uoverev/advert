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
 * agent_role Entity
 *
 * Date: 2015-12-02 13:10:25
 *
 * @author Acooly Code Generator
 */
@Entity
@Table(name = "agent_role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AgentRole extends AbstractEntity {
	/** id */
	private Long id;
	
	/** 角色名称 */
	private String name;
	
	
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

	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
