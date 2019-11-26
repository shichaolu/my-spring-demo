package com.example.mbgdemo;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 11:16 2019/11/26
 */
public class Generator {

    public static void main(String[] args) {
        //generatorXmlMapper();
        //generatorSimple();
        generatorDynamicSql();
    }

    private static void generatorXmlMapper() {
        generator("generatorConfig.xml");
    }

    private static void generatorSimple() {
        generator("generatorConfig-simple.xml");
    }

    private static void generatorDynamicSql() {
        generator("generatorConfig-dsql.xml");
    }

    private static void generator(String configFileName) {
        try {
            //MBG 执行过程中的警告信息
            List<String> warnings = new ArrayList<>();

            //当生成的代码重复时，覆盖原代码
            boolean overwrite = true;

            //读取MBG配置文件
            InputStream is = Generator.class.getResourceAsStream("/" + configFileName);
            Configuration config = new ConfigurationParser(warnings).parseConfiguration(is);
            is.close();

            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            //创建 MBG
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            //执行生成代码
            myBatisGenerator.generate(null);
            //输出警告信息
            for (String warning : warnings) {
                System.out.println(warning);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
