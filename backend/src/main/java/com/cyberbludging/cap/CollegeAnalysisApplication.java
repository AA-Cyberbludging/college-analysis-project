package com.cyberbludging.cap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
@MapperScan("com.cyberbludging.cap.mapper")
public class CollegeAnalysisApplication {
    public static void main(String[] args) {
        SpringApplication.run(CollegeAnalysisApplication.class, args);
    }
}
