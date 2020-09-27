package com.tata.change.base.service.impl;

import com.tata.change.base.demo.Demo;
import com.tata.change.base.mapper.BaseMapper;
import com.tata.change.base.service.Service;
import com.tata.change.mybatis.DataCount;
import com.tata.change.base.demo.Query;
import com.tata.change.util.result.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class ServiceImpl<T extends Demo> implements Service<T> {
    private static Class key;
    {
        if(key==null) {
            String typeName = this.getClass().getGenericSuperclass().getTypeName();
            String genericName = typeName.substring(typeName.indexOf("<") + 1, typeName.lastIndexOf(">"));
            try {
                key = Class.forName(genericName);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    @Autowired
    BaseMapper<T> baseMapper;
    @Override
    @Transactional
    public void baseInsert(T t) {
        System.out.println("baseMapper:--------------"+baseMapper);
        baseMapper.baseInsert(t);
    }

    @Override
    public T baseSearchById(Long id) {
        return baseMapper.baseSearchById(id);
    }

    @Override
    @Transactional
    public void baseUpdate(Map param) {
        baseMapper.baseUpdate(param);
    }

    @Override
    @Transactional
    public void baseDeleteById(Long id) {
        baseMapper.baseDeleteById(id);
    }

    @Override
    public List<T> baseSearchAll() {
        return baseMapper.baseSearchAll();
    }

    @Override
    public ResultJson query(Query<T> query) {
        List<T> data = baseMapper.query(query);
        Long count = DataCount.get(key);
        if(count == null)
            return new ResultJson("request failure");
        return new ResultJson(count,data,"request success");
    }
}
