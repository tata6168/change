package com.tata.change.lucene.configuration;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.management.MXBean;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Enumeration;
@Component
public class LuceneUtil {
    @Autowired
    @Qualifier("FSDirectory")
    public FSDirectory directory;
    IndexWriter indexWriter;
    @Bean("FSDirectory")
    public FSDirectory getDirectory(@Value("${lucene.directory}") String path){
        FSDirectory open = null;
        try {
            Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(path);
            if(resources.hasMoreElements()) {
                String path1 = resources.nextElement().getPath().replace('/','\\');
                open = FSDirectory.open(Paths.get(path));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return open;
    }
    //@param 是否需要添加分词
    public IndexWriter getIndexWriter(boolean sign) {
        synchronized (directory) {

            if (indexWriter == null) {
                try {
                    if(sign){
                        indexWriter = new IndexWriter(directory,new IndexWriterConfig(new SimpleAnalyzer()));
                    }else {
                        indexWriter = new IndexWriter(directory,new IndexWriterConfig());
                    }
                    return indexWriter;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                while (indexWriter.isOpen()) {
                    try {
                        directory.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    if(sign){
                        indexWriter = new IndexWriter(directory,new IndexWriterConfig(new SimpleAnalyzer()));
                    }else {
                        indexWriter = new IndexWriter(directory,new IndexWriterConfig());
                    }
                    return indexWriter;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }
    }
    public void close(){
        synchronized (directory){
            try {
                indexWriter.close();
                //释放锁
                directory.notifyAll();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
