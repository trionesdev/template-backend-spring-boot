package com.trionesdev.wms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = {"com.trionesdev.wms.*.domains.*.dao.mapper"})
public class ServeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServeApplication.class, args);
    }
}
