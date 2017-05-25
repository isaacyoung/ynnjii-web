package com.ynnjii.utils

import org.apache.shiro.crypto.SecureRandomNumberGenerator
import org.apache.shiro.crypto.hash.SimpleHash
import spock.lang.Specification

/**
 * project：ynnjii-web
 * class：
 * author：isaac
 * date：2017/5/25
 * description：
 */
class EncodePwdUtilsTest extends Specification {

    def "getEncryptPwd1"() {
        when:
        String algorithmName = "md5";
        String username = "guoderong";
        String password = "123456";
        String salt1 = username;
        String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
        int hashIterations = 2;

        SimpleHash hash = new SimpleHash(algorithmName, password, "guoderong9ff1aa721e0b362a471e56fbf82e60c2", hashIterations);
        System.out.println(salt1+salt2);
        String encodedPassword = hash.toHex().toUpperCase();
        System.out.println(encodedPassword);

        then:
        1 == 1
    }
}
