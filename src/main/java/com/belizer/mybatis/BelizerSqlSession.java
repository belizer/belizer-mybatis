package com.belizer.mybatis;

import java.sql.Connection;

public class BelizerSqlSession {
    private BelizerConfiguration belizerConfiguration;
    private BelizerExecutor belizerExecutor;
    private Connection conn;


    public BelizerSqlSession(BelizerConfiguration belizerConfiguration, BelizerExecutor belizerExecutor) {
        this.belizerConfiguration = belizerConfiguration;
        this.belizerExecutor = belizerExecutor;
    }


    //获取Mapper对象
    public <T>T getMapper(Class<T> type){
        return null;
    }
    //mapper对象调用方法 委派belizerExecutor执行数据库查询 并返回封装好的对象

    //根据配置文件获取connection
    getConnection(){

    }




}
