package com.tata.change.lucene.resolver.demo;

import org.apache.lucene.document.Field;

import java.lang.reflect.Constructor;

public class ResolverDemo {
    private String fieldName;
    private java.lang.reflect.Field fieldType;
    private Constructor<? extends Field> constructor;
    private Field.Store store;

    public Field.Store getStore() {
        return store;
    }

    public void setStore(Field.Store store) {
        this.store = store;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public java.lang.reflect.Field getFieldType() {
        return fieldType;
    }

    public void setFieldType(java.lang.reflect.Field fieldType) {
        this.fieldType = fieldType;
    }

    public Constructor<? extends Field> getConstructor() {
        return constructor;
    }

    public void setConstructor(Constructor<? extends Field> constructor) {
        this.constructor = constructor;
    }
}
