package com.xbs.business.service.base.vo;

import lombok.Data;

import java.util.List;

@Data
public class MenuVO {

    private String name;

    private List<MenuVO> menuVO;
}
