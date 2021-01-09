package com.xbs.business.biz.base;

import com.google.common.collect.Lists;
import com.xbs.business.dao.base.entity.Menu;
import com.xbs.business.dao.base.entity.Role;
import com.xbs.business.dao.base.entity.RoleMenu;
import com.xbs.business.service.base.intf.MenuService;
import com.xbs.business.service.base.intf.RoleService;
import com.xbs.business.service.base.intf.UserService;
import com.xbs.business.web.base.response.UserMenuResponse;
import com.xbs.util.base.Result;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author cheng
 * @Date1/2/21 3:18 PM
 * @Version V1.0
 **/
@Service
public class MenuBiz {

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    public Result getPageList(String name,Integer pageIndex,Integer pageSize) {

       return  Result.success(menuService.getPageList(name,pageIndex,pageSize));
    }

    public Result getUserMenu(Integer userId) {

        List<Role> roleList = roleService.listByUserId(userId);
        if(CollectionUtils.isEmpty(roleList)){
            return Result.success();
        }
        List<Integer> roleIds = roleList.stream().map(Role::getId).collect(Collectors.toList());
        List<Menu> menus = menuService.listByRoleIds(roleIds);
        UserMenuResponse userMenuResponse = new UserMenuResponse();
        if(CollectionUtils.isEmpty(menus)){
            return Result.success(userMenuResponse.getUserMenuResponseList());
        }
        this.repeatResponse(userMenuResponse,menus);
        return Result.success(userMenuResponse.getUserMenuResponseList());
    }

    private void repeatResponse(UserMenuResponse userMenuResponse, List<Menu> menus) {
        Integer partentId = userMenuResponse.getId();
        Iterator<Menu> iterator = menus.iterator();
        List<UserMenuResponse> responseList = Lists.newArrayList();
        while(iterator.hasNext()){
            Menu menu = iterator.next();
            if(menu.getParentId().equals(partentId)){
                UserMenuResponse innerResponse = new UserMenuResponse();
                innerResponse.setId(menu.getId());
                innerResponse.setName(menu.getName());
                iterator.remove();
                this.repeatResponse(innerResponse,menus);
                responseList.add(innerResponse);
                userMenuResponse.setUserMenuResponseList(responseList);
            }
        }
    }
}
