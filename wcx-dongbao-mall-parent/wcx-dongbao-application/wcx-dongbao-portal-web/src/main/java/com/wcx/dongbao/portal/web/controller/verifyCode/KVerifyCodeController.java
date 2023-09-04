package com.wcx.dongbao.portal.web.controller.verifyCode;

import com.baomidou.kaptcha.Kaptcha;
import com.baomidou.kaptcha.exception.KaptchaException;
import com.baomidou.kaptcha.exception.KaptchaNotFoundException;
import com.wcx.dongbao.common.base.result.DataObject;
import com.wcx.dongbao.portal.web.controller.verifyCode.custom.MyKaptcha;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/k-code")
public class KVerifyCodeController {
    @Autowired
    private MyKaptcha kaptcha;
//    @Autowired
//    private Kaptcha kaptcha;

    @GetMapping("/generator")
    public void generatorCode(HttpServletRequest request,HttpServletResponse response){//生成验证码的接口
        kaptcha.render();
    }

    @GetMapping("/verify")
    public String verify(String verifyCode, HttpServletRequest request) throws KaptchaException {//校验验证码的接口
        boolean pass= false;
        pass = kaptcha.validate(verifyCode,500);
        return pass?"通过":"不通过";
    }


}
