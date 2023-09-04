package com.wcx.dongbao.portal.web.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

public class MD5 {

    @Test
    public void testMD5(){
        String password="123456";
        DigestUtils.md5Digest(password.getBytes());
    }

    @Test
    public void testBCrypt(){
        String password="123456";
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        String encode=bCryptPasswordEncoder.encode(password);
        System.out.println(bCryptPasswordEncoder.matches("123", encode));
    }
}
