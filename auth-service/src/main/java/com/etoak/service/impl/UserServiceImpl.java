package com.etoak.service.impl;

import cn.hutool.crypto.digest.MD5;
import com.etoak.common.core.CommonConstant;
import com.etoak.common.jwt.JwtUtil;
import com.etoak.common.redis.RedisService;
import com.etoak.common.web.exception.CustomException;
import com.etoak.dto.LoginDTO;
import com.etoak.entity.User;
import com.etoak.mapper.UserMapper;
import com.etoak.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etoak.vo.TokenVO;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    RedisService redisService;

    @Override
    public TokenVO login(LoginDTO loginDTO) {
        /* 校验验证码 */
        String savedCode = redisService.get(CommonConstant.REDIS_CODE_KEY_PREFIX + loginDTO.getUuid());
        if(!StringUtils.equalsIgnoreCase(savedCode,loginDTO.getCode())){
            throw new CustomException("验证码错误");
        }

        /* 校验用户名和密码 */
        String password = MD5.create().digestHex(loginDTO.getPassword());
        User user = lambdaQuery().eq(User::getUsername,loginDTO.getUsername())
                .eq(User::getPassword,password)
                .one();
        if(ObjectUtils.isEmpty(user)){
            throw new CustomException("用户名或密码错误");
        }

        /* 创建token */
        Map<String,Object> claimsMap = new HashMap<>();
        claimsMap.put("username",user.getUsername());
        claimsMap.put("id",user.getId());

        String token = JwtUtil.create(claimsMap);
        return new TokenVO(user.getUsername(), token);

    }
}
