package com.example.demo.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author Agus
 * @since 2020-04-25
 */
@Data
@ApiModel(value = "DemoImportDTO对象")
public class DemoImportDTO {

    @ColumnWidth(30)
    @ExcelProperty(value = "名字",index = 0)
    private String name;
    @ColumnWidth(20)
    @ExcelProperty(value = "年龄",index = 1)
    private Integer age;
    @ColumnWidth(20)
    @ExcelProperty(value = "性别:男，女，未知",index = 2)
    private String sex;
    @ColumnWidth(30)
    @ExcelProperty(value = "手机号码",index = 3)
    private String cellPhone;

}
