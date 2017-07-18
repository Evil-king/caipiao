package com.caipiao.util;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Session缓存
 */
public class SessionIdCache {
private static ConcurrentHashMap<String, Integer> sidMap = new ConcurrentHashMap<String, Integer>();
    
    public static Integer getValue(String sid) {
        return sidMap.get(sid);
    }

    public static void setValue(String sid, Integer userId) {
        sidMap.put(sid, userId);
    }
    
    public static void remove(String sid) {
        sidMap.remove(sid);
    }
    
}
