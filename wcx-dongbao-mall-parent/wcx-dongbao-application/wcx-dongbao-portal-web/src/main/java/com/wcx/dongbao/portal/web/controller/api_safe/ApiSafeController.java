package com.wcx.dongbao.portal.web.controller.api_safe;

import com.wcx.dongbao.common.base.result.DataObject;
import com.wcx.dongbao.common.util.ApiSafeUtil;
import com.wcx.dongbao.portal.web.util.HttpParamUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;

@RestController
@RequestMapping("/api-safe")
public class ApiSafeController {
    @GetMapping("/hello")
    public String hello(){
        return "hello!";
    }

    //这个接口没用了，我们用过滤器来做
    @GetMapping("changed")
    public String changed(String name, String age, String sign, HttpServletRequest request){
        HashMap<String,Object> map=new HashMap<>();
        map.putAll(HttpParamUtil.getBodyParams(request));
        System.out.println("controller:"+map);
        return "";
    }
}
