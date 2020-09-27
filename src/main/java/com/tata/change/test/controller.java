package com.tata.change.test;

import com.tata.change.base.demo.Demo;
import com.tata.change.shiro.demo.Permission;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

@RestController
@RequestMapping("/test")
public class controller {
    //path "localhost:8080/test/result?path=path&sn=[pn:pn]"
    @GetMapping("/result")
    public Demo result(Permission permission){
        return permission;
    }
    @GetMapping("result2/{name}/{age}")
    public String result2(@PathVariable("name")String name,@PathVariable("age")Long age){
        return name+age;
    }
    @GetMapping("result3/{path}/{sn}")
    public String result2(Permission permission){
        return permission.toString();
    }
    // body raw json
    @PostMapping("/result4")
    public Demo result4(@RequestBody Permission permission){
        return permission;
    }
    //RequestParam x-www-form-urlenecoded  form-data
    @PostMapping("/result5")

    public String result5(@RequestParam("id") Long id, @RequestParam("name")String name, HttpServletRequest request){
        return id+name;
    }
    @PostMapping("/result6/{id}")
    public Long result6(@PathVariable("id") Long id){
        return id;
    }
    @PostMapping("/result7")
    public Long result7(Long id){
        return id;
    }

    @PostMapping("/stream")
    public void stream(HttpServletRequest re){

        try {
            byte[] bytes = new byte[1];
            ServletInputStream inputStream = re.getInputStream();
            while (inputStream.read(bytes)!=-1) {
                System.out.println("///////////////");
                java.lang.String s = new java.lang.String(bytes);
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
