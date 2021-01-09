package com.xbs.business.service.base.intf.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.xbs.business.dao.base.entity.Role;
import com.xbs.business.dao.base.entity.RoleMenu;
import com.xbs.business.dao.base.entity.UserRole;
import com.xbs.business.dao.base.mapper.RoleMapper;
import com.xbs.business.dao.base.mapper.RoleMenuMapper;
import com.xbs.business.dao.base.mapper.UserRoleMapper;
import com.xbs.business.service.base.intf.RoleService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author cheng
 * @Date1/3/21 6:17 AM
 * @Version V1.0
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;


    @Override
    public List<Role> listByUserId(Integer userId) {
        LambdaQueryWrapper<UserRole> qw = new LambdaQueryWrapper<>();
        qw.eq(UserRole::getUserId,userId);
        List<UserRole> userRoles = userRoleMapper.selectList(qw);
        if(CollectionUtils.isEmpty(userRoles)){
            return Lists.newArrayList();
        }
        List<Integer> roleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        return roleMapper.selectBatchIds(roleIds);
    }

    @Override
    public IPage<Role> getPageList(String name, Integer pageIndex, Integer pageSize) {

        Page page = new Page(pageIndex,pageSize);
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotEmpty(name)){
            queryWrapper.like(Role::getName,name);
        }
        return roleMapper.selectPage(page,queryWrapper);
    }

    @Override
    public void save(Role role) {

        if(role.getId() == null) {
            roleMapper.insert(role);
        }else{
            roleMapper.updateById(role);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRoleMenu(Integer roleId, String roleName, List<Integer> roleMenuIds) {
        if(roleId == null){
            return ;
        }
        if(StringUtils.isNotEmpty(roleName)){
            Role role = new Role();
            role.setId(roleId);
            role.setName(roleName);
            roleMapper.updateById(role);
        }
        if(CollectionUtils.isNotEmpty(roleMenuIds)){
            LambdaQueryWrapper<RoleMenu> queryWrapper = new LambdaQueryWrapper();
            queryWrapper.eq(RoleMenu::getRoleId,roleId);
            roleMenuMapper.delete(queryWrapper);

            roleMenuIds.forEach(roleMenuId ->{
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(roleMenuId);
                roleMenuMapper.insert(roleMenu);
            });
        }

    }
}
