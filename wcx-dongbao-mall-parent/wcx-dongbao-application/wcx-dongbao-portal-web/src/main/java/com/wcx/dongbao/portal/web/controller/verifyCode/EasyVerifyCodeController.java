package com.wcx.dongbao.portal.web.controller.verifyCode;

import com.ramostear.captcha.HappyCaptcha;
import com.ramostear.captcha.support.CaptchaStyle;
import com.ramostear.captcha.support.CaptchaType;
import com.wcx.dongbao.common.base.result.DataObject;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/easy-code")
public class EasyVerifyCodeController {

    @GetMapping("/generator")
    public void generatorCode(HttpServletRequest request,HttpServletResponse response){//生成验证码的接口
        try {
//            CaptchaUtil.out(request,response);//基础款
            ArithmeticCaptcha arithmeticCaptcha=new ArithmeticCaptcha();
            arithmeticCaptcha.setLen(3);//三位数的运算
            CaptchaUtil.out(arithmeticCaptcha,request,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/verify")
    public String verify(String verifyCode, HttpServletRequest request){//校验验证码的接口
        boolean pass=CaptchaUtil.ver(verifyCode,request);
        if (pass) CaptchaUtil.clear(request);
        return pass?"通过":"不通过";
    }

    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("/generator-redis")
    public void generatorCode_redis(HttpServletRequest request,HttpServletResponse response){//生成验证码的接口
        SpecCaptcha specCaptcha=new SpecCaptcha(100,50);
        String code=specCaptcha.text();//这就是验证码
        String key=request.getSession().getId();
        redisTemplate.opsForValue().set(key,code);
        redisTemplate.expire(key,30, TimeUnit.SECONDS);//60s过期时间
        try {
            CaptchaUtil.out(specCaptcha,request,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/verify-redis")
    public String verify_redis(String verifyCode, HttpServletRequest request){//校验验证码的接口
        String c=redisTemplate.opsForValue().get(request.getSession().getId());
        boolean pass=c!=null&&c.equalsIgnoreCase(verifyCode);
        if (pass) CaptchaUtil.clear(request);
        return pass?"通过":"不通过";
    }

    //https://tool.jisuapi.com/base642pic.html
    @GetMapping("/generator-redis-base64")
    public Object generatorCode_redis_base64(HttpServletRequest request,HttpServletResponse response){//生成验证码的接口
        SpecCaptcha specCaptcha=new SpecCaptcha(100,50);
        String code=specCaptcha.text();//这就是验证码
        String key= UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(key,code);
        redisTemplate.expire(key,60, TimeUnit.SECONDS);//60s过期时间
        String base64=specCaptcha.toBase64();
        return new DataObject().data("uuid",key).data("base64",base64).build();
    }

    @GetMapping("/verify-redis-base64")
    public String verify_redis_base64(String verifyCode,String uuid, HttpServletRequest request){//校验验证码的接口
        String c=redisTemplate.opsForValue().get(uuid);
        boolean pass=c!=null&&c.equalsIgnoreCase(verifyCode);
        if (pass) CaptchaUtil.clear(request);
        return pass?"通过":"不通过";
    }


}
