package com.xbs.business.web.base;

import com.xbs.business.biz.base.UserBiz;
import com.xbs.business.service.config.shiro.ShiroUtil;
import com.xbs.business.web.base.request.LoginRequest;
import com.xbs.business.web.base.request.ResetPasswordRequest;
import com.xbs.util.base.ErrorMessage;
import com.xbs.util.base.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author  czt
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserBiz userBiz;

    @GetMapping("/list")
    public Result list(@RequestParam(required = false)String name,
                       @RequestParam(required = false,defaultValue = "1")Integer pageIndex,
                       @RequestParam(required = false,defaultValue = "10")Integer pageSize){
        return Result.success(userBiz.getPageList(name,pageIndex,pageSize));
    }

    /**
     * 登陆成功，返回改用户登陆菜单
     * @param loginRequest
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest loginRequest){

        if(StringUtils.isAnyEmpty(loginRequest.getUserName(),loginRequest.getPassword())){
            return Result.error(ErrorMessage.USERNAME_ERROR);
        }
        return userBiz.login(loginRequest.getUserName(),loginRequest.getPassword());
    }


    /**
     * 登陆成功，返回改用户登陆菜单
     * @param resetPasswordRequest
     * @return
     */
    @PostMapping("/reset")
    public Result reset(@RequestBody ResetPasswordRequest resetPasswordRequest){

       if(!StringUtils.equals(resetPasswordRequest.getNewPassword(),resetPasswordRequest.getRepetPassword())){
           return Result.error(ErrorMessage.PARAM_ERROE);
       }
       return userBiz.reset(resetPasswordRequest.getNewPassword());
    }

    /**
     * 登陆成功，返回改用户登陆菜单
     * @param password
     * @return
     */
    @GetMapping("/password")
    public Result login(@RequestParam(value ="password") String password){

        return Result.success(ShiroUtil.generatePwdEncrypt(password));
    }


}
