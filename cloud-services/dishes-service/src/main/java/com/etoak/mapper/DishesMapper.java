package com.etoak.mapper;

import com.etoak.entity.Dishes;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etoak.vo.DishVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author et2406
 * @since 2024-11-18
 */
public interface DishesMapper extends BaseMapper<Dishes> {

    /**
     * 查询菜品列表
     *
     * @param dishes
     * @return
     */
    List<DishVO> getList(Dishes dishes);
}
