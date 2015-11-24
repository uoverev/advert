package com.advert.cms.business.service.impl;


import com.advert.cms.business.dao.TestUserInfoDao;
import com.advert.cms.business.domain.TestUserInfo;
import com.advert.cms.business.service.TestUserInfoService;
import com.better.framework.common.service.EntityServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("testUserInfoService")
public class TestUserInfoServiceImpl extends EntityServiceImpl<TestUserInfo, TestUserInfoDao> implements TestUserInfoService {

}
