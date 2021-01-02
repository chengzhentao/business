package com.xbs.business.service.base.intf.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xbs.business.dao.base.entity.Menu;
import com.xbs.business.dao.base.mapper.MenuMapper;
import com.xbs.business.service.base.intf.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author cheng
 * @Date1/2/21 3:19 PM
 * @Version V1.0
 **/
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public IPage<Menu> getPageList(String name,Integer pageIndex, Integer pageSize) {

        Page page = new Page(pageIndex,pageSize);
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotEmpty(name)){
            queryWrapper.like(Menu::getName,name);
        }
        return menuMapper.selectPage(page,queryWrapper);
    }
}
