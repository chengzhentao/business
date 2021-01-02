package com.xbs.business.service.base.intf.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.xbs.business.dao.base.entity.User;
import com.xbs.business.dao.base.mapper.UserMapper;
import com.xbs.business.service.base.intf.UserService;
import com.xbs.business.service.base.vo.UserVO;
import com.xbs.business.service.config.PageUtil;
import com.xbs.util.base.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author czt
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public IPage<UserVO> getPageList(String name, Integer pageIndex, Integer pageSize) {
        log.info("查询用户的列表信息");
        Page page = new Page(pageIndex,pageSize);
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        if(StringUtils.isNotEmpty(name)){
            qw.like(User::getUserName,name);
        }
        IPage<User> userPage = userMapper.selectPage(page,qw);
        if(userPage.getTotal() == 0){
            return PageUtil.buildEmptyPage(page);
        }
        List<User> users = userPage.getRecords();
        List<UserVO> userVOS = Lists.newArrayListWithCapacity(users.size());
        users.forEach(user ->userVOS.add(UserVO.builder()
                .phone(user.getPhone())
                .userName(user.getUserName())
                .id(user.getId())
                .build()));
        return PageUtil.buildPage(page,userVOS);
    }

    @Override
    public User getUserByUserName(String userName) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserName,userName);
        lambdaQueryWrapper.eq(User::getStatus,Constant.EFFECT);
        return userMapper.selectOne(lambdaQueryWrapper);
    }

    @Override
    public void resetPassword(String userName, String newPassword) {

        User user = this.getUserByUserName(userName);
        user.setPassword(newPassword);
        userMapper.updateById(user);
    }
}
