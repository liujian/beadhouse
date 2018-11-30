package com.beadhouse.redis;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;


@Service
public class RedisService <T> {
	
	@Resource
    private RedisTemplate redisTemplate;

    @Resource(name = "redisTemplate")
    ValueOperations<Object, Object> valOpsObj;


    /**
     * 一天有多少分钟，默认时间是一天
     */
    private static final long MINUTES_OF_ONE_DAY = 24 * 60;


    /**
     * 将 key，value 存放到redis数据库中，默认设置过期时间为一天
     *
     * @param key
     * @param value
     */
    public void set(String key, T value) {
        set(key, value, MINUTES_OF_ONE_DAY);
    }

    /**
     * 将 key，value 存放到redis数据库中，设置过期时间单位是分钟
     *
     * @param key
     * @param value
     * @param expireTime 单位是分钟
     */
    public void set(String key, T value, long expireTime) {
        ValueOperations<String, T> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key,value,expireTime,TimeUnit.MINUTES);
    }

    /**
     * 判断 key 是否在 redis 数据库中
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        
        return redisTemplate.hasKey(key);
    }

    /**
     * 根据指定o获取Object
     * @param o
     * @return
     */
    public Object getObj(Object o) {
        Object o1 = valOpsObj.get(o);
        if(o1==null){
            return o1;
        }else{
            return o1;
        }
    }
    /**
     * 设置obj缓存
     * @param key
    * @param value
    */
   public void setObj(Object key, Object value){

       valOpsObj.set(key, value);
   }
  
  
    
    /** 添加对象到redis 里面的list中
     *  redis中的 list 是双向的 所以添加的时候需要注意
     *  rightPush 先进先出 leftPush 先进后出 这里 需要注意
     * @param key list 对应的key
     * @param obj 需要存的对象
     */
     public void addList(String key,Object obj){
         redisTemplate.opsForList().rightPush(key,obj);
     }


    /**
     * opsForList().range(key, start, end);  取范围值  redis里面的list下标从0开始
     *  流程 拿到key 对应的list 取 0 到 5  和 mysql的limt  类似 注意下标即可
     * 从redis list 里面的获取数据分页
     * @param key redis list 对应的key
     * @param start  开始下标
     * @param end 介绍下标
     * @return 返回list给前端
     */
    public List getListPage(String key, int start, int end){
        return (List)redisTemplate.opsForList().range(key, start, end);
    }

}