package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author Agus
 * @since 2020-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("demo_import")
@ApiModel(value = "DemoImport对象", description = "")
public class DemoImport implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "名字")
    @TableId(value = "name",type = IdType.AUTO)
    private String name;

    @ApiModelProperty(value = "年龄")
    @TableField("age")
    private Integer age;

    @ApiModelProperty(value = "性别")
    @TableField("sex")
    private String sex;

    @ApiModelProperty(value = "手机号码")
    @TableField("cell_phone")
    private String cellPhone;

    @ApiModelProperty(value = "id")
    @TableId("id")
    private Long id;


}
