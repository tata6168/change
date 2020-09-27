package com.tata.change.mybatis.resolver.resolvermap;

import com.tata.change.base.demo.Demo;
import com.tata.change.mybatis.resolver.demo.ResolverDemo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldOverviewMap {
    static final Map<Class<? extends Demo>, List<ResolverDemo>> UPDATE = new HashMap<>();
    //Query 对象
    static final Map<Class<? extends Demo>, List<ResolverDemo>> RETRIEVE = new HashMap<>();
}
