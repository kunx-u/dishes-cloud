package com.etoak.service;

import com.etoak.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author et2406
 * @since 2024-11-18
 */
public interface DictService extends IService<Dict> {

    List<Dict> listDict(String type);
}
