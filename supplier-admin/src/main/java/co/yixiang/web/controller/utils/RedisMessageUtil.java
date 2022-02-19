package co.yixiang.web.controller.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author: Administrator RedisMessageUtil消息发布方法
 * @date: 2022/2/19 12:43
 * @description:
 */
public class RedisMessageUtil {

    private static final Logger log = LoggerFactory.getLogger(RedisMessageUtil.class);

    private RedisTemplate<String, Object> redisTemplate;

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void publish(String channal ,Object obj) {
        redisTemplate.convertAndSend(channal,obj );
    }
}
