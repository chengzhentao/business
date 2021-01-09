package com.xbs.business.service.base.intf;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xbs.business.dao.base.entity.Role;

import java.util.List;

/**
 * @Description
 * @Author cheng
 * @Date1/3/21 6:16 AM
 * @Version V1.0
 **/
public interface RoleService {


    /**
     *根据userId获取权限
     * @param userId
     * @return
     */
    List<Role> listByUserId(Integer userId);
    /**
     *
     * 分页获取数据
     * @param name
     * @param pageIndex
     * @param pageSize
     * @return
     */
    IPage<Role> getPageList(String name, Integer pageIndex, Integer pageSize);

    /**
     * 保存信息
     * @param role
     */
    void save(Role role);

    /**
     * 保存信息
     * @param roleId
     * @param roleName
     * @param roleMenuIds
     */
    void updateRoleMenu(Integer roleId, String roleName, List<Integer> roleMenuIds);
}
