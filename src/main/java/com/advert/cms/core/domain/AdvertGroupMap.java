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
 * advert_group_map Entity
 *
 * Date: 2015-12-02 13:10:04
 *
 * @author Acooly Code Generator
 */
@Entity
@Table(name = "advert_group_map")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AdvertGroupMap extends AbstractEntity {
	/** id */
	private Long id;
	
	/** 对应advert_group表id */
	private Long groupId;
	
	/** 对应advert表id */
	private Long advertId;
	
	/** 排序号 */
	private Long sortNo;
	
	
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

	public Long getAdvertId(){
		return this.advertId;
	}

	public void setAdvertId(Long advertId){
		this.advertId = advertId;
	}

	public Long getSortNo(){
		return this.sortNo;
	}

	public void setSortNo(Long sortNo){
		this.sortNo = sortNo;
	}

	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
