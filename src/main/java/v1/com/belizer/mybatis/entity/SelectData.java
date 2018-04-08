package v1.com.belizer.mybatis.entity;

/**
 * 存放select标签数据
 */
public class SelectData {
    private String id;
    private Class<?> resultClazz;
    private String sql;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Class<?> getResultClazz() {
        return resultClazz;
    }

    public void setResultClazz(Class<?> resultClazz) {
        this.resultClazz = resultClazz;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
