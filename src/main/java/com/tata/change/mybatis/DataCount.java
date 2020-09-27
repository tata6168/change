package com.tata.change.mybatis;

import com.tata.change.base.demo.Demo;

import java.util.HashMap;
import java.util.Map;

public class DataCount {
    private static final Map<Class<? extends Demo>,Long> DATACOUNT = new HashMap();
    /*
    * @Param sign true:+ false:-
    * */
    //更新数据
    public static boolean update(Class<? extends Demo> key, boolean sign){
        if(DATACOUNT.containsKey(key)){
            if(sign)
                DATACOUNT.replace(key, DATACOUNT.get(key)+1);
                DATACOUNT.replace(key, DATACOUNT.get(key)-1);
                return true;
        }
        return false;
    }
    //插入数据
    public static void InsertCount(Class<? extends Demo> key,Long count){
        DATACOUNT.put(key,count);
    }

    public static Long get(Class<? extends Demo> key){
        return DATACOUNT.get(key);
    }

}
