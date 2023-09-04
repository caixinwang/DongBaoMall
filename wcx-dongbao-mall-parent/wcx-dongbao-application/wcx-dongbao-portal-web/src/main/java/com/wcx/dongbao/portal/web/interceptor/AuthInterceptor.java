package com.wcx.dongbao.portal.web.interceptor;

import com.wcx.dongbao.common.base.annotations.TokenCheck;
import com.wcx.dongbao.common.util.JwtUtil;
import io.jsonwebtoken.SignatureException;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入AuthInterceptor拦截器");
        if (!(handler instanceof HandlerMethod)) return true;
        HandlerMethod handlerMethod=(HandlerMethod) handler;//强转，代表的就是加上注解的那个方法
        Method method= handlerMethod.getMethod();
        if (method.isAnnotationPresent(TokenCheck.class)){//如果这个方法加了这个注解
            TokenCheck annotation = method.getAnnotation(TokenCheck.class);
            if (annotation.required()){//在TokenCheck里面默认为true了
                try {
                    String token = request.getHeader("token");
                    if (token==null||token.length()==0) throw new SignatureException("");
                    JwtUtil.parseToken(token);
                    return true;//验证通过
                }catch (Exception e){
                    throw new SignatureException("");
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
