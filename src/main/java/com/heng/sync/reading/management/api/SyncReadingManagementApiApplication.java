package com.heng.sync.reading.management.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.heng.sync.reading.management.api.mapper")
public class SyncReadingManagementApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SyncReadingManagementApiApplication.class, args);
    }
}
