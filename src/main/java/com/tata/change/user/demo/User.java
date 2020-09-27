package com.tata.change.user.demo;

import com.tata.change.base.demo.Demo;
import com.tata.change.lucene.annotation.FieldCondition;
import lombok.Data;
import lombok.ToString;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ToString
@Data
public class User extends Demo {
    @FieldCondition(fileName = "userId",type = StringField.class)
    Long userId;
    @FieldCondition(fileName = "userName",type = TextField.class)
    String name;
    String passWord;
    Date registerTime;
    List<Integer> roleId = new ArrayList<>();
}
