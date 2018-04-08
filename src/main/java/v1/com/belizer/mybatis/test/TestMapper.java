package v1.com.belizer.mybatis.test;

import v1.com.belizer.mybatis.test.entity.Test;

import java.util.List;

public interface TestMapper {
    Test selectById(int id);
    List<Test> selectAll();
}
