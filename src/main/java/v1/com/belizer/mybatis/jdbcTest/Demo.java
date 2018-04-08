package v1.com.belizer.mybatis.jdbcTest;

import v1.com.belizer.mybatis.test.entity.Test;

import java.sql.*;

public class Demo {
    public static void main(String[] args) {
        try {
            String url="jdbc:mysql://localhost:3306/test";
            String sql="select * from test where name=?";
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn= DriverManager.getConnection(url,"root","123456");
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,"belizer");
            ResultSet rs=ps.executeQuery();
            Test test=new Test();
            while (rs.next()){
                test.setId(rs.getInt("id"));
                test.setName(rs.getString("name"));
                System.out.println(test);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
