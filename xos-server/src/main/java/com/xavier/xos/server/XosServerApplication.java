package com.xavier.xos.server;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.xavier.xos"})
@SpringBootApplication
@EnableDubbo
public class XosServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(XosServerApplication.class, args);
    }

}
