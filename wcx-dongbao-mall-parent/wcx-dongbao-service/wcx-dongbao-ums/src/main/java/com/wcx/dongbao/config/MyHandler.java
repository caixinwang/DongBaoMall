package com.wcx.dongbao.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class MyHandler implements MetaObjectHandler
{
    @Override
    public void insertFill(MetaObject metaObject) {
        //当代码在执行插入的时候Mybatis-plus会帮你自动生成gmtCreate、gmtModified这两个字段的数据。
        System.out.println("增加插入时间");
        this.setFieldValByName("gmtCreate",new Date(),metaObject);
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //当代码在执行更新的时候Mybatis-plus会帮你自动生成gmtModified这个字段的数据。
        System.out.println("增加更新时间");
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }
}
