package com.bicaijia.bcjsearch.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component("bcjRedis")
public class BcjRedis {

    private static JedisPool pool;
    // private Jedis jedis;

    @Value("redis.host")
    private String host;// Redis服务所在地址

    @Value("redis.port")
    private int port;// 主机端口

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    /**
     * 初始化jedis
     */
    public void init() {
        JedisPoolConfig config = new JedisPoolConfig();
        // 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
        // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
        config.setMaxActive(50);
        // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
        config.setMaxIdle(5);
        // 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
        config.setMaxWait(1000 * 100);
        // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
        config.setTestOnBorrow(true);
        pool = new JedisPool(config, host, port);
        // jedis = new Jedis(host, port);
    }

    public static void returnJedis(Jedis redis) {
        if (redis != null) {
            pool.returnResource(redis);
        }
    }

    public static Jedis getJedis() {
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
}
