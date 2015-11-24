package com.advert.cms.business.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.better.framework.common.domain.AbstractEntity;


/**
 * 行政区域表; InnoDB free: 140288 kB Entity
 * 
 * Date: 2014-03-26 10:34:29
 * 
 * @author Acooly Code Generator
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "OL_REGION")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Region extends AbstractEntity {
	/** 主键 */
	private Long id;

	/** 区域编码 */
	private Long code;

	/** 父级区域编码 */
	private String pcode;

	/** 层级 */
	private Long hieraechy;

	/** 区域名称 */
	private String name;

	/** 省级区域名称 */
	private String level1name;

	/** 地市级区域名称 */
	private String level2name;

	/** 区县级区域名称 */
	private String level3name;

	/** 乡镇级区域名称 */
	private String level4name;

	/** 村，街道级区域名称 */
	private String level5name;

	/** 中央信息库对应区域编码 */
	private String seftid;

	/** 区域全称 */
	private String fullname;

	/** 拼音首字母缩写 */
	private String phoneticizeab;

	/** 全拼 */
	private String phoneticize;

	@Id
	@GeneratedValue
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCode() {
		return this.code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getPcode() {
		return this.pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public Long getHieraechy() {
		return this.hieraechy;
	}

	public void setHieraechy(Long hieraechy) {
		this.hieraechy = hieraechy;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevel1name() {
		return this.level1name;
	}

	public void setLevel1name(String level1name) {
		this.level1name = level1name;
	}

	public String getLevel2name() {
		return this.level2name;
	}

	public void setLevel2name(String level2name) {
		this.level2name = level2name;
	}

	public String getLevel3name() {
		return this.level3name;
	}

	public void setLevel3name(String level3name) {
		this.level3name = level3name;
	}

	public String getLevel4name() {
		return this.level4name;
	}

	public void setLevel4name(String level4name) {
		this.level4name = level4name;
	}

	public String getLevel5name() {
		return this.level5name;
	}

	public void setLevel5name(String level5name) {
		this.level5name = level5name;
	}

	public String getSeftid() {
		return this.seftid;
	}

	public void setSeftid(String seftid) {
		this.seftid = seftid;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPhoneticizeab() {
		return this.phoneticizeab;
	}

	public void setPhoneticizeab(String phoneticizeab) {
		this.phoneticizeab = phoneticizeab;
	}

	public String getPhoneticize() {
		return this.phoneticize;
	}

	public void setPhoneticize(String phoneticize) {
		this.phoneticize = phoneticize;
	}

	@Override
	public String toString() {
		return "Region [id=" + id + ", code=" + code + ", pcode=" + pcode
				+ ", hieraechy=" + hieraechy + ", name=" + name
				+ ", level1name=" + level1name + ", level2name=" + level2name
				+ ", level3name=" + level3name + ", level4name=" + level4name
				+ ", level5name=" + level5name + ", seftid=" + seftid
				+ ", fullname=" + fullname + ", phoneticizeab=" + phoneticizeab
				+ ", phoneticize=" + phoneticize + "]";
	}

}
