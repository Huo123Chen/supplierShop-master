/**
 * Copyright (C) 2018-2021
 * All rights reserved, Designed By www.yixiang.co
 * 注意：本软件为www.yixiang.co开发研制
 */
package co.yixiang.app.modular.auth.controller;


import co.yixiang.app.common.R;
import co.yixiang.app.common.cache.CacheNames;
import co.yixiang.app.common.persistence.model.StoreMember;
import co.yixiang.app.common.utils.CommonEnum;
import co.yixiang.app.common.utils.JwtUtils;
import co.yixiang.app.common.utils.Md5Utils;
import co.yixiang.app.common.utils.RedisUtil;
import co.yixiang.app.modular.member.service.IMemberService;
import co.yixiang.app.modular.member.service.vo.LoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;


import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
/**
 * @ClassName 登陆授权
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/6/27
 **/
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "认证授权模块", tags = "认证授权模块", description = "认证授权模块")
public class AuthController {
    @Autowired
    private IMemberService memberService;
    @Autowired
    private JwtUtils jwtUtils;

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;
    /**
     * 验证码生成
     */
    @GetMapping(value = "/captchaImage")
    public ModelAndView getKaptchaImage(HttpServletRequest request, HttpServletResponse response)
    {
        ServletOutputStream out = null;
        try
        {
            HttpSession session = request.getSession();
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");
            response.setContentType("image/jpeg");

            String type = request.getParameter("type");
            String capStr = null;
            String code = null;
            BufferedImage bi = null;
            if ("math".equals(type))
            {
                String capText = captchaProducerMath.createText();
                capStr = capText.substring(0, capText.lastIndexOf("@"));
                code = capText.substring(capText.lastIndexOf("@") + 1);
                bi = captchaProducerMath.createImage(capStr);
            }
            else if ("char".equals(type))
            {
                capStr = code = captchaProducer.createText();
                bi = captchaProducer.createImage(capStr);
            }
            session.setAttribute(Constants.KAPTCHA_SESSION_KEY, code);
            out = response.getOutputStream();
            ImageIO.write(bi, "jpg", out);
            out.flush();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (out != null)
                {
                    out.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Autowired
    private RedisUtil redisUtil;

    private RedisTemplate<String, Object> redisTemplate;

//    private final SecurityManager securityManager;


//    @PostMapping("/authCode")
//    @ApiOperation(value = "获取验证码",notes = "获取验证码")
//    public R authCode(@Validated @RequestBody LoginVO loginVO) {
//
//        final String token = "123456";
//        String userName = loginVO.getUserName();
//
//
//
//
//        //String token ="";
//        HashMap<String,String> map = new HashMap<>();
//        map.put("access_token",token);
//        return R.success(map);
//    }

//    @GetMapping("/captcha")
//    @ApiOperation(value = "获取验证码")
//    public R captcha() {
//        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
//        String verCode = specCaptcha.text().toLowerCase();
//        String key = UUID.randomUUID().toString();
//        // 存入redis并设置过期时间为2分钟
//        redisUtil.set(CacheNames.CAPTCHA_KEY + key, verCode, 2L, TimeUnit.MINUTES);
//        // 将key和base64返回给前端
//        return R.data(Kv.init().set("key", key).set("image", specCaptcha.toBase64()));
//    }

  /*  *//**
     * 小程序登陆，颁发token，暂时模拟openid
     *
     * @return token字符串
     *//*
    @PostMapping("/oauth/userToken")
    @ApiOperation(value = "API用户获取令牌",notes = "API用户获取令牌")
    public R apiloginReturnToken(@Validated @RequestBody LoginVO loginVO) {
        Boolean isProduct = false; //true 开启真实小程序环境
        String openid = "orIMY4xGhMmipwFZoSL1vOhUNFZ0";
        WxMaUserInfo userInfo = null;
        try{

            StoreMember member = memberService.login(openid);
            //User user = null;
            if(member == null){
                //新用户插入数据
                StoreMember newMember = new StoreMember();
                newMember.setOpenid(userInfo.getOpenId());
                newMember.setNickname(userInfo.getNickName());
                newMember.setHeadimg(userInfo.getAvatarUrl());
                newMember.setSex(userInfo.getGender());
                newMember.setCreateTime(new Date());
                memberService.save(newMember);
                member = newMember;
            }

//            final TokenProfile profile = new TokenProfile();
//            profile.setId(member.getId().toString());
//            profile.setUsername(member.getNickname());
//            profile.addRole("user_role");

//            final String token = securityManager.login(profile);

            final String token = "123456";

            //String token ="";
            HashMap<String,String> map = new HashMap<>();
            map.put("access_token",token);
            return R.success(map);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return R.error(4000,e.getMessage());
        }
    }*/


    /*
     * @return token字符串
     * */
    @PostMapping("/oauth/login")
    @ApiOperation(value = "登录",notes = "登录")
    public R loginReturnToken(@Validated @RequestBody LoginVO loginVO) {
        String token = null;
        try{
            //todo XIAO验证码
            StoreMember member = memberService.login(loginVO);
            if (member != null){

                String pass = Md5Utils.encryptPassword(member.getNickname(), loginVO.getUserPass(),member.getSalt());

                if (pass.equals(member.getPassword())){
                    //密码通过 产生token
                    token = jwtUtils.createToken(member.getId()+"");
                    //新token初始化
                    redisUtil.set(token, member.getId());
                    redisUtil.set("userJwtToken_"+member.getId(), token);
                    redisUtil.hincr(CacheNames.USER_ONLINE_NUM,"userOnline",1d);
                    //记录token
                    //用户在线人数添加
                }else{
                    return R.error(CommonEnum.ACCOUNT_PASSS_ERRO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return R.error(CommonEnum.LOGIN_ERROR);
        }
        HashMap<String,String> map = new HashMap<>();
        map.put("access_token",token);
        return R.success(map);

    }
}