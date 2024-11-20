package com.etoak.controller;


import com.etoak.common.core.vo.PageVO;
import com.etoak.common.core.vo.ResultVO;
import com.etoak.entity.Ingredients;
import com.etoak.service.IngredientsService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author et2406
 * @since 2024-11-18
 */
@RestController
@RequestMapping("/ingredients")
@Validated
public class IngredientsController {

    @Autowired
    IngredientsService ingredientsService;

    @PostMapping
    public ResultVO<Object> add(@Validated @RequestBody Ingredients ingredients) {
        ingredientsService.add(ingredients);
        return ResultVO.success();
    }

    /**
     * 食材查询接口 GET /ingredients/list
     *
     * @param pageNum
     * @param pageSize
     * @param ingredients
     * @return
     */
    @GetMapping("/list")
    public ResultVO<PageVO<Ingredients>> getList(
            @RequestParam(required = false,defaultValue = "1") int pageNum,
            @RequestParam(required = false,defaultValue = "10") int pageSize,
            Ingredients ingredients){
        PageVO<Ingredients> pageVO = ingredientsService.getList(pageNum, pageSize, ingredients);
        return ResultVO.success(pageVO);
    }

    @PutMapping("/{id}")
    public ResultVO<Object> update(@PathVariable int id,@RequestBody Ingredients ingredients){
        ingredientsService.update(id,ingredients);
        return ResultVO.success();
    }

    /**
     * 删除食材接口 delete /ingredients/{id}
     */
    @DeleteMapping("/{id}")
    public ResultVO<Object> delete(@PathVariable int id){
        ingredientsService.delete(id);
        return ResultVO.success();
    }

    /**
     *
     */
    @GetMapping("/listByType")
    public ResultVO<List<Ingredients>> listByType(@NotBlank(message = "type不能为空")String type){
        List<Ingredients> ingredientsList = ingredientsService.listByType(type);
        return ResultVO.success(ingredientsList);
    }
}

