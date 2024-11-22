package com.etoak.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartDTO {

    @NotNull(message = "dishesId不能为空")
    private Integer dishesId;

    @NotBlank(message = "name不能为空")
    private String name;

    @NotNull(message = "count不能为空")
    @Min(message = "count最小值为1",value = 1)
    @Max(message = "count最大值是10",value = 10)
    private Integer count;

}
