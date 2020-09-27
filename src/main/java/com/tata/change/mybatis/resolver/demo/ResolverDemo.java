package com.tata.change.mybatis.resolver.demo;

import java.lang.reflect.Field;

public class ResolverDemo {
    private static String tableName;
    private String fieldName;
    private Field field;

    public static String getTableName() {
        return tableName;
    }

    public static void setTableName(String tableName) {
        ResolverDemo.tableName = tableName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
