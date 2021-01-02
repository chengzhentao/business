package com.xbs.business.service.config.shiro;

/**
 * @author czt
 * @description TODO
 * @date 2020/12/31 4:58 PM
 */

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

public class ShiroUtil {

    public static String generatePwdEncrypt(Object pwd) {
        SimpleHash hash = new SimpleHash(Sha256Hash.ALGORITHM_NAME, pwd,null,1024);
        return hash.toString();
    }

    public static String currentUser(){
         return (String)SecurityUtils.getSubject().getPrincipal();

    }

}