package v1.com.belizer.mybatis;


import v1.com.belizer.mybatis.entity.MapperData;
import v1.com.belizer.mybatis.entity.SelectData;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class MapperProxy<T> implements InvocationHandler{
    private final Class<T> mapperInterface;
    private Map mapperMap;
    private BelizerExecutor be;

    public MapperProxy(Class<T> mapperInterface, Map mapperMap,BelizerExecutor be) {
        this.mapperInterface = mapperInterface;
        this.mapperMap=mapperMap;
        this.be=be;
    }



    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
       //此处只完成了查询 todo update insert delete
        MapperData mapperData=(MapperData) mapperMap.get(mapperInterface.getName());//根据接口名查找对应的xml文件对象
        List<SelectData> selectDatas=mapperData.getSelectDatas();
        String sql=null;
        Class clazz=null;
        for(SelectData selectData:selectDatas){
            if(selectData.getId().equals(method.getName())){
                sql=selectData.getSql();
                clazz=selectData.getResultClazz();
            }
        }
        //todo  需要判断返回结果是多个还是一个
//        Class type=method.getReturnType();
        List list=null;
        if(args==null){
            list=be.doQuery(sql,null,clazz);
        }else {
            list=be.doQuery(sql,args[0],clazz);
        }

        if(list.size()>1){
            return list;
        }
        return list.get(0);
    }
}
