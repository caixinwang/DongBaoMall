package com.wcx.dongbao.portal.web.util;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

public class CachedBodyHttpServletRequest extends HttpServletRequestWrapper {
 
    //存储body数据的容器
    private byte[] cachedBody;
 
    public CachedBodyHttpServletRequest(HttpServletRequest request) throws IOException {
        super(request);
        //获取请求流
        InputStream requestInputStream = request.getInputStream();
        //以btye的方式，将其复制缓存起来
        this.cachedBody = StreamUtils.copyToByteArray(requestInputStream);
    }
 
    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new CachedBodyServletInputStream(this.cachedBody);
    }
 
    @Override
    public BufferedReader getReader() throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.cachedBody);
        return new BufferedReader(new InputStreamReader(byteArrayInputStream));
    }
 
}