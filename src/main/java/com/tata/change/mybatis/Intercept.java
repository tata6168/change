package com.tata.change.mybatis;

import com.tata.change.base.demo.Demo;
import com.tata.change.base.demo.Query;
import com.tata.change.user.demo.User;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Map;


@Component
@Intercepts({
        @Signature(type = StatementHandler.class,method = "prepare",args = {Connection.class,Integer.class}),
})
public class Intercept implements Interceptor {
    private static Field pageSize = null;
    private static Field currentPage =null;
    private static Field sql = null;
    static{
        try {
            pageSize = Query.class.getDeclaredField("pageSize");
            pageSize.setAccessible(true);
            currentPage = Query.class.getDeclaredField("currentPage");
            currentPage.setAccessible(true);
            sql = BoundSql.class.getDeclaredField("sql");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        boolean sign = true;
        //根据参数类型判断是否需要拦截
        for (Object o : invocation.getArgs()) {
            if(o instanceof Connection || o instanceof Integer ){
                sign = false;
            }
        }
        if(sign) return invocation.proceed();

        StatementHandler handler = (StatementHandler) invocation.getTarget();
        Object parameterObject = handler.getParameterHandler().getParameterObject();
        //根据Mapper传入的参数判断是否需要拦截
        if(parameterObject instanceof Query) {
            Long c = (Long)currentPage.get(parameterObject);
            Long p = (Long)pageSize.get(parameterObject);
            //判断分页数据是否为空
            if(c!=null&&p!=null) {
//                MetaObject metaObject = MetaObject.forObject(handler,
//                        SystemMetaObject.DEFAULT_OBJECT_FACTORY,
//                        SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,
//                        new DefaultReflectorFactory());
//                MappedStatement statement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
                BoundSql boundSql = handler.getBoundSql();
                String ssql = boundSql.getSql();
                String page = " LIMIT "+c+","+p+";";
                if(ssql.lastIndexOf(";")!=-1){
                    ssql= ssql.substring(0,ssql.length()-1)+page;
                }else {
                    ssql = ssql+page;
                }
                sql.setAccessible(true);
                sql.get(boundSql);
                sql.set(boundSql,ssql);
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }
}
