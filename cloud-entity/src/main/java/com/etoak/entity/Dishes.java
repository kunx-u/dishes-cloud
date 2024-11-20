package com.etoak.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Dishes
 *
 * @author et2406
 * @since 2024-11-18
 */
@Data
@TableName("t_dishes")
public class Dishes implements Serializable {

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
     * 食材图片
     */
    @NotBlank(message = "食材图片url不能为空")
    private String url;

    /**
     * 主要食材的id
     */
    @NotNull(message = "主要食材main不能为空")
    private Integer main;

    /**
     * 主要食材份量
     */
    @NotNull(message = "主要食材分量mainNum不能为空")
    private Integer mainNum;

    /**
     * 辅助食材的id
     */
    @NotNull(message = "辅助食材minor不能为空")
    private Integer minor;

    /**
     * 辅助食材份量
     */
    @NotNull(message = "辅助食材份量minorNum不能为空")
    private Integer minorNum;

    /**
     * 配料的id
     */
    @NotNull(message = "配料食材seasoning不能为空")
    private Integer seasoning;

    /**
     * 配料份量
     */
    @NotNull(message = "配料食材份量seasoningNum不能为空")
    private Integer seasoningNum;

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
