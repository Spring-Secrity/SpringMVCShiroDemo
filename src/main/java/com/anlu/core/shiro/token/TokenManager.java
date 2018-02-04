package com.anlu.core.shiro.token;

import com.anlu.core.utils.SpringContextUtil;

public class TokenManager {
    //用户登录管理
    public static final SimpleRealm realm = SpringContextUtil.getBean("sampleRealm",SimpleRealm.class);


}
