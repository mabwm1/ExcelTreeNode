package cn.bdqn.demo.listener;

import cn.bdqn.demo.entity.Excel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 监听器
 */
public class ProvinceCityCountyListener extends AnalysisEventListener<Excel> {

    private List<Excel> list = new ArrayList<>();

    @Override
    public void invoke(Excel data, AnalysisContext context) {
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {}

    public List<Excel> getList() {
        return list;
    }
}
