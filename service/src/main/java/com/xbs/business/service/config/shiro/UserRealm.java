package com.xbs.business.service.config.shiro;

import com.xbs.business.dao.base.entity.Role;
import com.xbs.business.dao.base.entity.User;
import com.xbs.business.service.base.intf.RoleService;
import com.xbs.business.service.base.intf.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserRealm extends AuthorizingRealm {

    private UserService userService;

    private RoleService roleService;


    public UserRealm(UserService userService,RoleService roleService){
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) getAvailablePrincipal(principalCollection);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        User personInfo = userService.getUserByUserName(userName);
        List<Role> roles = roleService.listByUserId(personInfo.getId());
        if (CollectionUtils.isNotEmpty(roles)) {
            Set<String> roleNames = roles.stream().map(Role::getName).collect(Collectors.toSet());
            info.setRoles(roleNames);
        }
        return info;
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
