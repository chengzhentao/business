package com.xbs.business.web.base;

import com.xbs.business.biz.base.RoleBiz;
import com.xbs.business.web.base.request.RoleMenuRequest;
import com.xbs.business.web.base.request.RoleRequest;
import com.xbs.util.base.Constant;
import com.xbs.util.base.Result;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Author cheng
 * @Date1/2/21 10:42 PM
 * @Version V1.0
 **/
@RestController
@RequestMapping("base/role")
@RequiresRoles(Constant.ADMIN_ROLE)
public class RoleController {

    @Autowired
    private RoleBiz roleBiz;

    @GetMapping("/list")
    public Result list(@RequestParam(required = false,value ="name") String name,
                       @RequestParam(required = false,value ="pageIndex") Integer pageIndex,
                       @RequestParam(required = false,value ="pageSize") Integer pageSize){

        return roleBiz.getPageList(name,pageIndex,pageSize);
    }

    @GetMapping("/add")
    public Result add(@RequestBody RoleRequest roleRequest){

        return roleBiz.save(roleRequest);
    }

    @GetMapping("/edit")
    public Result edit(@RequestBody RoleMenuRequest roleMenuRequest){

        return roleBiz.edit(roleMenuRequest);
    }

}
