package com.etoak.common.core.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVO<T> {

    private Integer pageNum;

    private Integer pageSize;

    private List<T> rows;

    private Long total;
}
