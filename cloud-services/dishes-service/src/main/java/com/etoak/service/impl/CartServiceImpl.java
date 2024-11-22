package com.etoak.service.impl;

import cn.hutool.json.JSONUtil;
import com.etoak.common.core.CommonConstant;
import com.etoak.common.redis.RedisService;
import com.etoak.common.web.context.LoginContext;
import com.etoak.dto.CartDTO;
import com.etoak.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    RedisService redisService;

    @Override
    public void add(CartDTO cartDTO) {
        redisService.hset(getCartKey(),cartDTO.getDishesId().toString(), JSONUtil.toJsonStr(cartDTO));
    }

    @Override
    public List<CartDTO> getList() {
        return redisService.hvals(getCartKey())
                .stream().map(cartJson->JSONUtil.toBean(cartJson,CartDTO.class))
                .toList();

    }

    @Override
    public void deleteDishes(int id) {
        redisService.hdel(getCartKey(),String.valueOf(id));
    }

    @Override
    public void clearCart() {
        redisService.del(getCartKey());
    }

    private String getCartKey(){
        return CommonConstant.REDIS_CART_PREFIX + LoginContext.getUserId();
    }
}
