package com.etoak.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
    private String name;

    /**
     * 食材类型, 数据在字典表中
     */
    private String type;

    /**
     * 食材图片
     */
    private String url;

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
