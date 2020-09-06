package com.tata.change.base.mapper;

import com.tata.change.base.demo.Demo;

import java.util.List;
import java.util.Map;

public interface BaseMapper<T extends Demo> {
    void baseInsert(T t);
    T baseSearchById(Long id);
    void baseUpdate(Map param);
    void baseDeleteById(Long id);
    List<T> baseSearchAll();
    Long count();
}
