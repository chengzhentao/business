package com.xbs.business.service.config.shiro;

/**
 * @author czt
 * @description TODO
 * @date 2020/12/31 4:58 PM
 */

import com.xbs.business.dao.base.entity.User;
import com.xbs.business.service.base.intf.UserService;
import com.xbs.util.base.Constant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class ShiroUtil {

    private static UserService userService;

    private static RedisTemplate<String, Object> redisTemplate;

    public static String generatePwdEncrypt(Object pwd) {
        SimpleHash hash = new SimpleHash(Sha256Hash.ALGORITHM_NAME, pwd,null,1024);
        return hash.toString();
    }

    public static User currentUser(){
         String userName = (String)SecurityUtils.getSubject().getPrincipal();
         if(redisTemplate.hasKey(userName)) {
             return (User) redisTemplate.opsForHash().get(Constant.SESSION,userName);
         }else{
             User user = userService.getUserByUserName(userName);
             redisTemplate.opsForHash().put(Constant.SESSION,userName,user);
             return user;
         }

    }

    @Autowired
    public void setUserService(UserService userService){
        ShiroUtil.userService = userService;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        ShiroUtil.redisTemplate = redisTemplate;
    }


}