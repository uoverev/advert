package com.advert.cms.core.service;

import org.springframework.web.multipart.MultipartFile;

import com.advert.cms.core.domain.CmsAttachment;

/**
 * com.feinno.eord.core.service.
 * User: wangyx
 * Date: 14-12-3
 * Time: 下午3:33
 */
public interface CmsAttachmentServiceFacade {

    public CmsAttachment delete(String oldAttachUrl);

    public CmsAttachment upload(Long userId, String sysKey, String relationKey, MultipartFile file);

    public CmsAttachment upload(Long userId, String sysKey, String relationKey, MultipartFile file, String oldAttachUrl);
}
