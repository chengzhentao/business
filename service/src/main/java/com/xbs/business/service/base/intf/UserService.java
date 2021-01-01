package com.xbs.business.service.base.intf;

import com.xbs.business.dao.base.entity.User;
import com.xbs.business.service.base.vo.UserVO;

import java.util.List;

public interface UserService {

    List<UserVO> getUserList();

    User getUserByUserName(String userName);

}
