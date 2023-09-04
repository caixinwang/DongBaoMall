package com.wcx.dongbao.portal.web.filter;

import com.wcx.dongbao.common.util.ApiSafeUtil;
import com.wcx.dongbao.portal.web.util.CachedBodyHttpServletRequest;
import com.wcx.dongbao.portal.web.util.HttpParamUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class SignAuthFilter implements Filter {
    //1.转成HttpServletRequest
    //2.拿到请求url上的所有参数，urlParams
    //3.拿到请求体上的所有参数,
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException{
        System.out.println("进入过滤器");
//        HttpServletRequest request =(HttpServletRequest) servletRequest;//流吸走body就没东西了
        HttpServletRequest request=new CachedBodyHttpServletRequest( (HttpServletRequest) servletRequest);//复制一份流
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        Map<String, Object> allParams = HttpParamUtil.getAllParams(request);
        if (ApiSafeUtil.checkSign(allParams)){
            System.out.println("通过sign");
            filterChain.doFilter(request,response);
        }else{
            System.out.println("未通过sign");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        System.out.println("销毁");
    }
}
