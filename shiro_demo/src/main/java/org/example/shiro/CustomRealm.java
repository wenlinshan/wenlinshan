package org.example.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.example.bean.User;
import org.example.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * 权限控制以及权限认证
 *
 */
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private LoginService loginService;

    /**
     * 权限配置类
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前登录用户名
        String userName = principalCollection.getPrimaryPrincipal().toString();
        User user = loginService.getUserByName(userName);

        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        user.getRoles().forEach(role -> {
            simpleAuthorizationInfo.addRole(role.getRoleName());
            role.getPermissions().forEach(permission -> simpleAuthorizationInfo.addStringPermission(permission.getPermissionsName()));
        });
        return simpleAuthorizationInfo;

    }

    /**
     * 权限认证类
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        Object username = authenticationToken.getPrincipal();
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        //获取用户信息
        String name = username.toString();
        User user = loginService.getUserByName(name);
        if (user == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            return new SimpleAuthenticationInfo(name, user.getPassword(), getName());
        }
    }
}
