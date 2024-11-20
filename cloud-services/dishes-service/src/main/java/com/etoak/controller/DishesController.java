package com.etoak.controller;


import com.etoak.common.core.vo.PageVO;
import com.etoak.common.core.vo.ResultVO;
import com.etoak.entity.Dishes;
import com.etoak.service.DishesService;
import com.etoak.vo.DishVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author et2406
 * @since 2024-11-18
 */
@RestController
@RequestMapping("/dishes")
public class DishesController {

    @Autowired
    DishesService dishesService;

    @PostMapping
    public ResultVO<Object> addDishes(@Validated @RequestBody Dishes dishes) {
        dishesService.addDishes(dishes);
        return ResultVO.success();
    }

    @GetMapping("/list")
    public ResultVO<PageVO<DishVO>> getList(
            @RequestParam(required = false,defaultValue = "1")int pageNum,
            @RequestParam(required = false,defaultValue = "10")int pageSize,
            Dishes dishes){
        PageVO<DishVO> pageVO = dishesService.getList(pageNum, pageSize, dishes);
        return ResultVO.success(pageVO);
    }

    @PutMapping("/{id}")
    public ResultVO<Object> update(@PathVariable int id, @RequestBody Dishes dishes){
        dishesService.update(id,dishes);
        return ResultVO.success();
    }
    /**
     * 删除菜品
     * delete /dishes/{id}
     */
    @DeleteMapping("/{id}")
    public ResultVO<Object> delete(@PathVariable int id){
        dishesService.delete(id);
        return ResultVO.success();
    }
}

