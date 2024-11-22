package com.etoak.service;

import com.etoak.dto.CartDTO;

import java.util.List;


/**
 * 购物车服务
 */
public interface CartService {

    /**
     * 添加、修改
     * @param cartDTO
     */
    void add(CartDTO cartDTO);

    /**
     * 查询当前登录用户的购物车中的菜品列表
     * @return
     */
    List<CartDTO> getList();

    /**
     * 删除当前登录用户的某个菜品
     */
    void deleteDishes(int id);

    /**
     * 清空当前用户的购物车
     */
    void clearCart();



}
