package com.etoak.controller;


import com.etoak.common.core.vo.ResultVO;
import com.etoak.dto.LoginDTO;
import com.etoak.service.UserService;
import com.etoak.vo.TokenVO;
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
 * @since 2024-11-20
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * post user/login
     * @param loginDTO
     * @return
     */
    @PostMapping("/login")
    public ResultVO<TokenVO> login(@Validated LoginDTO loginDTO){
        TokenVO tokenVO = userService.login(loginDTO);
        return ResultVO.success(tokenVO);
    }
}

