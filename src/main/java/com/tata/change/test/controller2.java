package com.tata.change.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

@RequestMapping("/test1")
@Controller
public class controller2 {
    @RequestMapping(value = "/reader",method = RequestMethod.POST)
    @ResponseBody
    //获取reader 需要重写HttpServletRequest 中的方法 来修改状态 避免illegalException
    public String reader(HttpServletRequest re){
        String s = "";
        try {
            BufferedReader reader = re.getReader();
            char[] c = new char[10];
            while (reader.ready()){
                reader.read(c);
                s = s+String.valueOf(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
}
