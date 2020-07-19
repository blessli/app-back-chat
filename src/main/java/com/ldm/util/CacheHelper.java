package com.ldm.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author lidongming
 * @ClassName CacheHelper.java
 * @Description TODO
 * @createTime 2020年07月14日 00:14:00
 */
@Service
public class CacheHelper {

    @Autowired
    private JedisPool jedisPool;

    /**
     * @title 滑动时间窗限流，用户操作频率限制
     * @description 用于控制用户行为,如发布活动/动态/评论/回复/聊天
     * @author lidongming
     * @updateTime 2020/4/8 1:57
     */
    public boolean limitFrequency(String type,int userId){
        long nowTs=System.currentTimeMillis();
        int period=60,maxCount=10;
        String key= RedisKeys.limitFrequency(type, userId);
        Jedis jedis=jedisPool.getResource();
        jedis.zadd(key,nowTs,""+nowTs);
        jedis.zremrangeByScore(key,0,nowTs-period*1000);
        long currCount=jedis.zcard(key);
        returnToPool(jedis);
        return currCount>maxCount;
    }
    /**
     * @title 将redis连接对象归还到redis连接池
     * @description
     * @author lidongming
     * @updateTime 2020/4/4 16:14
     */
    public static void returnToPool(Jedis jedis) {
        if (jedis != null){
            jedis.close();
        }
    }
}
