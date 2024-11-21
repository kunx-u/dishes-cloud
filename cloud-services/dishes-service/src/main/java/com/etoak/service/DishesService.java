package com.etoak.service;

import com.etoak.common.core.vo.PageVO;
import com.etoak.entity.Dishes;
import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.vo.DishVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author et2406
 * @since 2024-11-18
 */
public interface DishesService extends IService<Dishes> {

    /**
     * 添加菜品
     *
     * @param dishes 菜品参数
     */
    void addDishes(Dishes dishes);


    /**
     * 分页查询
     * @param pageNum
     * @param pageSize 每页记录数
     * @param dishes 查询参数
     * @return
     */
    PageVO<DishVO> getList(int pageNum,int pageSize,Dishes dishes);

    /**
     * 更新菜品
     *
     * @param id
     * @param dishes
     */
    void update(int id, Dishes dishes);

    /**
     * 删除菜品
     * @param id
     */
    void delete(int id);

    /**
     * 判断食材有没有使用
     * @param ingredientsId
     * @return
     */
    boolean isIngredients(int ingredientsId);
}
