package com.wcx.dongbao.portal.web.controller.verifyCode;

import com.ramostear.captcha.HappyCaptcha;
import com.ramostear.captcha.support.CaptchaStyle;
import com.ramostear.captcha.support.CaptchaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/happy-code")
public class HappyVerifyCodeController {



    //开源网站:https://gitee.com/ramostear/Happy-Captcha?_from=gitee_search
    @GetMapping("/generator")
    public void generatorCode(HttpServletRequest request,HttpServletResponse response){//生成验证码的接口
        HappyCaptcha.require(request,response)
                .style(CaptchaStyle.ANIM)
                .type(CaptchaType.ARITHMETIC_ZH)
                .build().finish();
    }

    @GetMapping("/verify")
    public String verify(String verifyCode, HttpServletRequest request){//校验验证码的接口
        boolean pass=HappyCaptcha.verification(request,verifyCode,true);
        if (pass) HappyCaptcha.remove(request);
        return pass?"通过":"不通过";
    }


}
