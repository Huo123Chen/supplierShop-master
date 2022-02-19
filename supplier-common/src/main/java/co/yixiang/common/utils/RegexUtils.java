package co.yixiang.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: Administrator
 * @date: 2022/2/4 12:17
 * @description:
 */
public class RegexUtils {


    //用正则表达式判断字符串是否为数字（含负数）
    public static boolean isNumeric(String str) {
        String regEx = "^-?[0-9]+$";
        Pattern pat = Pattern.compile(regEx);
        Matcher mat = pat.matcher(str);
        if (mat.find()) {
            return true;
        }
        else {
            return false;
        }
    }

}
