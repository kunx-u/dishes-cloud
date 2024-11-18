package com.etoak.controller;


import com.etoak.common.core.vo.ResultVO;
import com.etoak.entity.Dict;
import com.etoak.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author et2406
 * @since 2024-11-18
 */
@RestController
@RequestMapping("/dict")
public class DictController {

    @Autowired
    DictService dictService;

    @GetMapping("/list")
    public ResultVO<List<Dict>> list(@RequestParam String type){
        List<Dict> dictList = dictService.listDict(type);
        return ResultVO.success(dictList);
    }
}

