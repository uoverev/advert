package com.advert.cms.core.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.advert.cms.core.domain.CmsAttachment;
import com.advert.cms.core.service.CmsAttachmentService;
import com.advert.cms.core.service.CmsAttachmentServiceFacade;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * com.feinno.eord.core.service.impl.
 * User: wangyx
 * Date: 14-12-3
 * Time: 下午3:35
 */
public class CmsAttachmentServiceFacadeImpl implements CmsAttachmentServiceFacade {

    private Logger logger = LoggerFactory.getLogger(CmsAttachmentServiceFacadeImpl.class);

    private String attachRoot;

    private CmsAttachmentService cmsAttachmentService;

    @Override
    public CmsAttachment delete(String oldAttachUrl){
    	CmsAttachment attachment = cmsAttachmentService.getByAttachUrl(oldAttachUrl);
        if(attachment==null){
            return null;
        }
        File file = new File(attachment.getFilePath());
        if(file.exists() && file.isFile()){
            final String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
            File[] files = file.getParentFile().listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.indexOf(fileName) != -1;
                }
            });
            for(File f: files){
                f.delete();
            }
        }

        cmsAttachmentService.removeById(attachment.getId());
        return attachment;
    }

    @Override
    public CmsAttachment upload(Long userId, String sysKey, String relationKey, MultipartFile file) {
    	CmsAttachment attachment = new CmsAttachment();
        attachment.setUserId(userId);
        attachment.setRelationKey(relationKey);
        attachment.setSourceName(file.getOriginalFilename());
        attachment.setFileType(file.getContentType());
        attachment.setFileSize(file.getSize());
        attachment.setSysKey(sysKey);
        //文件名
        String fileName = UUID.randomUUID().toString().replaceAll("-", "");
        //文件后缀
        String suffix="";
        int len = file.getOriginalFilename().lastIndexOf(".");
        if(len>=1){
            suffix = file.getOriginalFilename().substring(len);
        }
        attachment.setFileName(fileName+suffix);
        //组装保持路径-文件夹
        Calendar calendar = Calendar.getInstance();
        String path = String.format("/attach/%s/%s/%s/%s",
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH)+1,
                calendar.get(Calendar.DATE),
                attachment.getFileName());
        //原图的保持位置
        attachment.setFilePath(attachRoot+path);
        //站点下的访问路径
        attachment.setAttachUrl(path);
        attachment.setCreateTime(new Date());
        File fileRoot = new File(attachment.getFilePath());

        if(fileRoot.getParentFile().isDirectory() || fileRoot.getParentFile().mkdirs()){
            FileOutputStream fileOutputStream = null;
            try{
                fileOutputStream = new FileOutputStream(fileRoot);
                fileOutputStream.write(file.getBytes());
            } catch (Exception ex){
                throw new RuntimeException(ex);
            } finally {
                if(fileOutputStream!=null){
                    try {
                        fileOutputStream.close();
                    } catch (Exception e) {
                    }
                }
            }
        }
        cmsAttachmentService.save(attachment);
        return attachment;
    }

    @Override
    public CmsAttachment upload(Long userId, String sysKey, String relationKey, MultipartFile file, String oldAttachUrl){
        if(StringUtils.isNotBlank(oldAttachUrl)){
            delete(oldAttachUrl);
        }
        try {
            return upload(userId, sysKey, relationKey, file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getAttachRoot() {
        return attachRoot;
    }

    public void setAttachRoot(String attachRoot) {
        this.attachRoot = attachRoot;
    }

    public CmsAttachmentService getCmsAttachmentService() {
        return cmsAttachmentService;
    }

    public void setCmsAttachmentService(CmsAttachmentService cmsAttachmentService) {
        this.cmsAttachmentService = cmsAttachmentService;
    }
}
