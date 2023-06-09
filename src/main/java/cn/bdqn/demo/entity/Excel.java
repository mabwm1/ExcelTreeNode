package cn.bdqn.demo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
/*实体*/
@Data
public class Excel {
    /**
     * 表格原本的数据
     */
    @ExcelProperty("RECID")
    private String id;

    @ExcelProperty("STDCODE")
    private String code;

    @ExcelProperty("STDNAME")
    private String label;

    @ExcelProperty("PARENTS")
    private String parents;

}
