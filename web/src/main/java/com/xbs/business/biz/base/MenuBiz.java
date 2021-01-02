package com.xbs.business.biz.base;

import com.xbs.business.service.base.intf.MenuService;
import com.xbs.business.service.base.intf.UserService;
import com.xbs.util.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author cheng
 * @Date1/2/21 3:18 PM
 * @Version V1.0
 **/
@Service
public class MenuBiz {

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    public Result getPageList(String name,Integer pageIndex,Integer pageSize) {

       return  Result.success(menuService.getPageList(name,pageIndex,pageSize));
    }
}
