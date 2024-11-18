package com.etoak.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
    private String name;

    /**
     * 食材图片
     */
    private String url;

    /**
     * 主要食材的id
     */
    private Integer main;

    /**
     * 主要食材份量
     */
    private Integer mainNum;

    /**
     * 辅助食材的id
     */
    private Integer minor;

    /**
     * 辅助食材份量
     */
    private Integer minorNum;

    /**
     * 配料的id
     */
    private Integer seasoning;

    /**
     * 配料份量
     */
    private Integer seasoningNum;

    /**
     * 食材描述
     */
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
