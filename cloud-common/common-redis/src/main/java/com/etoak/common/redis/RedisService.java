package com.etoak.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * String 的set命令
     * @param key
     * @param value 值
     * @param timeUnit 时间单位
     * @param expire 过期时长
     */
    public void set(String key, String value, long expire, TimeUnit timeUnit){
        stringRedisTemplate.opsForValue().set(key, value,expire,timeUnit);

    }

    /**
     * String 的get命令
     * @param key
     * @return
     */
    public String get(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }


    public void hset(String key,String filed,String value){
        stringRedisTemplate.opsForHash().put(key,filed,value);
    }

    public List<String> hvals(String key){
        return stringRedisTemplate.opsForHash().values(key)
                .stream().map(Object::toString).toList();
    }

    public void hdel(String key,String field){
        stringRedisTemplate.opsForHash().delete(key,field);
    }

    /**
     * 全局命令 del
     */
    public void del(String key){
        stringRedisTemplate.delete(key);
    }
}
