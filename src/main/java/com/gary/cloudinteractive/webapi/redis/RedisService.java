package com.gary.cloudinteractive.webapi.redis;

import java.util.List;
import java.util.Set;

public interface RedisService {
    boolean set(String key, Object value);

    boolean set(String key, Object value, Long expireTime);

    void remove(String... keys);

    /**
     * 批量刪除key
     * @param pattern
     */
    void removePattern(String pattern);

    /**
     * 刪除對應的value
     * @param key
     */
    void remove(String key);
    /**
     * 判斷快取中是否有對應的value
     * @param key
     * @return
     */
    boolean exists(String key);

    /**
     * 讀取快取
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 讀取快取重設時效時間
     * @param key
     * @return
     */
    Object get(String key, Long expireTime);

    /**
     * 雜湊 新增
     * @param key
     * @param hashKey
     * @param value
     */
    void hmSet(String key, Object hashKey, Object value);

    /**
     * 雜湊獲取資料
     * @param key
     * @param hashKey
     * @return
     */
    Object hmGet(String key, Object hashKey);

    /**
     * 列表新增
     * @param k
     * @param v
     */
    void lPush(String k, Object v);

    /**
     * 列表獲取
     * @param k
     * @param l
     * @param l1
     * @return
     */
    List<Object> lRange(String k, long l, long l1);

    /**
     * 集合新增
     * @param key
     * @param value
     */
    void setArray(String key, Object value);

    /**
     * 集合獲取
     * @param key
     * @return
     */
    Set<Object> getArray(String key);

    /**
     * 有序集合新增
     * @param key
     * @param value
     * @param scoure
     */
    void zAdd(String key, Object value, double scoure);

    /**
     * 有序集合獲取
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    Set<Object> rangeByScore(String key, double scoure, double scoure1);

}
