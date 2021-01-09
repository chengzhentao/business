package com.xbs.business.web.base.response;

import com.google.common.collect.Lists;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @Author cheng
 * @Date1/9/21 1:33 PM
 * @Version V1.0
 **/
@Data
public class UserMenuResponse implements Serializable {

    private Integer id = 0;

    private String name;

    private List<UserMenuResponse> userMenuResponseList = Lists.newArrayList();

}
