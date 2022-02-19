package co.yixiang.app.modular.member.service.vo;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 商城会员信息
 * </p>
 * @author hupeng
 * @since 2019-06-17
 */
@Data
public class ApiStoreMemberVO  {

    private static final long serialVersionUID = 1L;

    /**
     * vip编号
     */
    private String vipNo;
    /**
     * 会员手机号
     */
    private String phone;
    /**
     * 登录密码
     */
    @NotBlank(message="登录密码不能为空")
    private String password;

    /**
     * 会员昵称
     */
    @NotBlank(message="会员昵称不能为空")
    private String nickname;
    /**
     * 会员头像
     */
    private String headimg;

    /**
     * 性别
     */
    private String sex;
    /**
     * 会员级别
     */
    private Integer level;

    /**
     * 邀请码
     */
    @NotBlank(message="邀请码不能为空")
    private String invitCode;






}
