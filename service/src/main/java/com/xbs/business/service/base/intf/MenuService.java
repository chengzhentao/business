package com.xbs.business.service.base.intf;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xbs.business.dao.base.entity.Menu;

/**
 * @Description
 * @Author cheng
 * @Date1/2/21 3:19 PM
 * @Version V1.0
 **/
public interface MenuService {

    /**
     *
     * @param name
     * @param pageIndex
     * @param pageSize
     * @return
     */
    IPage<Menu> getPageList(String name,Integer pageIndex,Integer pageSize);
}
