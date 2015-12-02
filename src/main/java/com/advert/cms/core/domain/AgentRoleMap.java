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
 * agent_role_map Entity
 *
 * Date: 2015-12-02 13:10:31
 *
 * @author Acooly Code Generator
 */
@Entity
@Table(name = "agent_role_map")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AgentRoleMap extends AbstractEntity {
	/** id */
	private Long id;
	
	/** 角色id */
	private Long roleId;
	
	/** 机构ID */
	private Long orgId;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public Long getRoleId(){
		return this.roleId;
	}

	public void setRoleId(Long roleId){
		this.roleId = roleId;
	}

	public Long getOrgId(){
		return this.orgId;
	}

	public void setOrgId(Long orgId){
		this.orgId = orgId;
	}

	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
