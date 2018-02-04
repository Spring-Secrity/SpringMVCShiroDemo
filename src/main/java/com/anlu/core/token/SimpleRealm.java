package com.anlu.core.token;

import com.anlu.common.model.URole;
import com.anlu.common.model.UUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SimpleRealm extends AuthorizingRealm {

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
        //根据用户ID查询角色（role），放入到Authorization里。

        URole role1 = new URole();
        role1.setName("DBA");

        Set<String> roles = new HashSet<String>();
        roles.add("DBA");
        info.setRoles(roles);
        //根据用户ID查询权限（permission），放入到Authorization里。
        Set<String> permissions = new HashSet<String>();
        permissions.add("delete");
        info.setStringPermissions(permissions);
        return info;
    }

    /**
     * 认证信息，主要针对用户登录
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
       ShiroToken token = (ShiroToken)authenticationToken;

        UUser user = new UUser();
        if(user==null){
            throw new AccountException("帐号或密码不正确！");
        }else if(UUser._0.equals(user.getStatus())){
            throw new DisabledAccountException("账号已经禁止登录");
        }else{
            //更新登录时间
            user.setLastLoginTime(new Date());
        }
        return new SimpleAuthenticationInfo(user,user.getPswd(),getName());
    }
}
