package com.etoak.service;

import com.etoak.dto.OrderDTO;
import com.etoak.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author et2406
 * @since 2024-11-22
 */
public interface OrderService extends IService<Order> {

    void create(OrderDTO order);
}
