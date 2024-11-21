package com.etoak.service.impl;

import cn.hutool.core.date.DateUtil;
import com.etoak.common.core.vo.PageVO;
import com.etoak.common.web.exception.CustomException;
import com.etoak.entity.Dishes;
import com.etoak.mapper.DishesMapper;
import com.etoak.service.DishesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etoak.vo.DishVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author et2406
 * @since 2024-11-18
 */
@Service
public class DishesServiceImpl extends ServiceImpl<DishesMapper, Dishes> implements DishesService {

    @Override
    public void addDishes(Dishes dishes) {
        if (ObjectUtils.isNotEmpty(this.getByName(dishes.getName()))) {
            throw new CustomException("菜品名称重复");
        }

        dishes.setCreateTime(DateUtil.now());
        this.save(dishes);
    }

    @Override
    public PageVO<DishVO> getList(int pageNum, int pageSize, Dishes dishes) {
        PageHelper.startPage(pageNum,pageSize);
        List<DishVO> dishesVOList = baseMapper.getList(dishes);
        PageInfo<DishVO> pageInfo = new PageInfo<>(dishesVOList);
        return new PageVO<>(pageInfo.getPageNum(),pageInfo.getPageSize(),dishesVOList,pageInfo.getTotal());
    }

    @Override
    public void update(int id, Dishes dishes) {
        if(ObjectUtils.isEmpty(this.getById(id))){
            throw new CustomException("菜品不存在");
        }
        /* 菜品名称不能修改其他菜品名称 */
        String dishesName = dishes.getName();
        if(StringUtils.isNotEmpty(dishesName)){
            Dishes savedDishes = this.getByName(dishesName);
            if(ObjectUtils.isNotEmpty(savedDishes) && !savedDishes.getId().equals(id)){
                throw new CustomException("菜品名称重复");
            }
        }

        dishes.setId(id);
        dishes.setUpdateTime(DateUtil.now());
        updateById(dishes);
    }

    @Override
    public void delete(int id) {
        if(ObjectUtils.isEmpty(this.getById(id))){
            throw new CustomException("菜品不存在");
        }
        this.removeById(id);
    }

    @Override
    public boolean isIngredients(int ingredientsId) {
        return lambdaQuery().eq(Dishes::getMain,ingredientsId)
                .or()
                .eq(Dishes::getMinor,ingredientsId)
                .or()
                .eq(Dishes::getSeasoning,ingredientsId)
                .count()>0;
    }

    /**
     * 根据菜品名称查询菜品信息
     *
     * @param name 菜品名称
     * @return
     */
    private Dishes getByName(String name){
        return lambdaQuery().eq(Dishes::getName,name).one();
    }
}
