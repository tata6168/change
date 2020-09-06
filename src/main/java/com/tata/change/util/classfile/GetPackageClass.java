package com.tata.change.util.classfile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

//获取指定包下的所有类
/*
* 需要包名如  com.synthesize.util.classfile
* */
public enum GetPackageClass {
    INSTANCE;
    //递归目录标志
    boolean sign = false;
    public List<Class> getClasses(String packageName,boolean sign){
        this.sign = sign;
        String packagePath = packageName.replace('.','/');
        List<Class> classList = new ArrayList<>();
        return invoke(packageName,packagePath,classList);
    }
    private List<Class> invoke(String packageName,String packagePath,List<Class> classList){
        try {
            Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(packagePath);

            while (resources.hasMoreElements()){
                URL url = resources.nextElement();
                String type = url.getProtocol();
                if(type=="file"){
                    String path = url.getPath();
                    File file = new File(path);
                    File[] files = file.listFiles();
                    for (File f : files) {
                        String name = f.getName();
                        if(f.isDirectory()&&this.sign){
                            this.invoke(packageName + "." + name,packagePath+"/"+name,classList);
                        }else {
                            if(name.endsWith(".class")){
                                name = name.substring(0,name.lastIndexOf("."));
                                classList.add(Class.forName(packageName+"."+name));
                            }
                        }
                    }
                    //递归出口
                    return classList;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return classList;
    }
}
