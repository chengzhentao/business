package com.xbs.business.service.base.intf.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.xbs.business.dao.base.entity.User;
import com.xbs.business.dao.base.mapper.UserMapper;
import com.xbs.business.service.base.intf.UserService;
import com.xbs.business.service.base.vo.UserVO;
import com.xbs.util.base.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserVO> getUserList() {
        log.info("查询用户的列表信息");
        Wrapper<User> qw = new QueryWrapper<>();
        List<User> users = userMapper.selectList(qw);
        List<UserVO> userVOS = Lists.newArrayListWithCapacity(users.size());

        users.forEach(user -> {
            userVOS.add(UserVO.builder().build());
        });
        return userVOS;
    }

    @Override
    public User getUserByUserName(String userName) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserName,userName);
        return userMapper.selectOne(lambdaQueryWrapper);
    }

    @Override
    public UserVO getUserByUserName(String userName,String password) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserName,userName);
        lambdaQueryWrapper.eq(User::getPassword, password);
        lambdaQueryWrapper.eq(User::getStatus, Constant.EFFECT);
        User user = userMapper.selectOne(lambdaQueryWrapper);
        if(user == null){
            return null;
        }
        return UserVO.builder()
                .phone(user.getPhone())
                .id(user.getId())
                .build();
    }
}
