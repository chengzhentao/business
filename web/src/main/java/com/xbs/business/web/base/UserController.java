package com.xbs.business.web.base;

import com.xbs.business.biz.base.UserBiz;
import com.xbs.business.service.base.vo.UserVO;
import com.xbs.business.web.base.request.LoginRequest;
import com.xbs.util.base.ErrorMessage;
import com.xbs.util.base.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserBiz userBiz;

    @GetMapping("/list")
    public Result list(){
        return Result.success(userBiz.getUserList());
    }

    /**
     * 登陆成功，返回改用户登陆菜单
     * @param loginRequest
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest loginRequest){

        if(StringUtils.isAnyEmpty(loginRequest.getUserName(),loginRequest.getPassword())){
            return Result.error(ErrorMessage.PHONE_COMFIRM_ERROR);
        }
        UserVO user = userBiz.getUserByUserName(loginRequest.getUserName(),loginRequest.getPassword());
        if(user == null){
            return Result.error(ErrorMessage.PHONE_COMFIRM_ERROR);
        }

        return userBiz.login(loginRequest.getUserName(),loginRequest.getPassword());
    }


}
