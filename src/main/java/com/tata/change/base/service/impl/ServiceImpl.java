package com.tata.change.base.service.impl;

import com.tata.change.base.demo.Demo;
import com.tata.change.base.mapper.BaseMapper;
import com.tata.change.base.service.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class ServiceImpl<T extends Demo> implements Service<T> {
    BaseMapper<T> baseMapper;
    @Override
    @Transactional
    public void baseInsert(T t) {
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
}
