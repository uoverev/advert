package com.advert.cms.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.advert.cms.core.domain.Advert;
import com.better.framework.common.dao.jpa.EntityJpaDao;

/**
 * advert JPA Dao
 *
 * Date: 2015-12-02 13:09:15
 *
 * @author Code Generator
 *
 */
public interface AdvertDao extends EntityJpaDao<Advert, Long> {
	
	@Query(value="SELECT * FROM advert WHERE id in(SELECT advert_id FROM advert_group_map WHERE group_id = ?1 ORDER BY sort_no desc)",nativeQuery=true)
	public List<Advert> queryAdvertByGroupId(Long groupId);

}
