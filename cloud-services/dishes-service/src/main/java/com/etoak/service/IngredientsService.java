package com.etoak.service;

import com.etoak.common.core.vo.PageVO;
import com.etoak.entity.Ingredients;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author et2406
 * @since 2024-11-18
 */
public interface IngredientsService extends IService<Ingredients> {

    /**
     * 添加食材
     *
     * @param ingredients 食材参数
     */
    void add(Ingredients ingredients);

    /**
     * 分页查询
     * @param pageNum 页码
     * @param pageSize 每页记录数
     * @param ingredients 查询参数
     * @return
     */
    PageVO<Ingredients> getList(int pageNum,int pageSize,Ingredients ingredients);

    /**
     * 更新食材
     *
     * @param id 食材id
     * @param ingredients 更新参数
     */
    void update(int id, Ingredients ingredients);

    /**
     * 删除食材
     * @param id 食材id
     */
    void delete(int id);
}
