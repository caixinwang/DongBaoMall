package com.wcx.dongbao.portal.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.stream.Collectors;

public class HttpParamUtil {

    public static Map<String,Object> getAllParams(HttpServletRequest request){
        Map<String,Object> ans=new HashMap<>();
        ans.putAll(getUrlParams(request));
        ans.putAll(getBodyParams(request));
        System.out.println("all:"+ans);
        return ans;
    }


    /**
     * 两种方法:第一种是直接使用API，另一种是获取字符串然后自己转化
     * 1.先拿到url字符串
     * 2.拿到?后面的东西，按照&分割
     * 3.放入map中
     * @param request
     * @return
     */
    public static Map<String,Object> getUrlParams(HttpServletRequest request){
        Map<String,Object> ans=new HashMap<>();
//        String queryParams="";
//        try {
//            if (request.getQueryString()==null) return ans;//url上没有参数
//            queryParams=URLDecoder.decode(request.getQueryString(),"utf-8");
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException(e);
//        }
//        String[] params = queryParams.replaceAll("^.*\\?", "").split("&");
//        for (String param : params) {
//            String[] split = param.split("=");
//            ans.put(split[0],split[1]);
//        }
        Enumeration<String> parameterNames = request.getParameterNames();
        while(parameterNames.hasMoreElements()){
            String pn=parameterNames.nextElement();
            String p=request.getParameter(pn);
            ans.put(pn,p);
        }
        System.out.println("params:"+ans);
        return ans;
    }

    /**
     *
     * 1.利用流拿到body（type我们认为是json，拿到json串）,如果是表单我们可以直接调用方法直接获得map
     * 2.用ObjectMapper将json串转化为map
     * @param request HttpServletRequest
     * @return 排序后的Map
     * @throws IOException
     */
    public static Map<String,Object> getBodyParams(HttpServletRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap=new HashMap<>();
        String requestBody;
        try {
//            requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            StringBuilder sb=new StringBuilder();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(request.getInputStream()));
            String s="";
            while((s= bufferedReader.readLine())!=null){
                sb.append(s);
            }
            requestBody=sb.toString();
            jsonMap = objectMapper.readValue(requestBody, Map.class);
        } catch (IOException e) {
        }
        // 使用 Jackson 解析 JSON
        // 将 JSON 对象转换为 Map<String, String>
        System.out.println("body:"+jsonMap);
        return jsonMap;
    }

}
