package com.etoak.controller;


import com.etoak.common.core.vo.ResultVO;
import com.etoak.dto.OrderDTO;
import com.etoak.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author et2406
 * @since 2024-11-22
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping
    public ResultVO<Object> create(@Validated @RequestBody OrderDTO order){
        orderService.create(order);
        return ResultVO.success();
    }
}

