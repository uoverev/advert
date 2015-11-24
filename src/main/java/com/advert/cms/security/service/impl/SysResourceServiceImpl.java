package com.advert.cms.security.service.impl;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.advert.cms.security.cache.SecurityCache;
import com.advert.cms.security.dao.SysResourceDao;
import com.advert.cms.security.domain.SysResource;
import com.advert.cms.security.service.SysResourceService;
import com.better.framework.common.exception.BusinessException;
import com.better.framework.common.service.EntityServiceImpl;

@Service("sysResourceService")
public class SysResourceServiceImpl extends EntityServiceImpl<SysResource, SysResourceDao> implements SysResourceService {

    private Logger logger = LoggerFactory.getLogger(SysResourceServiceImpl.class);

    @Autowired
    private SecurityCache securityCache;

    @Override
    public void update(SysResource o) throws BusinessException {
        super.update(o);
        securityCache.clearAllAuthorization();
        securityCache.clearAllMenu();
    }

    @Override
    @Transactional
    public int updateStatusById(Long id, Integer status){
        return super.getEntityDao().updateStatusById(id, status);
    }

    @Override
    @Transactional
    public void sort(Long[] ids, Long[] sortNums, Long[] parentIds) {
        for(int j=0; j<ids.length; j++){
            super.getEntityDao().sort(ids[j], sortNums[j], parentIds[j]);
        }
    }

    //递归查找
    private void recursionResc(Iterator<SysResource> it, String rescType, Integer status){
        while (it.hasNext()){
            SysResource resc = it.next();

            if((StringUtils.isNotBlank(rescType) && rescType.equals(resc.getRestype())==false)
                    || (status!=null && status!=resc.getStatus())){
                it.remove();
            }else if(resc.getSubResource()!=null && resc.getSubResource().size()>=1){
                recursionResc(resc.getSubResource().iterator(), rescType, status);
            }
        }
    }

    @Override
    public List<SysResource> queryByTypeAndStatus(String type, Integer status) {
        Map<String, Object> seachMap = new HashMap<String, Object>();
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        seachMap.put("EQ_parentId", 0);
        if(StringUtils.isNotBlank(type)){
            seachMap.put("EQ_restype", type);
        }
        if(status!=null){
            seachMap.put("EQ_status", status);
        }
        sortMap.put("sortNum", true);

        List<SysResource> list = super.getEntityDao().query(seachMap, sortMap);

        //递归过滤条件
        recursionResc(list.iterator(), type, status);

        return list;
    }


}
