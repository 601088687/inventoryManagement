/*
package com.boot.template.service;

import com.boot.template.common.constant.RedisKeyConstants;
import com.boot.template.common.utils.JsonHelper;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Objects;

*/
/**
 * @author yishuai
 * @date 2022-08-04 14:39
 * 动态选择操作的数据库
 **//*

@Component
public class CacheService {

    Logger log = LoggerFactory.getLogger(getClass());

    @Qualifier("jedisPool")
    @Resource
    private JedisPool jedisPool;

    */
/**
     * 不设置过期时长
     *//*

    public final static int NOT_EXPIRE = -1;

    public final static int DEFAULT_DB = 0;

    private static final String RELEASE_LOCK_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    */
/**
     * SETNX key value expireTime
     * <p>
     * 将 key 的值设为 value ，当且仅当 key 不存在。
     * <p>
     * 若给定的 key 已经存在，则 SETNX 不做任何动作。
     * <p>
     * 可用版本： >= 1.0.0 时间复杂度： O(1) 返回值： 设置成功，返回 1 。 设置失败，返回 0 。
     *//*

    public Boolean tryLock(int dbIndex, String key, String value, long expireTime) {
        try (Jedis jedis = getJedis(dbIndex)) {
            String result = jedis.set(key, value, "NX", "EX", expireTime);
            if ("OK".equals(result)) {
                return Boolean.TRUE;
            }
        } catch (Exception e) {
            log.error(e.toString());
            throw new JedisException(e);
        }
        return Boolean.FALSE;
    }

    */
/**
     * del key value
     * <p>
     * 将 分布式锁删除
     * 判断对应的key和value是否为对应的锁，然后lua进行删除.防止其他命令干扰
     *//*

    public Boolean unLock(int dbIndex, String key, String value) {
        try (Jedis jedis = getJedis(dbIndex)) {
            Long result = (Long) jedis.eval(RELEASE_LOCK_SCRIPT, Collections.singletonList(key),
                    Collections.singletonList(value));
            if (1L == result) {
                return Boolean.TRUE;
            }
        } catch (Exception e) {
            log.error(e.toString());
            throw new JedisException(e);
        }
        return Boolean.FALSE;
    }


    public void expire(String key, int expire, Integer dbIndex) {
        try (Jedis jedis = getJedis(dbIndex)) {
            jedis.expire(key, expire);
        } catch (Exception e) {
            log.error(e.toString());
            throw new JedisException(e);
        }
    }

    public void expire(String key, int expire) {
        this.expire(key, expire, DEFAULT_DB);
    }

    */
/**
     * 插入值-对象,指定数据库 指定过期时间
     *
     * @param key     键
     * @param value   值
     * @param dbIndex 数据库索引 范围 0-15 默认0
     * @param expire  过期时间 单位：秒
     *//*

    public void set(String key, Object value, Integer dbIndex, int expire) {
        try (Jedis jedis = getJedis(dbIndex)) {
            jedis.set(key, JsonHelper.toJson(value));
            if (expire != NOT_EXPIRE) {
                jedis.expire(key, expire);
            }
        } catch (Exception e) {
            log.error(e.toString());
            throw new JedisException(e);
        }
    }

    public void setEx(String key, Object value, int expire) {
        set(key, value, DEFAULT_DB, expire);
    }

    */
/**
     * 插入值-对象,指定数据库索引
     *//*

    public void set(String key, Object value, Integer dbIndex) {
        set(key, value, dbIndex, NOT_EXPIRE);
    }

    */
/**
     * 插入值-对象
     *//*

    public void set(String key, Object value) {
        set(key, value, DEFAULT_DB, NOT_EXPIRE);
    }

    */
/**
     * 获取值-对象,指定数据库索引,并设置过期时间
     *
     * @return
     *//*

    public <T> T getObj(String key, TypeToken<T> tTypeToken, Integer dbIndex) {
        try (Jedis jedis = getJedis(dbIndex)) {
            String value = jedis.get(key);
            return StringUtils.isBlank(value) ? null : JsonHelper.parseObject(value, tTypeToken);
        } catch (Exception e) {
            log.error(e.toString());
            throw new JedisException(e);
        }
    }

    */
/**
     * 获取值-对象,并设置过期时间
     *
     * @return
     *//*

    public <T> T get(String key, TypeToken<T> tTypeToken) {
        return getObj(key, tTypeToken, DEFAULT_DB);
    }

    */
