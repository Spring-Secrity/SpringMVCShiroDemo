package com.anlu.core.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class RoleFilter extends AccessControlFilter {


    static final String LOGIN_URL = "http://www.sojson.com/user/open/toLogin.shtml";
    static final String UNAUTHORIZED_URL = "http://www.sojson.com/unauthorized.html";

    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {

        String[] arra = (String[])o;
        Subject subject = getSubject(servletRequest,servletResponse);
        for (String role : arra) {
            if(subject.hasRole("role:" + role)){
                return true;
            }
        }
        return false;
    }

    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        Subject subject = getSubject(servletRequest, servletResponse);
        if(subject.getPrincipal() == null){//表示没有登录，重定向到登录页面
            saveRequest(servletRequest);
            WebUtils.issueRedirect(servletRequest, servletResponse, LOGIN_URL);
        }else{
            if(StringUtils.hasText(UNAUTHORIZED_URL)){//如果有未授权页面跳转过去
                WebUtils.issueRedirect(servletRequest, servletResponse, UNAUTHORIZED_URL);
            }else{//否则返回401未授权状态码
                WebUtils.toHttp(servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
        return false;
    }
}
