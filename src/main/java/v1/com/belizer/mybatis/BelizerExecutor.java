package v1.com.belizer.mybatis;

import v1.com.belizer.mybatis.test.entity.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * 用来执行sql 并返回封装的对象
 */
public class BelizerExecutor {
    private Connection conn;
    public BelizerExecutor(Map dataSource){
        try {
            Class.forName(dataSource.get("driverClass").toString());
            conn= DriverManager.getConnection
                    (dataSource.get("jdbcUrl").toString(),dataSource.get("user").toString(),dataSource.get("password").toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    //要执行查询，需要一个doQuery方法，如果是多个参数，可以把它拼装成一个对象
    public <E>List<E> doQuery(String sql,Object paramter,Class resultClazz) throws SQLException, IllegalAccessException, InstantiationException {
        PreparedStatement ps=conn.prepareStatement(sql);
        setParameter(paramter, ps);
        ResultSet rs=ps.executeQuery();
        Field[] declaredFields = resultClazz.getDeclaredFields();
        List resultList=new ArrayList();
        //假设数据库列名和类字段名一样
        while (rs.next()){
            Object o = resultClazz.newInstance();
            for(Field field : declaredFields){
                field.setAccessible(true);
                Object object = rs.getObject(field.getName());
                if(null != object){
                    field.set(o, object);
                }
            }
            resultList.add(o);
        }
        return (List<E>)resultList;
    }

    private void setParameter(Object paramter, PreparedStatement ps) throws SQLException {
        if(paramter instanceof Long){
            ps.setLong(1,(Long)paramter);
        }else if(paramter instanceof Integer){
            ps.setInt(1,(Integer)paramter);
        }else if(paramter instanceof Double){
            ps.setDouble(1,(Double)paramter);
        }else if(paramter instanceof Float){
            ps.setFloat(1,(Float)paramter);
        }else if(paramter instanceof Short){
            ps.setShort(1,(Short)paramter);
        }else if(paramter instanceof Byte){
            ps.setByte(1,(Byte)paramter);
        }else if(paramter instanceof Character){
//            ps.setChar
        }else if(paramter instanceof Boolean){
            ps.setBoolean(1,(Boolean)paramter);
        }else if(paramter instanceof String){
            ps.setString(1,(String)paramter);
        }else if(paramter instanceof Date){
            ps.setDate(1,new java.sql.Date(((Date) paramter).getTime()));
        }else if(paramter instanceof BigDecimal){
            ps.setBigDecimal(1,(BigDecimal)paramter);
        }else if(paramter instanceof Map){

        }else if(paramter instanceof HashMap){

        }else if(paramter instanceof List){

        }else if(paramter instanceof ArrayList){

        }else if(paramter instanceof Collection){

        }else if(paramter instanceof Iterator){

        }else {
            //说明是多个参数
        }
    }



}
