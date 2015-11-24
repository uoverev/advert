package com.advert.cms.core.dao;

import org.springframework.data.jpa.repository.Query;

import com.advert.cms.core.domain.CmsAttachment;
import com.better.framework.common.dao.jpa.EntityJpaDao;

/**
 * 附件列表 JPA Dao
 *
 * Date: 2014-12-02 13:39:35
 *
 * @author Feinno Code Generator
 *
 */
public interface CmsAttachmentDao extends EntityJpaDao<CmsAttachment, Long> {

    @Query("from CmsAttachment where attachUrl = ?1")
    CmsAttachment getByAttachUrl(String attachUrl);

}
