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
 * user_agent_role Entity
 *
 * Date: 2015-12-02 13:10:42
 *
 * @author Acooly Code Generator
 */
@Entity
@Table(name = "user_agent_role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserAgentRole extends AbstractEntity {
	/** id */
	private Long id;
	
	/** 用户id */
	private Long userId;
	
	/** 机构角色id,对应agent_role表id */
	private Long agentRoleId;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public Long getUserId(){
		return this.userId;
	}

	public void setUserId(Long userId){
		this.userId = userId;
	}

	public Long getAgentRoleId(){
		return this.agentRoleId;
	}

	public void setAgentRoleId(Long agentRoleId){
		this.agentRoleId = agentRoleId;
	}

	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
