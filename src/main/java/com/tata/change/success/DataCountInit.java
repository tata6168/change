package com.tata.change.success;

import com.tata.change.base.demo.Demo;
import com.tata.change.base.mapper.BaseMapper;
import com.tata.change.shiro.demo.Permission;
import com.tata.change.user.demo.User;
import com.tata.change.util.DataCount;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

//boot start success processes
@Component
public class DataCountInit implements ApplicationRunner {
    @Autowired
    ApplicationContext applicationContext;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        //获取实现BaseMapper接口的bean
        ObjectProvider<BaseMapper> provider = applicationContext.getBeanProvider(BaseMapper.class);
        provider.forEach(e->{
            //获取Mapper实现的所有接口
            for (Class<?> aClass : e.getClass().getInterfaces()) {
                //获取接口泛型Type
                for (Type t : aClass.getGenericInterfaces()) {
                    //获取泛型类名
                    String s = t.getTypeName();
                    try {
                        //加载泛型类名的class对象
                        Class<?> demo = Class.forName(s.substring(s.indexOf("<") + 1, s.lastIndexOf(">")));
                        //判断泛型是否继承Demo（规范类）
                        if(Demo.class.isAssignableFrom(demo)){
                            //初始化数据
                            DataCount.InsertCount((Class<? extends Demo>) demo,e.count());
                        }
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
}

