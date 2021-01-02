package com.xbs.business.web.base.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Author cheng
 * @Date1/2/21 8:50 PM
 * @Version V1.0
 **/
@Data
public class ResetPasswordRequest implements Serializable {

    private String newPassword;

    private String repetPassword;
}
