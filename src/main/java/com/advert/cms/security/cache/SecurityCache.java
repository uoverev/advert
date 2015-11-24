package com.advert.cms.security.cache;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.advert.cms.security.dto.MenuView;
import com.better.module.security.shiro.realm.SampleRealm;

/**
 * Created by Administrator on 2015/6/4.
 * 安全框架cache
 */
@Service
public class SecurityCache {

    private final static String MENU_CACHE_KEY="menu.view.cache.key";

    @Resource
    private SampleRealm sampleRealm;

    @Resource
    private CacheManager cacheManager;

    //授权 cache
    public void clearAuthorization(String userName){
        sampleRealm.clearCachedAuthorizationInfo(userName);
    }

    public void clearAllAuthorization(){
        sampleRealm.clearAllCachedAuthorizationInfo();
    }


    //获取菜单
    public List<MenuView> getMenu(Long roleId){
        Cache<Object, Object> cache = cacheManager.getCache(MENU_CACHE_KEY);
        return (List<MenuView>) cache.get(roleId);
    }

    //菜单 cache
    public void putMenu(Long roleId, List<MenuView> list){
        Cache<Object, Object> cache = cacheManager.getCache(MENU_CACHE_KEY);
        cache.put(roleId, list);
    }

    //删除菜单
    public void clearMenu(Long roleId){
        Cache<Object, Object> cache = cacheManager.getCache(MENU_CACHE_KEY);
        cache.remove(roleId);
    }

    //删除所有菜单
    public void clearAllMenu(){
        Cache<Object, Object> cache = cacheManager.getCache(MENU_CACHE_KEY);
        cache.clear();
    }

}
