package co.yixiang.web.controller.demo.controller;

import co.yixiang.common.core.controller.BaseController;
import co.yixiang.common.core.domain.AjaxResult;
import co.yixiang.common.core.domain.entity.SysRole;
import co.yixiang.web.controller.utils.RedisMessageUtil;
import co.yixiang.web.controller.utils.RedisUtil;
import co.yixiang.web.vo.MessageVO;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author
 */
@EnableScheduling
@Controller
@RequestMapping("/testRedis")
public class TestRedisController extends BaseController
{

    @Resource
    RedisUtil redisUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 加载角色部门（数据权限）列表树
     */
    @PostMapping("/redisAdd")
    public Boolean redisAdd(@RequestBody SysRole role)
    {
        Boolean bo = redisUtil.set(role.getRoleKey(),JSON.toJSONString(role),10000L);
        return bo;
    }
    @PostMapping("/redisQuery")
    public AjaxResult redisQuery(@RequestBody SysRole role)
    {
        SysRole sysRole = null;
        String object = (String)redisTemplate.opsForValue().get(role.getRoleKey());
        if (null != object){
            sysRole = JSON.parseObject(object,SysRole.class);
            logger.info("================> sysRole:{} ", JSON.toJSON(sysRole));
        }

        return AjaxResult.success(sysRole);
    }

    @PostMapping("/redisDelete")
    public AjaxResult redisDelete(@RequestBody SysRole role)
    {
        SysRole sysRole = null;
        redisUtil.del(role.getRoleKey());
        Object object = redisUtil.get(role.getRoleKey());
        if (null != object){
            sysRole = (SysRole)object;
            logger.info("================> sysRole:{} ", JSON.toJSON(sysRole));
        }
        return AjaxResult.success(sysRole);
    }

    @Autowired
    private RedisMessageUtil redisMessageUtil;

    private int count = 0;

    @Scheduled(fixedRate = 5000)
    public String pubMsg() {
        MessageVO messageVO=new MessageVO();//1, "尚***", 26,"男","陕西省xxxx市xxxxxx县"

        count ++;
        messageVO.setCode(count);
        messageVO.setMessage("消息内容:"+count);

        redisMessageUtil.publish("phone", JSON.toJSONString(messageVO));
        logger.info("Publisher sendes Topic... ");
        return "success";
    }
}
