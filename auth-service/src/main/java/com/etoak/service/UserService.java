package com.etoak.service;

import com.etoak.dto.LoginDTO;
import com.etoak.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.vo.TokenVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author et2406
 * @since 2024-11-20
 */
public interface UserService extends IService<User> {

    /**
     * 登录
     *
     * @param loginDTO
     * @return
     */
    TokenVO login(LoginDTO loginDTO);
}
