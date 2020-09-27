package com.tata.change.lucene.service;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.tata.change.base.demo.Demo;
import com.tata.change.lucene.configuration.LuceneUtil;
import com.tata.change.lucene.resolver.demo.ResolverDemo;
import com.tata.change.lucene.resolver.resolvermap.FieldOverViewMap;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;


@Service
public class LuceneService {
    @Autowired
    LuceneUtil util;
    public void writer(Demo demo){
        IndexWriter indexWriter = util.getIndexWriter(true);
        List<ResolverDemo> resolverDemos = FieldOverViewMap.getWriterMap().get(demo.getClass());
        Document document = new Document();
        resolverDemos.forEach(e-> {
            try {
                java.lang.reflect.Field fieldType = e.getFieldType();
                //字段数据获取 判断是否含有数据
                Object o = fieldType.get(demo);
                Field field = null;
                if(o!=null) {
                    String fieldName = e.getFieldName();
                    Field.Store store = e.getStore();

                    if(store!=null){
                        field = e.getConstructor().newInstance(fieldName,o);
                    }else {
                        field = e.getConstructor().newInstance(fieldName,o,store);
                    }
                }
                document.add(field);
                indexWriter.commit();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            } catch (InstantiationException ex) {
                ex.printStackTrace();
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        util.close();
    }
    //输入框查询
    public String[] InputBoxQuery(String fieldName,String field,Integer count){
        try {
            String [] result = null;
            QueryParser parser = new QueryParser(field,new SimpleAnalyzer());
            Query parse = parser.parse(fieldName + ":" + field);
            DirectoryReader reader = DirectoryReader.open(util.directory);
            IndexSearcher searcher = new IndexSearcher(reader);
            if(count==null||count==0)
                count=10;
            ScoreDoc[] docs = searcher.search(parse, count).scoreDocs;
            if(docs.length!=0)
            result = new String[docs.length];
            for (int i = 0; i < docs.length; i++) {
                result[i] = reader.document(docs[i].doc).getField(fieldName).stringValue();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void delete(String fieldName,String field){
        //无分词 Term删除
        IndexWriter writer = util.getIndexWriter(false);
        try {
            writer.deleteDocuments(new Term(fieldName,field));
            util.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
