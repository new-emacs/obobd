package com.obobod.cache.redis;


import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

import java.util.Set;


/**
 * @author litao
 * modified by songteng @2015-09-28
 */

public class RedisDao {

    private static final Logger log = Logger.getLogger(RedisDao.class);

    // redis秒单位过期时间 目前设置为10分钟
    public static final int REDIS_EXPIRE_TIME = 60 * 10 *60;

    Jedis jedis = new Jedis("redis.local");

    /**
     * 根据key获取valueString
     *
     * @param key
     * @return String
     * @method: RedisDao getValueByKey
     * @create date： 2014年8月27日
     * @2014, by chendongwei.
     */
    public String getValueByKey(String key) {

        String value = null;

        try {
            value = this.jedis.get(key);
        } catch (Exception e) {
            RedisDao.log.error("getValueByKey exception ,Key:" + key, e);
        }

        return value;
    }

    public byte[] get(byte key[]) {
        byte[] value = null;
        try {
            value = this.jedis.get(key);
        } catch (Exception e) {
            RedisDao.log.error("get exception ,Key:", e);
        }

        return value;
    }

    public void put(byte[] key, byte[] value) throws Exception {

        try {
            this.jedis.set(key, value);
            this.jedis.expire(key, REDIS_EXPIRE_TIME);
        } catch (Exception e) {
            RedisDao.log.error("setKeyValue exception ,Key:" + e.getMessage());
            throw e;
        }
    }



    public Long remove(byte[] key) throws Exception {
        return this.jedis.del(key);
    }

    public  synchronized void clear() {

        jedis.flushAll();

    }



    /**
     * reids存储key， value
     *
     * @param key
     * @param value
     * @throws Exception void
     * @method: RedisDao setKeyValue
     * @create date： 2014年8月27日
     * @2014, by chendongwei.
     */
    public void setKeyValue(String key, String value) throws Exception {

        try {
            this.jedis.set(key, value);
            this.jedis.expire(key, REDIS_EXPIRE_TIME);
        } catch (Exception e) {
            RedisDao.log.error("setKeyValue exception ,Key:" + key + " ### Value:" + value);
            throw e;
        }
    }


    public Set<String> keySet() {
        return this.jedis.keys("*");
    }
}
