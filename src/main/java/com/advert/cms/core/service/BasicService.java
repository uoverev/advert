package com.advert.cms.core.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.advert.cms.business.dao.RegionDao;
import com.advert.cms.business.domain.Region;

/**
 * 
 * @author lihanpei
 *
 */
public class BasicService {
	
	private static final Logger logger = LoggerFactory.getLogger(BasicService.class);
	private byte[] lock = new byte[0];
	private static Map<String, String> codeMap;
	@Autowired
	private RegionDao regionDao;
	
//	@Autowired
//	private SpyMemcachedClient spyMemcachedClient;

	// 一级区域列表
	private static List<Region> oneLevelRegionList = null;
	//其它区域二级以上
	private static List<Region> otherLevelList = null;
	// 区域名称
	// private static Map<String, String> regionNameMap = new HashMap<String,
	// String>();

	// 区域Map
	private static Map<String, List<Region>> regLevel2Map = new HashMap<String, List<Region>>();
	// 区域Map
	private static Map<String, List<Region>> regionMap = null;
	// 根据编码取编码数据
	private static Map<String, Region> regionCodeNameMap = null;
	
	public void init() {
		try {	
			logger.info("BasicServiceImpl--->启动加载公用字典数据");
		} catch (Exception e) {
			logger.error("BasicServiceImpl--->启动加载数据--->异常");
			e.printStackTrace();
		}
	}
	
	/**
	 * 加载区域数据
	 * 
	 * <p>Description:</p>
	 */
	private void loadRegionData(){
		
		regionMap = new HashMap<String, List<Region>>();
		regionCodeNameMap = new HashMap<String, Region>();
		// 取国内一级地区
		oneLevelRegionList = regionDao.queryByOneLevelList();
		if (null!=oneLevelRegionList) {
			for(Region re : oneLevelRegionList){
				regionCodeNameMap.put(re.getCode()+"", re);
			}
		}
		
		// 取国内除1级外的区域列表
		List<Region> list = regionDao.queryByOtherLevelList();
		otherLevelList = list;
		if (null!=list) {
			for(Region re : list){
				regionCodeNameMap.put(re.getCode()+"", re);
				
				String pcode = re.getPcode();
				
				if (regionMap.containsKey(pcode)) {
					List<Region> pList = regionMap.get(pcode);
					pList.add(re);
					regionMap.put(pcode, pList);
				} else {
					List<Region> nlist = new ArrayList<Region>();
					nlist.add(re);
					regionMap.put(pcode, nlist);
				}
			}
		}
	}
	
	/**
	 * 取国内一级区域列表
	 * 
	 * <p>Description:</p>
	 * @return
	 */
	public List<Region> getOneLevelRegionList(){
		return oneLevelRegionList;
	}
	
	/**
	 * 根据区域编码，取该编码下级区域列表
	 * 
	 * <p>Description:</p>
	 * @param code
	 * @return
	 */
	public List<Region> getRegionListByCode(String code){
		return regionMap.get(code);
	}
	
	/**
	 * 根据编码取区域全称
	 * 
	 * <p>Description:</p>
	 * @param code
	 * @return
	 */
	public String getRegionFullNameByCode(String code){
		Region re = regionCodeNameMap.get(code);
		String fullName = "";
		if(null!=re){
			fullName = re.getFullname();
		}
		return fullName;
	}
	
	/**
	 * 根据编码取地区对象
	 * 
	 * <p>Description:</p>
	 * @param code
	 * @return
	 */
	public Region getRegionByCode(String code){
		return regionCodeNameMap.get(code);
	}
	public List<Region> queryByOtherLevelList() {
		return otherLevelList;
	}

	/**
	 * 获取省下的二级区域（直辖市是3级区域）
	 * 
	 * @return
	 */
	public List<Region> getRegListByCode(String code) {
		List<Region> regionList = regLevel2Map.get(code);
		if (null == regionList || regionList.size() < 1) {
			synchronized (lock){
				regionList = regLevel2Map.get(code);
				if (null != regionList && regionList.size() >0) {
					return regionList;
				}else{
					if (codeMap.containsKey(code)) {
						String name = codeMap.get(code);
						// 直辖市查询3级区域
						regionList = regionDao.queryListByName(name, 3l);
					}else{
						// 省查询二级区域
						regionList = regionDao.queryListByCode(code);
					}
					regLevel2Map.put(code, regionList);
					return regionList;
				}
            }
		}
		return regionList;
	}

	public String getRegionNameByCode(String code) {
		logger.info("通过code获取区域名称，code是：" + code);
		Region region = regionCodeNameMap.get(code);
		if (null != region) {
			logger.info("通过code获取区域名称，名称是：" + region.getName());
			return region.getName();
		}
		return "";
	}
}
