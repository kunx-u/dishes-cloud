package com.etoak.service.impl;

import com.etoak.entity.User;
import com.etoak.mapper.UserMapper;
import com.etoak.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author et2406
 * @since 2024-11-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
