package com.belizer.mybatis;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

/**
 * 配置文件类
 */
public class BelizerConfiguration {
    //1，读取xml文件
    public static Document getDocument(String path){
        SAXReader saxReader=new SAXReader();
        Document document=null;
        try {
             document=saxReader.read(path);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }
    //2，解析xml文件 装配Configuration

}
