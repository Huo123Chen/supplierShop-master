package co.yixiang.app.common.error;

/**
 * @author: Administrator
 * @date: 2022/2/3 14:30
 * @description:
 */

public interface BaseErrorInfoInterface {
    /** 错误码*/
    String getResultCode();

    /** 错误描述*/
    String getResultMsg();
}