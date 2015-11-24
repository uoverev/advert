package com.advert.cms.core.service.impl;

import org.springframework.stereotype.Service;

import com.advert.cms.core.dao.CmsAttachmentDao;
import com.advert.cms.core.domain.CmsAttachment;
import com.advert.cms.core.service.CmsAttachmentService;
import com.better.framework.common.service.EntityServiceImpl;

@Service("cmsAttachmentService")
public class CmsAttachmentServiceImpl extends EntityServiceImpl<CmsAttachment, CmsAttachmentDao> implements CmsAttachmentService {

    @Override
    public CmsAttachment getByAttachUrl(String attachUrl) {
        return getEntityDao().getByAttachUrl(attachUrl);
    }
}
