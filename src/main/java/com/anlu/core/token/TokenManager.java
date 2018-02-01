package com.anlu.core.token;

import com.anlu.core.utils.SpringContextUtil;

import java.awt.image.SampleModel;

public class TokenManager {
    //用户登录管理
    public static final SimpleRealm realm = SpringContextUtil.getBean("sampleRealm",SimpleRealm.class);


}
