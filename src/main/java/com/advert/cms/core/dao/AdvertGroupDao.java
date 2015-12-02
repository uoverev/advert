package com.advert.cms.core.dao;

import org.springframework.data.jpa.repository.Query;

import com.advert.cms.core.domain.AdvertGroup;
import com.better.framework.common.dao.jpa.EntityJpaDao;

/**
 * advert_group JPA Dao
 *
 * Date: 2015-12-02 13:09:49
 *
 * @author Code Generator
 *
 */
public interface AdvertGroupDao extends EntityJpaDao<AdvertGroup, Long> {
	
	@Query(value="SELECT * FROM advert_group WHERE id in (SELECT group_id FROM advert_group_agent WHERE agent_id = (SELECT agent_id FROM advert_clent_side WHERE CODE = ?1)) and NOW() >= start_time and NOW() < ent_time",nativeQuery=true)
	public AdvertGroup getAdvertGroupByCode(String code);

}
