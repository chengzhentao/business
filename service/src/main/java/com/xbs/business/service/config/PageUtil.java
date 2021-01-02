package com.xbs.business.service.config;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.xbs.business.service.base.vo.UserVO;

import java.util.List;

/**
 * @Description
 * @Author cheng
 * @Date1/2/21 4:18 PM
 * @Version V1.0
 **/
public class PageUtil {

    public static<T> IPage<T> buildPage(IPage page, List<T> records){
        IPage newPage = new Page(page.getCurrent(),page.getSize(),page.getTotal());
        newPage.setRecords(records);
        return newPage;
    }

    public static IPage<UserVO> buildEmptyPage(IPage page) {
        return buildPage(page, Lists.newArrayList());
    }
}
