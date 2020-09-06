package com.tata.change.lucene.annotation;

import org.apache.lucene.document.Field;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldCondition {
    Class<? extends Field> type();
    Field.Store store() default Field.Store.YES;
}
