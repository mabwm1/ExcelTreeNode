package cn.bdqn.demo.listener;

import cn.bdqn.demo.entity.Excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

public class ExcelListener extends AnalysisEventListener<Excel> {

    /*一行一行的读取excel 中的内容，从第二行读取*/
    @Override
    public void invoke(Excel demoData, AnalysisContext analysisContext) {
        System.out.println("每一行的内容"+demoData);
    }

    /*读取表头中的内容*/
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头的内容为"+headMap);
    }

    /*读取完成之后执行的方法*/
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
