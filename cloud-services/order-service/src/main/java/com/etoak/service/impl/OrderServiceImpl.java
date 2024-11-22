package com.etoak.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.etoak.common.core.enums.OrderStateEnum;
import com.etoak.common.web.context.LoginContext;
import com.etoak.dto.OrderDTO;
import com.etoak.entity.Order;
import com.etoak.entity.OrderItem;
import com.etoak.mapper.OrderMapper;
import com.etoak.service.OrderItemService;
import com.etoak.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author et2406
 * @since 2024-11-22
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    OrderItemService orderItemService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(OrderDTO orderDTO) {
        String orderNo = IdUtil.getSnowflakeNextIdStr();
        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setUserId(Integer.valueOf(LoginContext.getUserId()));
        order.setOrderState(Integer.valueOf(OrderStateEnum.NEW.getState()));
        order.setCreateTime(DateUtil.now());
        order.setPrepareTime(orderDTO.getPrepareTime());

        this.save(order);

        List<OrderItem> orderItemList = orderDTO.getItemList().stream()
                .peek(item -> item.setOrderNo(orderNo))
                .toList();
        orderItemService.saveBatch(orderItemList);
    }
}
