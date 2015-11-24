package com.advert.cms.business.domain;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.GenerationType;

import com.better.framework.common.domain.AbstractEntity;

/**
 * test_user_info Entity
 *
 * Date: 2015-11-06 11:11:51
 *
 * @author Acooly Code Generator
 */
@Entity
@Table(name = "test_user_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TestUserInfo extends AbstractEntity {
	/** id */
	private Long id;
	
	/** 姓名 */
	private String name;
	
	/** 所在省 */
	private String province;
	
	/** 所在市 */
	private String city;
	
	/** 所在区 */
	private String distinct;
	
	/** 详细地址 */
	private String detail;
	
	/** 身份证号 */
	private String identity;
	
	/** 性别 */
	private String sex;
	
	/** 车辆品牌 */
	private String carB;
	
	/** 购车时间 */
	private Date buyT;
	
	
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

	public String getProvince(){
		return this.province;
	}

	public void setProvince(String province){
		this.province = province;
	}

	public String getCity(){
		return this.city;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getDistinct(){
		return this.distinct;
	}

	public void setDistinct(String distinct){
		this.distinct = distinct;
	}

	public String getDetail(){
		return this.detail;
	}

	public void setDetail(String detail){
		this.detail = detail;
	}

	public String getIdentity(){
		return this.identity;
	}

	public void setIdentity(String identity){
		this.identity = identity;
	}

	public String getSex(){
		return this.sex;
	}

	public void setSex(String sex){
		this.sex = sex;
	}

	public String getCarB(){
		return this.carB;
	}

	public void setCarB(String carB){
		this.carB = carB;
	}

	public Date getBuyT(){
		return this.buyT;
	}

	public void setBuyT(Date buyT){
		this.buyT = buyT;
	}

	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
