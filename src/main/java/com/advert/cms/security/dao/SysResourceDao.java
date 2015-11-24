package com.advert.cms.security.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.advert.cms.security.domain.SysResource;
import com.better.framework.common.dao.jpa.EntityJpaDao;

/**
 * 系统资源表 JPA Dao
 *
 * Date: 2015-05-12 15:39:57
 *
 * @author Code Generator
 *
 */
public interface SysResourceDao extends EntityJpaDao<SysResource, Long> {

    @Modifying
    @Query(value = "update SysResource t set t.status=?2 where id=?1")
    public int updateStatusById(Long id, Integer status);

    @Modifying
    @Query(value = "update SysResource set sortNum=?2, parentId=?3 where id=?1")
    int sort(Long id, Long sortNum, Long parentId);
}
