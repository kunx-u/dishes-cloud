package com.etoak.mapper;

import com.etoak.entity.Ingredients;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author et2406
 * @since 2024-11-18
 */
public interface IngredientsMapper extends BaseMapper<Ingredients> {

    /**
     * 查询食材列表
     *
     * @param ingredients
     * @return
     */
    List<Ingredients> getList(Ingredients ingredients);
}
