package com.etoak.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum OrderStateEnum {
    NEW("1"),// 新建状态
    PAY("2"), //支付
    CANCEL("3"); //取消
    private String state;
}
