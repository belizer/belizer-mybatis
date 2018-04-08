package v1.com.belizer.mybatis;

import v1.com.belizer.mybatis.test.TestMapper;
import v1.com.belizer.mybatis.test.entity.Test;

import java.util.List;

/**
 * test！
 *
 */
public class App {
    public static void main( String[] args ) {
        //读取配置文件
        BelizerConfiguration.readDocument("D:\\gitrepository\\gupao\\belizer-mybatis\\src\\main\\java\\v1\\com\\belizer\\mybatis\\belizer.conf.xml");
        //获取sqlSession
        BelizerExecutor be=new BelizerExecutor(BelizerConfiguration.dataSource);
        BelizerSqlSession bs=new BelizerSqlSession(be);
        //获取mapper
        TestMapper testMapper=bs.getMapper(TestMapper.class);
        //调用方法
        Test test=testMapper.selectById(1);
        System.out.println(test);

        List<Test> tests=testMapper.selectAll();
        System.out.println(tests);
    }
}
