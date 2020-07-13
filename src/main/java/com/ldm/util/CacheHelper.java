package com.ldm.util;

import redis.clients.jedis.Jedis;

/**
 * @author lidongming
 * @ClassName CacheHelper.java
 * @Description TODO
 * @createTime 2020年07月14日 00:14:00
 */
public class CacheHelper {
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
