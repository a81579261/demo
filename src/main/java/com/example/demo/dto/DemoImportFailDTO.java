package com.example.demo.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "DemoImportFailDTO对象")
public class DemoImportFailDTO {

    @ColumnWidth(30)
    @ExcelProperty(value = "名字",index = 0)
    private String name;
    @ColumnWidth(20)
    @ExcelProperty(value = "年龄:男，女，未知",index = 1)
    private Integer age;
    @ColumnWidth(20)
    @ExcelProperty(value = "性别",index = 2)
    private String sex;
    @ColumnWidth(30)
    @ExcelProperty(value = "手机号码",index = 3)
    private String cellPhone;
    @ColumnWidth(30)
    @ExcelProperty(value = "失败原因",index = 4)
    private String failReason;
}
