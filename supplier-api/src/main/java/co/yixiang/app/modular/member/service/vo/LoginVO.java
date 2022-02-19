package co.yixiang.app.modular.member.service.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginVO {
    //@NotBlank(message = "code必传")
    //todo 验证码
    private String code;

    private String nickname;

    private String userPass;


    //邀请码
    private String invitCode;





}
