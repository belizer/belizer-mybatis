package com.belizer.mybatis;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 用来执行sql 并返回封装的对象
 */
public class BelizerExecutor {
    private Connection conn;
    public BelizerExecutor(Connection connection){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection()
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
