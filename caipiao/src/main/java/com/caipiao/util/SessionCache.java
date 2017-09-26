package com.caipiao.util;

import redis.clients.jedis.ShardedJedis;

/**
  * @类名：SessionCache.java
  * @功能描述: 会话缓存
*/
public class SessionCache {
    
    private static ShardedJedis j;

    /**
     * @方法名： setSession
     * @功能描述: 保持会话
     * @param sessionId
     */
    public static void setSession(String sessionId, Integer memberId) {
        j.set(sessionId, String.valueOf(memberId));
        j.expire(sessionId, 30 * 60);
    }
    
    /**
     * @方法名： getSession
     * @功能描述: 取得用户id
     * @param sessionId 会话id
     */
    public static Object getSession(String sessionId) {
        String strMemberId = j.get(sessionId);
        if (strMemberId != null) {
            j.expire(sessionId, 30 * 60);
        }
        return strMemberId;
    }
    
    /**
     * @方法名： removeSession
     * @功能描述: 删除会话记录
     * @param sessionId 会话id
     */
    public static void removeSession(String sessionId) {
        j.del(sessionId);
    }

}
