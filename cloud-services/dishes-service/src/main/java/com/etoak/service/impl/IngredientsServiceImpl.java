package com.etoak.service.impl;

import cn.hutool.core.date.DateUtil;
import com.etoak.common.core.vo.PageVO;
import com.etoak.common.web.exception.CustomException;
import com.etoak.entity.Ingredients;
import com.etoak.mapper.IngredientsMapper;
import com.etoak.service.IngredientsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author et2406
 * @since 2024-11-18
 */
@Service
public class IngredientsServiceImpl extends ServiceImpl<IngredientsMapper, Ingredients> implements IngredientsService {

    @Override
    public void add(Ingredients ingredients) {
        // 校验食材名称
        if (ObjectUtils.isNotEmpty(getByName(ingredients.getName()))) {
            throw new CustomException("食材名称重复");
        }
        // 设置创建时间
        ingredients.setCreateTime(DateUtil.now());
        this.save(ingredients);
    }

    @Override
    public PageVO<Ingredients> getList(int pageNum, int pageSize, Ingredients ingredients) {
        PageHelper.startPage(pageNum, pageSize);
        List<Ingredients> ingredientsList = baseMapper.getList(ingredients);
        PageInfo<Ingredients> pageInfo = new PageInfo<>(ingredientsList);

        return new PageVO<>(pageInfo.getPageNum(), pageInfo.getPageSize(), ingredientsList, pageInfo.getTotal());
    }

    @Override
    public void update(int id, Ingredients ingredients) {
        /* 验证id */
        if(ObjectUtils.isEmpty(this.getById(id))){
            throw new CustomException("食材不存在");
        }

        /* 食材名不为空时，判断食材名称是否重复 */
        String ingredientsName = ingredients.getName();
        if(StringUtils.isNotEmpty(ingredientsName)){
            // 根据name查询食材
            Ingredients savedIngredients = this.getByName(ingredientsName);
            // 如果食材不为空，且食材id与传入id不一致，则认为重复
            if(ObjectUtils.isNotEmpty(savedIngredients) && !savedIngredients.getId().equals(id)){
                throw new CustomException("食材名称重复");
            }

        }

        /* 更新 */
        ingredients.setId(id);
        ingredients.setUpdateTime(DateUtil.now());
        this.updateById(ingredients);

    }

    @Override
    public void delete(int id) {
        if (ObjectUtils.isEmpty(getById(id))){
            throw new CustomException("食材不存在");
        }

        // TODO 判断这个食材没有被使用

        this.removeById(id);
    }

    @Override
    public List<Ingredients> listByType(String type) {
        Ingredients ingredients = new Ingredients();
        ingredients.setType(type);
        return baseMapper.getList(ingredients);
    }

    /**
     * 根据食材名称查询食材
     *
     * @param name
     * @return
     */
    private Ingredients getByName(String name) {
        return lambdaQuery().eq(Ingredients::getName, name).one();
    }
}
