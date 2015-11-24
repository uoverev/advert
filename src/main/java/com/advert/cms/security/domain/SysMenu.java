package com.advert.cms.security.domain;


import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.better.framework.common.domain.AbstractEntity;

/**
 * 系统菜单表 Entity
 *
 * Date: 2015-05-28 09:55:09
 *
 * @author Acooly Code Generator
 */
@Entity
@Table(name = "sys_menu")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysMenu extends AbstractEntity {
	/** 主键ID */
	private Long id;
	
	/** 父菜单 */
	private Long parentId;
	
	/** 菜单名称 */
	private String title;
	
	/** 菜单名称拼音首字母 */
	private String titleFirstSpell;
	
	/** 菜单图标 */
	private String icon;
	
	/** 显示方式 */
	private Integer showMode;
	
	/** 描述 */
	private String descn;
	
	/** 排序 */
	private Long sortNum;
	
	/** 状态 */
	private Integer status;
	
	/** 资源ID */
	private Long resourceId;
	
	/** 创建时间 */
	private Date createDate;

    private Set<SysMenu> subMenus = new LinkedHashSet<SysMenu>();
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getParentId(){
		return this.parentId;
	}
	
	public void setParentId(Long parentId){
		this.parentId = parentId;
	}
	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	public String getTitleFirstSpell(){
		return this.titleFirstSpell;
	}
	
	public void setTitleFirstSpell(String titleFirstSpell){
		this.titleFirstSpell = titleFirstSpell;
	}
	public String getIcon(){
		return this.icon;
	}
	
	public void setIcon(String icon){
		this.icon = icon;
	}
	public Integer getShowMode(){
		return this.showMode;
	}
	
	public void setShowMode(Integer showMode){
		this.showMode = showMode;
	}
	public String getDescn(){
		return this.descn;
	}
	
	public void setDescn(String descn){
		this.descn = descn;
	}
	public Long getSortNum(){
		return this.sortNum;
	}
	
	public void setSortNum(Long sortNum){
		this.sortNum = sortNum;
	}
	public Integer getStatus(){
		return this.status;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	public Long getResourceId(){
		return this.resourceId;
	}
	
	public void setResourceId(Long resourceId){
		this.resourceId = resourceId;
	}
	public Date getCreateDate(){
		return this.createDate;
	}
	
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    @OrderBy("sortNum ASC")
    //@Where(clause = "status=1")
    public Set<SysMenu> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(Set<SysMenu> subMenus) {
        this.subMenus = subMenus;
    }

    @Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
