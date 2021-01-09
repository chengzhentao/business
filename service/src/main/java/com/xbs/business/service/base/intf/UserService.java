package com.xbs.business.service.base.intf;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xbs.business.dao.base.entity.User;
import com.xbs.business.service.base.vo.UserVO;

public interface UserService {

    /**
     *
     * @param name
     * @param pageIndex
     * @param pageSize
     * @return
     */
    IPage<UserVO> getPageList(String name, Integer pageIndex, Integer pageSize);

    /**
     *
     * @param userName
     * @return
     */
    User getUserByUserName(String userName);

    /**
     *
     * @param userName
     * @param newPassword
     */
    void resetPassword(String userName,String newPassword);
}
