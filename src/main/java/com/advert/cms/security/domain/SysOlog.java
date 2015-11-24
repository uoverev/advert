package com.advert.cms.security.domain;


import java.util.Date;

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
 * 系统日志 Entity
 *
 * Date: 2015-05-14 16:04:44
 *
 * @author Acooly Code Generator
 */
@Entity
@Table(name = "sys_olog")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysOlog extends AbstractEntity {
	/** 主键 */
	private Long id;
	
	/** 模块 */
	private String module;
	
	/** 模块名称 */
	private String moduleName;
	
	/** 操作 */
	private String action;
	
	/** 操作名称 */
	private String actionName;
	
	/** 执行时间 */
	private Long executeMilliseconds;
	
	/** 操作时间 */
	private Date operateTime;
	
	/** 操作员 */
	private String operateUser;
	
	/** 操作员ID */
	private Long operateUserId;
	
	/** 请求参数 */
	private String requestParameters;
	
	/** 操作结果 */
	private Integer operateResult;
	
	/** 消息 */
	private String operateMessage;
	
	/** 客户端信息 */
	private String clientInformations;
	
	/** 备注 */
	private String descn;
	
	/** 平台标识 */
	private String tag;
	
	/** 会话ID */
	private String sessionId;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getModule(){
		return this.module;
	}
	
	public void setModule(String module){
		this.module = module;
	}
	public String getModuleName(){
		return this.moduleName;
	}
	
	public void setModuleName(String moduleName){
		this.moduleName = moduleName;
	}
	public String getAction(){
		return this.action;
	}
	
	public void setAction(String action){
		this.action = action;
	}
	public String getActionName(){
		return this.actionName;
	}
	
	public void setActionName(String actionName){
		this.actionName = actionName;
	}
	public Long getExecuteMilliseconds(){
		return this.executeMilliseconds;
	}
	
	public void setExecuteMilliseconds(Long executeMilliseconds){
		this.executeMilliseconds = executeMilliseconds;
	}
	public Date getOperateTime(){
		return this.operateTime;
	}
	
	public void setOperateTime(Date operateTime){
		this.operateTime = operateTime;
	}
	public String getOperateUser(){
		return this.operateUser;
	}
	
	public void setOperateUser(String operateUser){
		this.operateUser = operateUser;
	}
	public Long getOperateUserId(){
		return this.operateUserId;
	}
	
	public void setOperateUserId(Long operateUserId){
		this.operateUserId = operateUserId;
	}
	public String getRequestParameters(){
		return this.requestParameters;
	}
	
	public void setRequestParameters(String requestParameters){
		this.requestParameters = requestParameters;
	}
	public Integer getOperateResult(){
		return this.operateResult;
	}
	
	public void setOperateResult(Integer operateResult){
		this.operateResult = operateResult;
	}
	public String getOperateMessage(){
		return this.operateMessage;
	}
	
	public void setOperateMessage(String operateMessage){
		this.operateMessage = operateMessage;
	}
	public String getClientInformations(){
		return this.clientInformations;
	}
	
	public void setClientInformations(String clientInformations){
		this.clientInformations = clientInformations;
	}
	public String getDescn(){
		return this.descn;
	}
	
	public void setDescn(String descn){
		this.descn = descn;
	}
	public String getTag(){
		return this.tag;
	}
	
	public void setTag(String tag){
		this.tag = tag;
	}
	public String getSessionId(){
		return this.sessionId;
	}
	
	public void setSessionId(String sessionId){
		this.sessionId = sessionId;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
