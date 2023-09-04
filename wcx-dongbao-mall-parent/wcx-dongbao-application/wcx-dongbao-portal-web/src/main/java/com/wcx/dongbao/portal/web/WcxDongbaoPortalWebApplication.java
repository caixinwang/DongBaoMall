package com.wcx.dongbao.portal.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//scanBasePackages不加的话只会扫描com.wcx.dongbao.portal.web下面的包，因为这个启动类在这个层级
@SpringBootApplication(scanBasePackages = {"com.wcx"})
@MapperScan("com.wcx.dongbao.dao")
public class WcxDongbaoPortalWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WcxDongbaoPortalWebApplication.class,args);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){//这里不配置会报错
        return new BCryptPasswordEncoder();
    }

}
