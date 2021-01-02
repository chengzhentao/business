package com.xbs.business.biz.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xbs.business.dao.base.entity.User;
import com.xbs.business.service.base.intf.UserService;
import com.xbs.business.service.base.vo.UserVO;
import com.xbs.business.service.config.shiro.ShiroConfig;
import com.xbs.business.service.config.shiro.ShiroUtil;
import com.xbs.util.base.ErrorMessage;
import com.xbs.util.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 *
 * @author czt
 */
@Service
@Slf4j
public class UserBiz {

    @Autowired
    private UserService userService;

    public IPage<UserVO> getPageList(String name, Integer pageIndex, Integer pageSize) {

        return userService.getPageList(name,pageIndex,pageSize);
    }

    public Result login(String userName, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
        try {
            SecurityUtils.getSubject().login(token);
        } catch (AuthenticationException e) {
            log.error("登陆失败",e);
            return Result.error(ErrorMessage.USERNAME_ERROR);
        }
        Subject subject = SecurityUtils.getSubject();
        Serializable tokenId = subject.getSession().getId();
        return Result.success(tokenId);


    }

    public Result reset(String newPassword) {
        String userName = ShiroUtil.currentUser();
        try {
            SecurityUtils.getSubject().logout();
        } catch (AuthenticationException e) {
            log.error("修改密码失败",e);
            return Result.error(ErrorMessage.USERNAME_ERROR);
        }
        userService.resetPassword(userName,ShiroUtil.generatePwdEncrypt(newPassword));
        return Result.success();
    }
}
