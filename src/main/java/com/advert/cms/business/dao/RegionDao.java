package com.advert.cms.business.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.advert.cms.business.domain.Region;
import com.better.framework.common.dao.jpa.EntityJpaDao;

/**
 * 行政区域表 JPA Dao
 *
 * Date: 2015-11-06 11:40:15
 *
 * @author Code Generator
 *
 */
public interface RegionDao extends EntityJpaDao<Region, Long> {

	/**
	 * 查询1级列表
	 * <p>Description:</p>
	 * @return
	 */
	@Query(value = "FROM Region WHERE pcode=0 AND hieraechy=1 ORDER BY code")
	List<Region> queryByOneLevelList();
	
	/**
	 * 查询所有
	 * <p>Description:</p>
	 * @return
	 */
	@Query(value = "FROM Region ORDER BY code")
	List<Region> queryList();
	
	/**
	 * 查询除1级外的区域列表
	 * 
	 * <p>Description:</p>
	 * @return
	 */
	@Query(value = "FROM Region WHERE hieraechy>1 ORDER BY pcode")
	List<Region> queryByOtherLevelList();

	/**
	 * <p>
	 * Description:查询某个省下的二级区域
	 * </p>
	 * 
	 * @return
	 * @author huxiaoping on 2015年5月27日
	 */
	@Query(value = "from Region where pcode=?1 ORDER BY code")
	public List<Region> queryListByCode(String pcode);

	/**
	 * <p>
	 * Description:查询某个直辖市下的三级区域
	 * </p>
	 * 
	 * @return
	 * @author huxiaoping on 2015年5月28日
	 */
	@Query(value = "from Region where level1name=?1 and hieraechy=?2 ORDER BY code")
	public List<Region> queryListByName(String name, Long hieraechy);
}
