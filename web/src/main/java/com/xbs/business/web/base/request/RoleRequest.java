package com.xbs.business.web.base.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Author cheng
 * @Date1/3/21 6:24 AM
 * @Version V1.0
 **/
@Data
public class RoleRequest implements Serializable {

    private Integer roleId;

    private String roleName;
}
