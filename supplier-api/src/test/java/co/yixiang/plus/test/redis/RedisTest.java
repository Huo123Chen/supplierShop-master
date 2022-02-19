package co.yixiang.plus.test.redis;

import co.yixiang.ApiApplication;
import co.yixiang.app.common.utils.RedisUtil;
import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: Administrator
 * @date: 2022/2/3 12:47
 * @description:
 */

@RunWith(SpringRunner.class)
public class RedisTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisUtil redisUtil;

    @Test
    public void testInsert(){
        User user = new User();
        user.setAge(28);
        user.setName("LiYi");

        redisUtil.set("LiYi",user);
    }

    @Data
    class User {
        private String name;
        private int age;


    }
}
