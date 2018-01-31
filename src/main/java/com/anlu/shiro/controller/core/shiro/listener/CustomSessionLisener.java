package com.anlu.shiro.controller.core.shiro.listener;

import com.anlu.shiro.controller.core.shiro.session.ShiroSessionRepository;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.web.servlet.ShiroHttpSession;

public class CustomSessionLisener implements SessionListener{
    private ShiroSessionRepository shiroSessionRepository;

    /**
     * 一个回话的生命周期开始
     */

    public void onStart(Session session) {
        //TODO
        System.out.println("on start");
    }
    /**
     * 一个回话的生命周期结束
     */

    public void onStop(Session session) {
        //TODO
        System.out.println("on stop");
    }


    public void onExpiration(Session session) {
        shiroSessionRepository.deleteSession(session.getId());
    }

    public ShiroSessionRepository getShiroSessionRepository() {
        return shiroSessionRepository;
    }

    public void setShiroSessionRepository(ShiroSessionRepository shiroSessionRepository) {
        this.shiroSessionRepository = shiroSessionRepository;
    }
}
