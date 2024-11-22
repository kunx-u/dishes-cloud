package com.etoak.dto;

import com.etoak.entity.OrderItem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {

    @NotBlank(message = "prepareTime不能为空")
    private String prepareTime;

    @NotEmpty(message = "itemList不能为空")
    private List<OrderItem> itemList;
}
