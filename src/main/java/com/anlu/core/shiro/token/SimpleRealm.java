package com.anlu.core.shiro.token;

import com.anlu.common.model.URole;
import com.anlu.common.model.UUser;
import com.anlu.core.shiro.token.manager.TokenManager;
import com.anlu.permission.service.PermissionService;
import com.anlu.permission.service.RoleService;
import com.anlu.user.service.UUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.ws.Service;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SimpleRealm extends AuthorizingRealm {


    @Autowired
    UUserService userService;
    @Autowired
    PermissionService permissionService;
    @Autowired
    RoleService roleService;

    public SimpleRealm() {
        super();
    }

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //根据用户ID查询角色（role），放入到Authorization里。
        Long userId = TokenManager.getUserId();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
       //根据用户ID查询角色(role),放入到Authorization里
        Set<String> roles = roleService.findRoleByUserId(userId);
        info.setRoles(roles);

        //根据用户ID查询权限(permission),放入到Authorization里
        Set<String> permissions = permissionService.findPermissionByUserId(userId);
        info.setStringPermissions(permissions);
        return info;
    }

    /**
     * 认证信息，主要针对用户登录
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        ShiroToken token = (ShiroToken) authenticationToken;
        UUser user = userService.login(token.getUsername(), token.getPswd());

        if (user == null) {
            throw new AccountException("帐号或密码不正确！");
        } else if (UUser._0.equals(user.getStatus())) {
            throw new DisabledAccountException("账号已经禁止登录");
        } else {
            //更新登录时间
            user.setLastLoginTime(new Date());
            userService.updateByPrimaryKeySelective(user);
        }
        return new SimpleAuthenticationInfo(user, user.getPswd(), getName());
    }

    /**
     * 清空当前用户权限信息
     */
    public void clearCachedAuthorizationInfo() {
        PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 指定principalCollection 清除
     */
    public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }
}
