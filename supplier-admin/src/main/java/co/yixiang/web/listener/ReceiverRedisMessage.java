package co.yixiang.web.listener;

import co.yixiang.web.vo.MessageVO;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

/**
 * @author: Administrator 消息订阅方法
 * @date: 2022/2/19 12:45
 * @description:
 */
public class ReceiverRedisMessage {

    private static final Logger log = LoggerFactory.getLogger(ReceiverRedisMessage.class);
    private CountDownLatch latch;

    @Autowired
    public ReceiverRedisMessage(CountDownLatch latch) {
        this.latch = latch;
    }

    /**
     * 队列消息接收方法
     *
     * @param jsonMsg
     */
    public void receiveMessage(String jsonMsg) {
        log.info("[开始消费REDIS消息队列phone数据...]");
        try {
            log.info("监听者收到消息：{}", jsonMsg);
            MessageVO messageVO = (MessageVO)Json2Pojo(jsonMsg, MessageVO.class);
//          MessageVO messageVO = JSON.parseObject(jsonMsg, MessageVO.class);
            System.out.println("转化为对象 ："+messageVO);
            log.info("[消费REDIS消息队列phone数据成功.]");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[消费REDIS消息队列phone数据失败，失败信息:{}]", e.getMessage());
        }
        latch.countDown();
    }

    public  <T> T Json2Pojo(String str, Class<T> clazz) {
        return JSON.parseObject(JSON.parse(str).toString(), clazz);
    }
}