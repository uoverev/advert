package com.advert.cms.security.domain;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OrderBy;

import com.better.framework.common.domain.AbstractEntity;

/**
 * 系统用户表 Entity
 *
 * Date: 2015-05-11 16:11:20
 *
 * @author Acooly Code Generator
 */
@Entity
@Table(name = "sys_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysUser extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -582186445384211844L;

	/** 主键ID */
	private Long id;
	
	/** 登录用户名 */
	private String username;
	
	/** 操作员姓名 */
	private String nickname;
	
	/** 登录密码 */
	private String password;
	
	/** 密码加密填充值 */
	private String salt;
	
	/** 用户类型 */
	private Integer usertype;
	
	/** 电子邮件 */
	private String email;
	
	/** 状态 */
	private Integer status;
	
	/** 描述 */
	private String descn;
	
	/** 创建时间 */
	private Date createDate;
	
	/** 最后登录时间 */
	private Date lastLoginDate;
	
	/** 最后登录IP */
	private String lastLoginIp;
	
	/** 过期时间 */
	private Date expiredDate;
	
	/** 解锁时间 */
	private Date unlockDate;

	/** 所属的角色 */
	private SysRole role;
	
	//用户状态为启用
	public static Integer STATUS_START = 1;
	
	//用户状态为停用
	public static Integer STATUS_STOP = 0;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getUsername(){
		return this.username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	public String getNickname(){
		return this.nickname;
	}
	
	public void setNickname(String nickname){
		this.nickname = nickname;
	}
	public String getPassword(){
		return this.password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	public String getSalt(){
		return this.salt;
	}
	
	public void setSalt(String salt){
		this.salt = salt;
	}
	public Integer getUsertype(){
		return this.usertype;
	}
	
	public void setUsertype(Integer usertype){
		this.usertype = usertype;
	}
	public String getEmail(){
		return this.email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	public Integer getStatus(){
		return this.status;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	public String getDescn(){
		return this.descn;
	}
	
	public void setDescn(String descn){
		this.descn = descn;
	}
	public Date getCreateDate(){
		return this.createDate;
	}
	
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	public Date getLastLoginDate(){
		return this.lastLoginDate;
	}
	
	public void setLastLoginDate(Date lastLoginDate){
		this.lastLoginDate = lastLoginDate;
	}
	public String getLastLoginIp(){
		return this.lastLoginIp;
	}
	
	public void setLastLoginIp(String lastLoginIp){
		this.lastLoginIp = lastLoginIp;
	}
	public Date getExpiredDate(){
		return this.expiredDate;
	}
	
	public void setExpiredDate(Date expiredDate){
		this.expiredDate = expiredDate;
	}
	public Date getUnlockDate(){
		return this.unlockDate;
	}
	
	public void setUnlockDate(Date unlockDate){
		this.unlockDate = unlockDate;
	}

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, targetEntity = SysRole.class)
	@JoinTable(name = "sys_user_role", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	@OrderBy(clause = "ROLE_ID")
	public SysRole getRole() {
		return role;
	}

	public void setRole(SysRole role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
