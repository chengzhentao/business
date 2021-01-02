package com.xbs.business.web.base;

import com.xbs.business.biz.base.MenuBiz;
import com.xbs.util.base.Constant;
import com.xbs.util.base.Result;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author cheng
 * @Date1/2/21 3:16 PM
 * @Version V1.0
 **/
@RestController
@RequestMapping("base/menu")
@RequiresRoles(Constant.ADMIN_ROLE)
public class MenuController {

    @Autowired
    private MenuBiz menuBiz;

    @GetMapping("/list")
    public Result list(@RequestParam(required = false,value ="name") String name,
                       @RequestParam(required = false,value ="pageIndex") Integer pageIndex,
                       @RequestParam(required = false,value ="pageSize") Integer pageSize){

        return menuBiz.getPageList(name,pageIndex,pageSize);
    }

}

