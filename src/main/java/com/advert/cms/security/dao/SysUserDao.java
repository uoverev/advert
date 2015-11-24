package com.advert.cms.security.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.advert.cms.security.domain.SysUser;
import com.better.framework.common.dao.jpa.EntityJpaDao;

/**
 * 系统用户表 JPA Dao
 *
 * Date: 2015-05-11 16:11:20
 *
 * @author Code Generator
 *
 */
public interface SysUserDao extends EntityJpaDao<SysUser, Long> {

	public SysUser findByUsername(String username);
	
    @Modifying
    @Query(value = "update SysUser t set t.status=?2 where id=?1")
    public int updateStatusById(Long id, Integer status);

    @Modifying
    @Query(value = "update SysUser t set t.password=?2, t.salt=?3 where id=?1")
    public int updateUserpassById(Long id, String userpass, String salt);
    
    @Modifying
    @Query(value = "update SysUser t set t.lastLoginDate=?2, t.lastLoginIp=?3 where id=?1")
    public int updateUserLoginById(Long id, Date lastLoginDate, String lastLoginIp);
    
    public SysUser findByUsernameAndStatus(String username,Integer status);
}
