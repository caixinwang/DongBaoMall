package com.wcx.dongbao.portal.web.advice;

import com.baomidou.kaptcha.exception.*;
import com.wcx.dongbao.common.base.enums.StateCodeEnum;
import com.wcx.dongbao.common.base.result.ResultWrapper;
import io.jsonwebtoken.SignatureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler({SignatureException.class})
    public ResultWrapper signatureException() {
        return ResultWrapper.getBuilder(StateCodeEnum.INVALID_TOKEN).build();
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResultWrapper illegalArgumentException() {
        return ResultWrapper.getBuilder(StateCodeEnum.ILLEGAL_PARAM).build();
    }

    @ExceptionHandler({KaptchaException.class})
    public String kaptchaExceptionException(KaptchaException e) {
        if (e instanceof KaptchaIncorrectException) {
            return "验证码不对";
        } else if (e instanceof KaptchaNotFoundException) {
            return "验证码错误";
        } else if (e instanceof KaptchaRenderException) {
            return "验证码生成失败";
        }
        return "验证码超时";
    }


}
