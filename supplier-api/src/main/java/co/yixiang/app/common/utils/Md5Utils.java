package co.yixiang.app.common.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author: Administrator
 * @date: 2022/2/3 15:48
 * @description:
 */
public class Md5Utils {
    public static  String encryptPassword(String loginName, String password, String salt)
    {
        return new Md5Hash(loginName + password + salt).toHex();
    }
}
