package com.tata.change.lucene.resolver.resolvermap;

import com.tata.change.base.demo.Demo;
import com.tata.change.lucene.resolver.demo.ResolverDemo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldOverViewMap {
    private final static Map<Class<? extends Demo>, List<ResolverDemo>> writerMap = new HashMap<>();
    private final static Map<Class<? extends Demo>, List<ResolverDemo>> searchMap = new HashMap<>();

    public static Map<Class<? extends Demo>, List<ResolverDemo>> getWriterMap() {
        return writerMap;
    }

    public static Map<Class<? extends Demo>, List<ResolverDemo>> getSearchMap() {
        return searchMap;
    }
}
