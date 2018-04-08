package v1.com.belizer.mybatis;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import v1.com.belizer.mybatis.entity.MapperData;
import v1.com.belizer.mybatis.entity.SelectData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 配置文件类
 */
public class BelizerConfiguration {

    public static Map dataSource=new ConcurrentHashMap();
    public static Map<String,MapperData> mapperMap=new ConcurrentHashMap<String, MapperData>();

    private static Document document;
    //1，读取xml文件
    public static void readDocument(String path){
        SAXReader saxReader=new SAXReader();
        Document document=null;
        try {
             document=saxReader.read(path);
            BelizerConfiguration.document=document;
            setDataSource();
            setMapperList(path);

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


    //2，解析xml文件 装配Configuration
    private static void setDataSource(){
       List<Node> nodes=document.selectNodes("//configuration/dataSource/property");
       for(Node node:nodes){
           dataSource.put(node.valueOf("@name"),node.valueOf("@value"));
       }
    }

    private static void setMapperList(String path) throws DocumentException, ClassNotFoundException {
        //1，获取mapper.xml文件地址
        List<Node> nodes=document.selectNodes("//configuration/mappers/mapper");
        //2，读取mapper.xml文件
        String pathDir=path.substring(0,path.lastIndexOf("\\"));//获取path目录
        SAXReader saxReader=new SAXReader();
        for(Node node:nodes){
            String mapperPath=node.valueOf("@resource");//获取的是mapper.xml文件的相对路径
            String r_mapperPath=pathDir+mapperPath;//mapper.xml文件的真实路径
            Document doc=saxReader.read(r_mapperPath);
            //3，拼装成MapperData
            MapperData mapperData=new MapperData();
            List<SelectData> list=new ArrayList<SelectData>();
            //todo 扩展读取其他标签
            List<Node> nodes1=doc.selectNodes("//mapper/select");//这里只是读取了select标签
            for(Node node1:nodes1){
                SelectData selectData=new SelectData();
                selectData.setId(node1.valueOf("@id"));
                selectData.setResultClazz(Class.forName(node1.valueOf("@resultType")));
                selectData.setSql(node1.getText());
                list.add(selectData);
            }

            //获取namespace
            String  namespace=doc.selectSingleNode("//mapper").valueOf("@namespace");
            mapperData.setSelectDatas(list);
            mapperMap.put(namespace,mapperData);
        }
    }


}
