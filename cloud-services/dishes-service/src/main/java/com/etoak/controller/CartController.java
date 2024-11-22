package com.etoak.controller;

import com.etoak.common.core.vo.ResultVO;
import com.etoak.dto.CartDTO;
import com.etoak.service.CartService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping
    public ResultVO<Object> add(@Validated @RequestBody CartDTO cartDTO){
        cartService.add(cartDTO);
        return ResultVO.success();
    }

    /**
     * 查询购物车中的菜品列表  get/cart
     */
    @GetMapping
    public ResultVO<List<CartDTO>> getList(){
        List<CartDTO> cartDTOList = cartService.getList();
        return ResultVO.success(cartDTOList);
    }

    /**
     * 删除当前用户购物车的某个菜品 delete  cart/dishes/{id}
     */
    @DeleteMapping("/dishes/{id}")
    public ResultVO<Object> deleteDishes(@PathVariable int id){
        cartService.deleteDishes(id);
        return ResultVO.success();
    }

    @DeleteMapping
    public ResultVO<Object> clear(){
        cartService.clearCart();
        return ResultVO.success();
    }
}
