package com.anlu.core.token;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.shiro.authc.UsernamePasswordToken;

import java.io.Serializable;

public class ShiroToken extends UsernamePasswordToken implements Serializable {
    /** 登录密码[字符串类型] 因为父类是char[] ] **/
    private String pswd ;

    public String getPswd() {
        return pswd;
    }


    public void setPswd(String pswd) {
        this.pswd = pswd;
    }
    public ShiroToken(String username, String password) {
        super(username, password);
        this.pswd=password;
    }
}
