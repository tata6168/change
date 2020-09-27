package com.tata.change.lucene.resolver.resolvermap;

import com.tata.change.base.demo.Demo;
import com.tata.change.lucene.resolver.demo.ResolverDemo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldOverViewMap {
    //写入字段
    private final static Map<Class<? extends Demo>, List<ResolverDemo>> writerMap = new HashMap<>();
    //查询返回数据
    private final static Map<Class<? extends Demo>, List<ResolverDemo>> searchMap = new HashMap<>();
    //查询条件
    private final static Map<Class<? extends Demo>, List<ResolverDemo>> queryMap = new HashMap<>();

    public static Map<Class<? extends Demo>, List<ResolverDemo>> getQueryMap() {
        return queryMap;
    }

    public static Map<Class<? extends Demo>, List<ResolverDemo>> getWriterMap() {
        return writerMap;
    }

    public static Map<Class<? extends Demo>, List<ResolverDemo>> getSearchMap() {
        return searchMap;
    }
}
