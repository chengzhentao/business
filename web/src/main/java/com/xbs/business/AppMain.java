package com.xbs.business;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@MapperScan("com.xbs.business.dao.*.mapper")
public class AppMain {

    public static void main(String[] args) {

        SpringApplication.run(AppMain.class, args);
        log.info("springboot项目启动完成");
    }

}
