package v1.com.belizer.mybatis;

import java.lang.reflect.Proxy;
import java.sql.Connection;

public class BelizerSqlSession {
//    private BelizerConfiguration belizerConfiguration;
    private BelizerExecutor belizerExecutor;
//    private Connection conn;


    public BelizerSqlSession(BelizerExecutor belizerExecutor) {
//        this.belizerConfiguration = belizerConfiguration;
        this.belizerExecutor = belizerExecutor;
    }


    //获取Mapper对象 获取Mapper对象的同时把Mapper对象和xml文件绑定
    //mapper对象调用方法 委派belizerExecutor执行数据库查询 并返回封装好的对象
    public <T>T getMapper(Class<T> type){
        return (T) Proxy.newProxyInstance(type.getClassLoader(),new Class[]{type},new MapperProxy<T>(type,BelizerConfiguration.mapperMap,belizerExecutor));
    }






}
