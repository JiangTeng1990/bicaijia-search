package com.bicaijia.bcjsearch.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component("bcjRedis")
public class BcjRedis {

   @Autowired
   private JedisPool pool;
   
   @Value("${redis.expireTime}")
   private Integer expireTime;

    public void returnJedis(Jedis redis) {
        if (redis != null) {
            pool.returnResourceObject(redis);
        }
    }

    public Jedis getJedis() {
        return pool.getResource();
    }

    public byte[] objectToBytes(Object obj) throws Exception {
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOut = new ObjectOutputStream(byteOutputStream);
        try {
            objectOut.writeObject(obj);
            byte[] objByte = byteOutputStream.toByteArray();
            return objByte;
        } catch (Exception e) {
            throw e;
        } finally {
            byteOutputStream.close();
            objectOut.close();
        }
    }

    private Object bytesToObject(byte[] bytes) throws Exception {
        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectIn = new ObjectInputStream(byteInputStream);
        try {
            Object obj = objectIn.readObject();
            byteInputStream.close();
            objectIn.close();
            return obj;
        } catch (Exception e) {
            throw e;
        } finally {
            byteInputStream.close();
            byteInputStream.close();
        }
    }

    public void setString(String key, String value) throws Exception {
        Jedis redis = null;
        try {
            redis = getJedis();
            redis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 这里很重要，一旦拿到的jedis实例使用完毕，必须要返还给池中
            returnJedis(redis);
        }
    }

    public String getString(String key) throws Exception {
        Jedis redis = null;
        try {
            redis = getJedis();
            return redis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 这里很重要，一旦拿到的jedis实例使用完毕，必须要返还给池中
            returnJedis(redis);
        }
        return null;
    }

    public void set(String key, Object value) throws Exception {
        Jedis redis = null;
        try {
            redis = getJedis();
            redis.set(objectToBytes(key), objectToBytes(value));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 这里很重要，一旦拿到的jedis实例使用完毕，必须要返还给池中
            returnJedis(redis);
        }
    }

    public Object get(String key) throws Exception {
        Jedis redis = null;
        try {
            redis = getJedis();
            byte[] bytes = redis.get(objectToBytes(key));
            return bytesToObject(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 这里很重要，一旦拿到的jedis实例使用完毕，必须要返还给池中
            returnJedis(redis);
        }
        return null;
    }

    public Object getAndSet(String key, Object value) throws Exception {
        Jedis redis = null;
        try {
            redis = getJedis();
            byte[] bytes = redis.getSet(objectToBytes(key),
                    objectToBytes(value));
            return bytesToObject(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 这里很重要，一旦拿到的jedis实例使用完毕，必须要返还给池中
            returnJedis(redis);
        }
        return null;
    }
    
    public void setex(String key, Object value) {
        Jedis redis = null;
        try {
            redis = getJedis();
            redis.setex(objectToBytes(key), expireTime, objectToBytes(value));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 这里很重要，一旦拿到的jedis实例使用完毕，必须要返还给池中
            returnJedis(redis);
        }
    }
}
