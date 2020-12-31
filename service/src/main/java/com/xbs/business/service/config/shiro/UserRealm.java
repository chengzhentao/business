package com.xbs.business.service.config.shiro;

import com.xbs.business.dao.base.entity.User;
import com.xbs.business.service.base.intf.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class UserRealm extends AuthorizingRealm {

    private UserService userService;


    public UserRealm(UserService userService){
        this.userService = userService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        // 获取用户名
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User personInfo = userService.getUserByUserName(token.getUsername());
        if (personInfo == null) {
            throw new IncorrectCredentialsException("用户名或密码不正确") ;
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(personInfo.getUserName(), personInfo.getPassword(), this.getName());
        return info;
    }
}
