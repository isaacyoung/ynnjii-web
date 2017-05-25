package com.ynnjii.utils;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * project：ynnjii-web
 * class：加密
 * author：isaac
 * date：2017/5/25
 * description：
 */
public class EncodePwdUtils {

    /**
     *
     * @param username 用户名
     * @param password 明文密码
     * @return param1 ：salt，param2：加密密码
     */
    public static String[] getsaltAndPwd(String username, String password){
        String algorithmName = "md5";

        String salt1 = username;
        String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
        int hashIterations = 2;

        SimpleHash hash = new SimpleHash(algorithmName, password, salt1 + salt2, hashIterations);
        String encodedPassword = hash.toHex().toUpperCase();
        return new String[]{
                salt1+salt2,
                encodedPassword,
        };
    }

    /**
     *
     * @param password 原文密码
     * @param salt 盐
     * @return 加密密码
     */
    public static String getEncryptPwd(String password,String salt){
        String algorithmName = "md5";
        int hashIterations = 2;
        SimpleHash hash = new SimpleHash(algorithmName, password, salt, hashIterations);
        return hash.toHex().toUpperCase();
    }
}
