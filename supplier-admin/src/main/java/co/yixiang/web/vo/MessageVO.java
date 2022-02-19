package co.yixiang.web.vo;

import java.io.Serializable;

/**
 * @author: Administrator
 * @date: 2022/2/19 12:45
 * @description:
 */
public class MessageVO implements Serializable {

    private static final long serialVersionUID = 1L;
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageVO() {
    }
}