/**
     * 取值-字符串,指定数据库索引
     *//*

    public String get(String key, Integer dbIndex) {
        try (Jedis jedis = getJedis(dbIndex)) {
            return jedis.get(key);
        } catch (Exception e) {
            log.error(e.toString());
            throw new JedisException(e);
        }
    }

    */
/**
     * 取值-字符串,指定数据库索引
     *//*

    public String get(String key) {
        return get(key, DEFAULT_DB);
    }

    */
/**
     * 删除,指定索引
     *//*

    public void delete(String key, Integer dbIndex) {
        try (Jedis jedis = getJedis(dbIndex)) {
            jedis.del(key);
        } catch (Exception e) {
            log.error(e.toString());
            throw new JedisException(e);
        }
    }

    */
/**
     * 删除,指定索引
     *//*

    public void delete(String key) {
        delete(key, DEFAULT_DB);
    }


    */
/**
     * 取值-hash,指定数据库索引
     *//*

    public String hget(String key, String field, Integer dbIndex) {
        try (Jedis jedis = getJedis(dbIndex)) {
            return jedis.hget(key, field);
        } catch (Exception e) {
            log.error(e.toString());
            throw new JedisException(e);
        }
    }

    public String hget(String key, String field) {
        return this.hget(key, field, DEFAULT_DB);
    }

    public <T> T hgetObj(String key, String field, TypeToken<T> tTypeToken, Integer dbIndex) {
        String hget = this.hget(key, field, dbIndex);
        return StringUtils.isBlank(hget) ? null : JsonHelper.parseObject(hget, tTypeToken);
    }

    public <T> T hgetObj(String key, String field, TypeToken<T> tTypeToken) {
        return hgetObj(key, field, tTypeToken, DEFAULT_DB);
    }

    */
/**
     * 赋值-hash,指定数据库索引
     *//*

    public Long hsetObj(String key, String field, Object value, Integer dbIndex) {
        try (Jedis jedis = getJedis(dbIndex)) {
            return jedis.hset(key, field, JsonHelper.toJson(value));
        } catch (Exception e) {
            log.error(e.toString());
            throw new JedisException(e);
        }
    }

    public Long hsetObj(String key, String field, Object value) {
        return hsetObj(key, field, value, DEFAULT_DB);
    }

    */
/**
     * list left push,指定数据库索引
     *//*

    public Long lpush(Integer dbIndex, String key, String... values) {
        try (Jedis jedis = getJedis(dbIndex)) {
            return jedis.lpush(key, values);
        } catch (Exception e) {
            log.error(e.toString());
            throw new JedisException(e);
        }
    }

    */
/**
     * list left push
     *//*

    public Long lpush(String key, String... values) {
        return this.lpush(DEFAULT_DB, key, values);
    }

    */
/**
     * 获取jedis对象，并指定dbIndex
     *
     * @param dbIndex
     *//*

    private Jedis getJedis(Integer dbIndex) {
        Jedis jedis = jedisPool.getResource();
        try {
            if (Objects.isNull(dbIndex) || 0 > dbIndex) {
                dbIndex = 0;
            }
            jedis.select(dbIndex);
            return jedis;
        } catch (Exception e) {
            e.printStackTrace();
            if (null != jedis) {
                jedis.close();
            }
            return null;
        }
    }

    */
/**
     * 获取jedis对象，默认为1
     *//*

    private Jedis getJedis() {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.select(DEFAULT_DB);
            return jedis;
        } catch (Exception e) {
            e.printStackTrace();
            if (null != jedis) {
                jedis.close();
            }
            return null;
        }
    }

    public static String format(String formatKey, String... keyValues) {
        if (keyValues == null || keyValues.length == 0) {
            return formatKey;
        }
        StringBuilder key = new StringBuilder();
        char[] chars = formatKey.toCharArray();
        int index = -1;
        boolean inmark = false;
        boolean firstinmark = false;
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (ch == '{') {
                index++;
                inmark = true;
                firstinmark = true;
            } else if (ch == '}') {
                inmark = false;
            } else if (inmark) {
                if (firstinmark) {
                    firstinmark = false;
                    key.append(keyValues[index]);
                }
            } else {
                key.append(chars[i]);
            }
        }
        return key.toString();
    }

    public Integer getSchoolDBIdxFromRedis(String schoolId) {
        String dbIdx = this.hget(RedisKeyConstants.PR_SCHOOL_REDIS_DBIDX, schoolId, 0);
        if (dbIdx != null && !dbIdx.equals("")) {
            return Integer.valueOf(dbIdx);
        } else {
            return 0;
        }
    }
}
*/
