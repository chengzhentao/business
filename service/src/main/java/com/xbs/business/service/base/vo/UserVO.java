package com.xbs.business.service.base.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class UserVO implements Serializable {

    private Integer id;

    private String userName;

    private String nickName;

    private String phone;

    private MenuVO menuVO;

}
