package com.anlu.core.shiro.cache.impl;

import com.anlu.core.shiro.cache.ShiroCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;

public class CustomShiroCacheManager implements CacheManager, Destroyable {
    private ShiroCacheManager shiroCacheManager;
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return getShiroCacheManager().getCache(s);
    }

    public void destroy() throws Exception {
        shiroCacheManager.destroy();
    }

    public ShiroCacheManager getShiroCacheManager() {
        return shiroCacheManager;
    }

    public void setShiroCacheManager(ShiroCacheManager shiroCacheManager) {
        this.shiroCacheManager = shiroCacheManager;
    }
}
