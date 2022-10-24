package com.liuchen.demo.excel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: TestEasyExcel
 * @Description:
 * @date: 2022/10/24 12:01
 */

public class TestEasyExcel {

    @Test
    public void write() {
        // 实现excel写的操作
        // 1、设置写入文件夹地址和excel文件名称
        String filename = "F:/write.xlsx";

        // 2、调用easyExcel里面的方法实现写操作
        // write方法两个参数：第一个参数文件路径名称，第二个参数实体类class
        EasyExcel.write(filename, DemoData.class).sheet("学生列表").doWrite(getData());
    }

    @Test
    public void read(){

        // 实现excel读操作
        String filename = "F:/write.xlsx";

        EasyExcel.read(filename, DemoData.class, new ExcelListener()).sheet().doRead();
    }

    // 创建方法返回list集合
    private static List<DemoData> getData(){
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData demoData = new DemoData();
            demoData.setSno(i);
            demoData.setSname("lucy" + i);
            list.add(demoData);
        }

        return list;
    }
}
