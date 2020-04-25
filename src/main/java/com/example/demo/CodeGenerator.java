package com.example.demo;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class CodeGenerator {

        public static void main(String[] args) {
                // TODO Auto-generated method stub
                AutoGenerator mpg = new AutoGenerator();

                //全局配置
                GlobalConfig gc = new GlobalConfig();
                String path = "F:\\workspace\\demo\\src\\main";
                gc.setOutputDir(path+"/java");
                //gc.setOutputDir(System.getProperty("user.dir")+"/src/main/java");
                gc.setFileOverride(true);
                //不需要ActiveRecord特性的请改为false
                //gc.setActiveRecord(true);
                gc.setActiveRecord(false);
                gc.setSwagger2(true);
                gc.setAuthor("Agus");


                //自定义文件命名，注意%s 会自动填充表实体属性
                gc.setControllerName("%sController");
                gc.setServiceName("%sService");
                gc.setServiceImplName("%sServiceImpl");
                gc.setEntityName("%s");
                gc.setMapperName("%sDao");

                mpg.setGlobalConfig(gc);

                //数据源配置
                DataSourceConfig dsc = new DataSourceConfig();
                dsc.setDbType(DbType.MYSQL);
                dsc.setTypeConvert(new MySqlTypeConvert() {
                        @Override
                        public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                                System.out.println("转换类型：" + fieldType);
                                // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。

                                if (fieldType.equals("datetime"))
                                        return DbColumnType.DATE;
                                else
                                        return (DbColumnType) super.processTypeConvert(globalConfig,fieldType);
                        }
                });
                dsc.setDriverName("com.mysql.cj.jdbc.Driver");
                //dsc.setUsername("root");
                //dsc.setPassword("8Z4Fby5CV!KQXe5a");
//                dsc.setUrl("jdbc:mysql://47.107.83.120:3306/utopa_dev_crm?serverTimezone=UTC&useSSL=false&useUnicode=true&autoReconnect=true&characterEncoding=utf8&allowMultiQueries=true");
                dsc.setUrl("jdbc:mysql://localhost:3306/demo?characterEncoding=UTF8&allowMultiQueries=true&serverTimezone=UTC");
                dsc.setUsername("root");
                dsc.setPassword("root");
                mpg.setDataSource(dsc);
                //策略配置
                StrategyConfig strategy = new StrategyConfig();
                //此处可以修改您的表前缀
                strategy.setTablePrefix(new String[]{});
                //表名生成策略
                strategy.setNaming(NamingStrategy.underline_to_camel);
                strategy.setColumnNaming(NamingStrategy.underline_to_camel);
                //需要生成的表
                strategy.setInclude(new String[]{
                "demo_import"
                });

                strategy.setSuperServiceClass(null);
                strategy.setSuperServiceImplClass(null);
                //strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
                strategy.setSuperMapperClass(null);
                strategy.setControllerMappingHyphenStyle(true);

                strategy.setTablePrefix("");

                strategy.setRestControllerStyle(false);

                //strategy.setEntityLombokModel(false);
                strategy.setEntityLombokModel(true);
                strategy.setEntitySerialVersionUID(true);
                strategy.setEntityTableFieldAnnotationEnable(true);

                mpg.setStrategy(strategy);
                // 配置模板
                TemplateConfig templateConfig = new TemplateConfig();
                templateConfig.setXml(null);
//                templateConfig.setController(null);
                //templateConfig.setService(null);
                //templateConfig.setServiceImpl(null);
                mpg.setTemplate(templateConfig);

                //包配置
                PackageConfig pc = new PackageConfig();
                pc.setParent("com.example.demo");
                pc.setController("controller");
                pc.setService("service");
                pc.setServiceImpl("service.impl");
                pc.setMapper("dao");
                pc.setEntity("entity");
                mpg.setPackageInfo(pc);

                //执行生成
                mpg.execute();

        }

}
