package com.etoak.service.impl;

import com.etoak.entity.Dict;
import com.etoak.mapper.DictMapper;
import com.etoak.service.DictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author et2406
 * @since 2024-11-18
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Override
    public List<Dict> listDict(String type) {
        return lambdaQuery().eq(Dict::getType,type)
                .orderByAsc(Dict::getSort)
                .list();

    }
}
