package com.wcx.dongbao.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    private static final String secret="dasfasfa";//随便写
    public static String createToken(String subject){//refreshToken
        String token = Jwts.builder()
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS256, secret)
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
                .compact();
        return token;
    }
    
    public static String accessToken(String refreshToken){
        String subject=parseToken(refreshToken);
        String n_token = Jwts.builder()
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS256, secret)
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*10))
                .compact();
        return n_token;
    }

    public static String parseToken(String token){
        Claims body = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        String subject=body.getSubject();
        return subject;
    }

    public static void main(String[] args) {
        String name="caixinwang";
        String token = createToken(name);
        System.out.println(token);
        String parse=parseToken(token);
        System.out.println(parse);
    }

}
