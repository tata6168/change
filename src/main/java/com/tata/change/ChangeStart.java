package com.tata.change;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.tata.change.shiro.mapper","com.tata.change.user.mapper"})
public class ChangeStart {
    public static void main(String[] args) {
        SpringApplication.run(ChangeStart.class,args);
    }
}
