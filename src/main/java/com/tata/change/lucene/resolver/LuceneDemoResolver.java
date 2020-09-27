package com.tata.change.lucene.resolver;

import com.tata.change.base.demo.Demo;
import com.tata.change.lucene.annotation.FieldCondition;
import com.tata.change.lucene.resolver.demo.ResolverDemo;
import com.tata.change.lucene.resolver.resolvermap.FieldOverViewMap;
import com.tata.change.util.classfile.GetPackageClass;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LuceneDemoResolver {
    public void resolve(Class<? extends Demo> c){
        int sign = 0;
        for (Field f : c.getDeclaredFields()) {
            FieldCondition a = f.getDeclaredAnnotation(FieldCondition.class);
            if(a==null) continue;
            org.apache.lucene.document.Field.Store store = a.store();
            String fileName = a.fileName();
            Class<? extends org.apache.lucene.document.Field> type = a.type();

            try {
                //只存储不做查询条件
                if(type.equals(StoredField.class)){
                    Constructor<? extends org.apache.lucene.document.Field> cons = type.getDeclaredConstructor(String.class, String.class);
                    add(store,c,f,fileName,cons,1);
                }else if(type.equals(StringField.class) || type.equals(TextField.class)){
                    Constructor<? extends org.apache.lucene.document.Field> cons = type.getDeclaredConstructor(String.class, String.class, org.apache.lucene.document.Field.Store.class);
                    sign=2;
                    if(store.equals(org.apache.lucene.document.Field.Store.YES))
                        sign=3;
                    add(store,c,f,fileName,cons,sign);
                }else {

                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

        }
    }
    private void add(org.apache.lucene.document.Field.Store store,Class<? extends Demo> c, Field field,String fieldName ,Constructor<?> constructor, int sign){
        Map<Class<? extends Demo>, List<ResolverDemo>> map1 = FieldOverViewMap.getWriterMap();
        ArrayList<Map<Class<? extends Demo>, List<ResolverDemo>>> objects = new ArrayList<>();
        objects.add(FieldOverViewMap.getWriterMap());
        boolean b = false;
        if(sign==3)
            b=true;
        if(sign==1||b)
            objects.add(FieldOverViewMap.getSearchMap());
        if(sign==2||b)
        objects.add(FieldOverViewMap.getQueryMap());
        ResolverDemo demo = new ResolverDemo();
        if(fieldName.equals("*")){
            fieldName = field.getName();
        }
        demo.setFieldName(fieldName);
        demo.setFieldType(field);
        demo.setStore(store);
        for (Map<Class<? extends Demo>, List<ResolverDemo>> map : objects) {
            if(map.containsKey(c)){
                map1.get(c).add(demo);
                map.get(c).add(demo);
            }else {
                List<ResolverDemo> resolverDemos = new ArrayList<>();
                resolverDemos.add(demo);
                map1.put(c,resolverDemos);
                map.put(c,resolverDemos);
            }
        }
    }
}
