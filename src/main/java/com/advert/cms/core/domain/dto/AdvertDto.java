package com.advert.cms.core.domain.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.advert.cms.core.domain.Advert;

public class AdvertDto implements Serializable{

	private static final long serialVersionUID = 1L;

	/** id */
	private Long id;
	
	/** 分组名称 */
	private String name;
	
	/** 类型 */
	private Integer type;
	
	/** 图片播放间隔时间,秒 */
	private Long imgInterval;
	
	/** 播放开始时间22:00 */
	private String startTime;
	
	/** 播放结束时间23:00 */
	private String entTime;
	
	/** 循环次数 */
	private Integer cycle;
	
	/** 最后更新时间 */
	private Date updateTime;
	
	/** 广告详细 */
	private List<Advert> list;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getType() {
		return type;
	}

	public Long getImgInterval() {
		return imgInterval;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEntTime() {
		return entTime;
	}

	public Integer getCycle() {
		return cycle;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public List<Advert> getList() {
		return list;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setImgInterval(Long imgInterval) {
		this.imgInterval = imgInterval;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEntTime(String entTime) {
		this.entTime = entTime;
	}

	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setList(List<Advert> list) {
		this.list = list;
	}
	
}
