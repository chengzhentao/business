package com.xbs.business.web.base.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Author cheng
 * @Date1/3/21 2:07 PM
 * @Version V1.0
 **/
@Data
public class RoleMenuRequest implements Serializable {

    private Integer roleId;

    private String roleName;

    private String menuIds;
}
