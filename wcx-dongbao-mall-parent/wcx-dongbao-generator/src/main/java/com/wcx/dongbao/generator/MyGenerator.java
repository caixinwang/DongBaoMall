package com.wcx.dongbao.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MyGenerator {
    public static void main(String[] args) {
        // 创建代码生成器
        AutoGenerator generator = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("D:\\Notes\\DongBao\\wcx-dongbao-mall-parent\\wcx-dongbao-service\\wcx-dongbao-sms\\src\\main\\java"); // 代码输出目录
        gc.setAuthor("caixinwang"); // 作者
        gc.setOpen(false); // 生成后是否打开文件夹
//        gc.setSwagger2(true); // 是否使用Swagger2注解
        gc.setDateType(DateType.ONLY_DATE); // 时间类型
        gc.setFileOverride(false);//开启-关闭 覆盖
        gc.setActiveRecord(false);
        gc.setEnableCache(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        gc.setMapperName("%sMapper");
        generator.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL); // 数据库类型
        dataSourceConfig.setTypeConvert(new MySqlTypeConvert(){});
        dataSourceConfig.setUrl("jdbc:mysql://47.103.40.79:3306/wcx_dongbao_mall?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&failOverReadOnly=false");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver"); // 数据库驱动
        dataSourceConfig.setUsername("root"); // 数据库用户名
        dataSourceConfig.setPassword("YYcx2928999."); // 数据库密码
        generator.setDataSource(dataSourceConfig);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.wcx.dongbao"); // 包名
        pc.setEntity("entity");
        pc.setMapper("dao");
        pc.setXml("dao.mapper");
        pc.setController("controller");
        generator.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel); // 表名映射策略
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel); // 字段名映射策略
        strategyConfig.setEntityLombokModel(true); // 是否使用lombok
        strategyConfig.setLogicDeleteFieldName("is_deleted"); // 逻辑删除字段名
//        strategyConfig.setEntityTableFieldAnnotationEnable(true); // 是否生成字段注解
        strategyConfig.setEntityBooleanColumnRemoveIsPrefix(true); // 去掉Boolean类型字段的is_前缀
        strategyConfig.setRestControllerStyle(false); // 生成@RestController控制器
        strategyConfig.setControllerMappingHyphenStyle(true); // 驼峰转连字符
        strategyConfig.setTablePrefix(""); // 表前缀
        generator.setStrategy(strategyConfig);
        //指定几个要生成的类，而不是全部都生成！！空串代表什么都不生成。
        strategyConfig.setInclude(
                ""
        );

        // 模板配置
        TemplateConfig tc = new TemplateConfig();
//        tc.setController("/template/controller.java");
//        tc.setEntity("/template/entity.java");
//        tc.setService("/template/service.java");
//        tc.setServiceImpl("/template/serviceImpl.java");
        generator.setTemplate(tc);


        // 执行生成代码
        generator.execute();
    }
}
