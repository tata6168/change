package com.tata.change.mybatis.resolver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisDemoResolver {
    @Value("${mybatis.type-aliases-package}")
    public String[] paths;


}
