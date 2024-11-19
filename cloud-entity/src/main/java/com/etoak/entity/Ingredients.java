package com.etoak.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Ingredients
 *
 * @author et2406
 * @since 2024-11-18
 */
@Data
@TableName("t_ingredients")
public class Ingredients implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 食材名称
     */
    @NotBlank(message = "食材名称name不能为空")
    private String name;

    /**
     * 食材类型, 数据在字典表中
     */
    @NotBlank(message = "食材类型type不能为空")
    @DecimalMin(value = "1",message = "食材类型type最小值为1")
    @DecimalMax(value = "3",message = "食材类型type最大值为3")
    private String type;

    /**
     * 食材图片
     */
    @NotBlank(message = "食材图片url不能为空")
    private String url;

    /**
     * 食材描述
     */
    @NotBlank(message = "食材描述description不能为空")
    private String description;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;

}
