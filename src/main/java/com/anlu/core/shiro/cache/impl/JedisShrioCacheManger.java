package com.anlu.core.shiro.cache.impl;

import com.anlu.core.shiro.cache.JedisManager;
import com.anlu.core.shiro.cache.JedisShiroCache;
import com.anlu.core.shiro.cache.ShiroCacheManager;
import org.apache.shiro.cache.Cache;

public class JedisShrioCacheManger implements ShiroCacheManager {
    private JedisManager jedisManager;


    public <K, V> Cache<K, V> getCache(String name) {
        return new JedisShiroCache<K, V>(name, getJedisManager());
    }


    public void destroy() {
        //如果和其他系统，或者应用在一起就不能关闭
        //getJedisManager().getJedis().shutdown();
    }

    public JedisManager getJedisManager() {
        return jedisManager;
    }

    public void setJedisManager(JedisManager jedisManager) {
        this.jedisManager = jedisManager;
    }
}
