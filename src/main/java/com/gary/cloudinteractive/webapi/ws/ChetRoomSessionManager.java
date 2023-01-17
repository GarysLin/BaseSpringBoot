package com.gary.cloudinteractive.webapi.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class ChetRoomSessionManager {
    /**
     * 保存連結 session 的地方
     */
    private static ConcurrentHashMap<String, WebSocketSession> SESSION_POOL = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<String, String> USER_SESSION_POOL = new ConcurrentHashMap<>();

    /**
     * 增加 session
     * @param key
     */
    public static void add(String key, WebSocketSession session) {
        // 添加 session
        SESSION_POOL.put(key, session);
    }

    /**
     * 刪除 session,會返回刪除的 session
     * @param key
     * @return
     */
    public static WebSocketSession remove(String key) {
        // 删除 session
        return SESSION_POOL.remove(key);
    }

    /**
     * 刪除並同步關閉連結
     * @param key
     */
    public static void removeAndClose(String key) {
        WebSocketSession session = remove(key);
        if (session != null) {
            try {
                // 關閉連結
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 獲得 session
     * @param key
     * @return
     */
    public static WebSocketSession get(String key) {
        return SESSION_POOL.get(key);
    }

    public static int getCount() {
        return SESSION_POOL.size();
    }

    //廣播訊息
    public static void broadcast(String message){
        SESSION_POOL.forEach((k,v)->{
            try{
                v.sendMessage(new TextMessage(message));
            }catch (Exception e){
            }
        });
    }
}
