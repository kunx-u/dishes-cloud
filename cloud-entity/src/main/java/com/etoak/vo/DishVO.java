package com.etoak.vo;

import com.etoak.entity.Dishes;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DishVO extends Dishes {

    /**
     * 主要食材名称
     */
    private String mainName;
    /**
     * 辅助食材名称
     */
    private String minorName;

    private String seasoningName;

}
