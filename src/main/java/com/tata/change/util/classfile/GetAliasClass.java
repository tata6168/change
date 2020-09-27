package com.tata.change.util.classfile;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;

public class GetAliasClass {
    ClassLoader classLoader;
    {
        classLoader = Thread.currentThread().getContextClassLoader();
    }
    //前+后缀迭代次数集合
    ArrayList<Integer> keyIt = new ArrayList<>();
    boolean sign = false;
    //后缀集合
    ArrayList<String> suffixAll = new ArrayList<>();
    ArrayList<String> buf = new ArrayList<>();
    ArrayList<String> success = new ArrayList<>();
    int count = -1;
    String replace = "";
    public Class[] getClass(String...pn){
        resolverPackageName(pn);
        Class[] classes = new Class[success.size()];
        for (int i = 0; i < classes.length; i++) {
            try {
                classes[i] = Class.forName(success.get(i));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        success=null;
        return classes;
    }
    private void resolverPackageName(String...pn){
        String prefix = "";
        boolean sign= false;
        for (String ps : pn) {
            if(ps.indexOf("*")==-1){
                suffixAll.add(ps);
                continue;
            }
            try {
                this.replace = ps.substring(0, ps.indexOf("*")).replace('.','\\');
                prefix = replace.replace('\\','/');
                Enumeration<URL> resources = classLoader.getResources(prefix);
                while (resources.hasMoreElements()){
                    URL url = resources.nextElement();
                    if(url.getProtocol().equals("file")){
                        prefix = url.getFile();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            int k = -1;
            int s=0;
            char[] chars = ps.substring(ps.indexOf("*"),ps.length()).toCharArray();
            for (int i = 0; i < chars.length; i++) {
                //关键字迭代次数
                if(chars[i]=='*'){
                    setKeyIt((i-k)==2,true);
                    if (sign) {
                        sign = false;
                    }
                    k = i;
                }else if(chars[i]!='.'&&chars[i-1]=='.'){
                    setKeyIt(sign,false);
                    if(!sign)
                        sign=true;
                }else if(chars[i]=='.'){
                    if(chars[i+1]!='*'&&!sign){
                        s=i+1;
                    } else if(chars[i-1]!='*'){
                        char[] chars1 = Arrays.copyOfRange(chars, s, i);
                        suffixAll.add(String.copyValueOf(chars1));
                        s=i+1;
                    }
                }
                if(i==(chars.length-1)){
                    suffixAll.add(String.copyValueOf(Arrays.copyOfRange(chars, s, chars.length)));
                }
            }
            invoke(prefix);

        }
        keyIt = null;
        suffixAll = null;
        classLoader = null;
    }
    private void setKeyIt(boolean condition,boolean arithmetic){

        int i,s;
        //key
        if (arithmetic){
            i=2;s=1;
            //suffix
        }else {
            i=1;s=2;
        }
        int size = keyIt.size();
        if(condition){
            int index;
            if(size%2==0){
                index = size-i;
            }else {
                index = size-s;
            }
            keyIt.set(index,keyIt.get(index)+1);
        }else {
            keyIt.add(1);
        }
    }
    private void invoke(String prefix){
        if(buf.size()==0) buf.add(prefix);
        FilenameFilter k = new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                if(new File(dir.getPath()+"\\"+name).isDirectory())
                    return true;
                return false;
            }
        };
        FilenameFilter classNameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.endsWith(".class"))
                    return true;
                return false;
            }
        };
        for (int i = 0; i < keyIt.size(); i+=2) {
            Integer ki = keyIt.get(i);
            Integer si = keyIt.get(i + 1);
            for (Integer j = ki; j > 0; j--) {
                ArrayList<String> a = new ArrayList<>();
                for (int i1 = 0; i1 < buf.size(); i1++) {
                    for (File f : new File(buf.get(i1)).listFiles(k)) {
                        a.add(f.getPath());
                    }
                }
                buf = a;
            }
            for (Integer j1 = 0; j1 < si; j1++) {
                //后缀索引
                count++;
                ArrayList<String> a = null;
                for (int i1 = 0; i1 < buf.size(); i1++) {
                    for (File f : new File(buf.get(i1)).listFiles(new FilenameFilter() {



                        @Override
                        public boolean accept(File dir, String name) {
                            if (name.equals(suffixAll.get(count)))
                                return true;
                            return false;
                        }
                    })) {
                        int ib = i;
                        if(i==0)
                            ib = 1;
                        if(ib*2!=keyIt.size()){
                            a=new ArrayList<>();
                            a.add(f.getPath());
                        }else {
                            for (File c : new File(f.getPath()).listFiles(classNameFilter)) {
                                String path = c.getPath();
                                success.add(path.substring(path.indexOf(replace), path.lastIndexOf(".")).replace('\\', '.'));
                            }
                        }
                    }
                }
                buf =a;
            }
        }
    }
}
