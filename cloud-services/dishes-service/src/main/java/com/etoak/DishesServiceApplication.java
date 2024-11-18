package com.etoak;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages = "com.etoak.**.mapper")
@EnableTransactionManagement
@EnableFeignClients
public class DishesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DishesServiceApplication.class,args);
    }
}
