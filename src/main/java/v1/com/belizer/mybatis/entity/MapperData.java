package v1.com.belizer.mybatis.entity;

import java.util.List;
import java.util.Map;

public class MapperData {
    private List<Map> resultMaps;

    private List<SelectData> selectDatas;

    public List<Map> getResultMaps() {
        return resultMaps;
    }

    public void setResultMaps(List<Map> resultMaps) {
        this.resultMaps = resultMaps;
    }

    public List<SelectData> getSelectDatas() {
        return selectDatas;
    }

    public void setSelectDatas(List<SelectData> selectDatas) {
        this.selectDatas = selectDatas;
    }
}
