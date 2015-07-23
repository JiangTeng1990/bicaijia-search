package com.bicaijia.bcjsearch.query;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


public class Condition {
    //数据缓存
    private static Map<String, Object> cacheMap = new ConcurrentHashMap<String, Object>(100);
    
    public static <T> void convertFilterField( IndexQuery indexQuery, Class<T> clazz) {
       
        Set<String> names = getFieldNames(clazz);
        Map<String, Object> map = indexQuery.getFilterField();
        if(map.size() == 0) return;
        for (String fieldName : map.keySet()) {
            if (!names.contains(fieldName)) {
                map.remove(fieldName);
            }
        }
    }
    
   public static <T> void convertFilterField(IndexQuery from, IndexQuery to, Class<T> clazz) {
       
       Map<String, Object> map = from.getFilterField();
       Set<String> names = getFieldNames(clazz);
       for (Map.Entry<String, Object> entry : map.entrySet()) {
           String key = entry.getKey();
           Object value = entry.getValue();
           if (null == value) {
               continue;
           }
           if (names.contains(key)) {
               to.getFilterField().put(key, value);
           }
       }
   }
   
   public static <T> Set<String> getFieldNames(Class<T> clazz) {
       //获取所有属性
       String key = "getFieldNames/" + clazz.getName();
       Object obj = cacheMap.get(key);
       Set<String> names = null;
       if (obj == null) {
           Field[] fields = clazz.getDeclaredFields();
           names = new HashSet<String>();
           for (Field field : fields) {
               String name = field.getName();
               if (name == null) {
                   continue;
               }
               names.add(name.trim());
           }
           cacheMap.put(key, names);
       } else {
           names = (Set<String>) obj;
       }
       return names;
   }
}
