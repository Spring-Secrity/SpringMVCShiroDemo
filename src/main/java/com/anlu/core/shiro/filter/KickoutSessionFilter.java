package com.anlu.core.shiro.filter;

import com.anlu.core.shiro.session.ShiroSessionRepository;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class KickoutSessionFilter  extends AccessControlFilter {
    //静态注入
    private String kickoutUrl;

    public String getKickoutUrl() {
        return kickoutUrl;
    }

    public void setKickoutUrl(String kickoutUrl) {
        this.kickoutUrl = kickoutUrl;
    }

    //在线用户
    final static String ONLINE_USER = KickoutSessionFilter.class.getCanonicalName()+ "_online_user";
    //踢出状态，true标示踢出
    final static String KICKOUT_STATUS = KickoutSessionFilter.class.getCanonicalName()+ "_kickout_status";


    //session获取
    static ShiroSessionRepository shiroSessionRepository;

    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return false;
    }



}
