package com.xbs.business.biz.base;

import com.google.common.collect.Lists;
import com.xbs.business.dao.base.entity.Role;
import com.xbs.business.service.base.intf.RoleService;
import com.xbs.business.web.base.request.RoleMenuRequest;
import com.xbs.business.web.base.request.RoleRequest;
import com.xbs.util.base.ErrorMessage;
import com.xbs.util.base.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author cheng
 * @Date1/2/21 10:42 PM
 * @Version V1.0
 **/
@Service
public class RoleBiz {

    @Autowired
    private RoleService roleService;

    public Result getPageList(String name, Integer pageIndex, Integer pageSize) {

        return Result.success(roleService.getPageList(name,pageIndex,pageSize));
    }

    public Result save(RoleRequest roleRequest) {

        Role role = new Role();
        role.setName(roleRequest.getRoleName());
        if(role.getId() != null){
            role.setId(roleRequest.getRoleId());
        }
        roleService.save(role);
        return Result.success();
    }

    public Result edit(RoleMenuRequest roleMenuRequest) {
        String menuIds = roleMenuRequest.getMenuIds();
        if(roleMenuRequest.getRoleId() == null || StringUtils.isAllEmpty(menuIds,roleMenuRequest.getRoleName())){
            return Result.error(ErrorMessage.PARAM_ERROE);
        }
        Set<Integer> menuSet = new HashSet<>();
        if(StringUtils.isEmpty(roleMenuRequest.getMenuIds())) {
            menuSet = Arrays.stream(menuIds.split(",")).map(menuId -> Integer.parseInt(menuId)).collect(Collectors.toSet());
        }
        roleService.updateRoleMenu(roleMenuRequest.getRoleId(), roleMenuRequest.getRoleName(),Lists.newArrayList(menuSet));
        return Result.success();
    }
}
