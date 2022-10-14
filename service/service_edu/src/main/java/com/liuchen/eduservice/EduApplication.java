package com.liuchen.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName: EduApplication
 * @Description:
 * @date: 2022/10/14 16:33
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.liuchen"})
public class EduApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
