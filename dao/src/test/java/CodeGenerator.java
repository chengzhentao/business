import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.List;

public class CodeGenerator {

    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        globalConfig(mpg,projectPath);
        // 数据源配置
        dataSourceConfig(mpg);
        // 包配置
        packageConfig(mpg);
        // 自定义配置
        injectionConfig(mpg,projectPath);
        // 配置模板
        templateConfig(mpg);
        // 策略配置
        strategyConfig(mpg);
        //模版引擎设置
        mpg.execute();
    }


    private static void globalConfig(AutoGenerator mpg,String projectPath) {
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath + Constant.OUT_PATH);
        gc.setAuthor("czt");
        gc.setOpen(false);
        gc.setFileOverride(true);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);
    }

    private static void dataSourceConfig(AutoGenerator mpg) {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(Constant.URL);
        dsc.setDriverName(Constant.DRIVER);
        dsc.setUsername(Constant.USER_NAME);
        dsc.setPassword(Constant.PASSWORD);
        mpg.setDataSource(dsc);
    }

    private static void packageConfig(AutoGenerator mpg) {
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(Constant.MOULD);
        pc.setParent(Constant.PARENT);
        mpg.setPackageInfo(pc);
    }

    private static void injectionConfig(AutoGenerator mpg,String projectPath) {

        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 如果模板引擎是 velocity 需要与模版引擎设置一致
        String templatePath = Constant.PATH;
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + Constant.MAPPER_PATH + Constant.MOULD
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplateEngine(new VelocityTemplateEngine());

    }


    private static void templateConfig(AutoGenerator mpg) {
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        templateConfig.setController(null);
        templateConfig.setService(null);
        templateConfig.setServiceImpl(null);
        mpg.setTemplate(templateConfig);

    }


    private static void strategyConfig(AutoGenerator mpg) {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(Constant.TABLES.split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(Constant.MOULD + "_");
        mpg.setStrategy(strategy);

    }


}
