package com.tata.change.lucene.resolver;

import com.tata.change.lucene.annotation.FieldCondition;
import com.tata.change.lucene.resolver.demo.ResolverDemo;
import com.tata.change.lucene.resolver.resolvermap.FieldOverViewMap;
import com.tata.change.util.classfile.GetPackageClass;
import org.apache.lucene.document.LongPoint;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LuceneDemoResolver {
    public void resolve(String demoPackage){
        // Map  key添加标志
        boolean sign = true;
        List<Class> demoClasses = GetPackageClass.INSTANCE.getClasses(demoPackage, false);
        Iterator<Class> iterator = demoClasses.iterator();
        while (iterator.hasNext()){
            Class e = iterator.next();
            for (Field f : e.getDeclaredFields()) {
                f.setAccessible(true);
                FieldCondition fieldCondition = f.getDeclaredAnnotation(FieldCondition.class);
                if(fieldCondition != null){
                    if(sign){
                        sign=false;
                        FieldOverViewMap.getSearchMap().put(e,new ArrayList<>());
                        FieldOverViewMap.getWriterMap().put(e,new ArrayList<>());
                    }
                    ResolverDemo resolver = new ResolverDemo();
                    List<ResolverDemo> list = FieldOverViewMap.getSearchMap().get(e);
                    List<ResolverDemo> list1 = FieldOverViewMap.getWriterMap().get(e);
                    Class<? extends org.apache.lucene.document.Field> type = fieldCondition.type();
                    //字段名字
                    resolver.setFieldName(f.getName());
                    //字段是否存储
                    resolver.setStore(fieldCondition.store());
                    //字段的reflect.Field对象
                    resolver.setFieldType(f);
                    //如果此字段不存储就不用放到查询Map中
                    if(fieldCondition.store()!= org.apache.lucene.document.Field.Store.NO)
                        list.add(resolver);
                    //...Point 构造方法没有Store对象所以设置为null
                    if(type == LongPoint.class) {
                        resolver.setStore(null);
                    }
                    resolver.setStore(fieldCondition.store());
                    list1.add(resolver);
                }
            }
            sign = true;
        }
    }
}
