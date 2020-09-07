package com.tata.change.base.service;

import com.tata.change.base.demo.Demo;
import com.tata.change.util.Query;
import com.tata.change.util.result.ResultJson;

import java.util.List;
import java.util.Map;

public interface Service<T extends Demo> {
    void baseInsert(T t);
    T baseSearchById(Long id);
    void baseUpdate(Map param);
    void baseDeleteById(Long id);
    List<T> baseSearchAll();
    ResultJson query(Query<T> query);
}
