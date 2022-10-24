package com.liuchen.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @ClassName: DemoData
 * @Description:
 * @date: 2022/10/24 11:59
 */
@Data
public class DemoData {

    // 设置excel表头名称
    @ExcelProperty("学生编号")
    private Integer sno;

    @ExcelProperty("学生姓名")
    private String sname;
}
