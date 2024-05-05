package com.trionesdev.backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = {"ms.triones.backend.*.domains.*.dao.mapper"})
public class ServeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServeApplication.class, args);
    }
}
