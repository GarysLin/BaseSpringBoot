package com.gary.cloudinteractive.webapi.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@ServerEndpoint(value = "/websocket") //接受websocket請求路徑
@Component  //註冊到spring容器中
public class MyWebSocket {


    //儲存所有線上socket連線
    private static Map<String,MyWebSocket> webSocketMap = new LinkedHashMap<>();

    //記錄當前線上數目
    private static int count=0;

    //當前連線（每個websocket連入都會建立一個MyWebSocket例項
    private Session session;

    //處理連線建立
    @OnOpen
    public void onOpen(Session session){
        this.session=session;
        webSocketMap.put(session.getId(),this);
        addCount();
        log.info("新的連線加入：{}",session.getId());
        broadcast("新的連線加入：" + session.getId());
    }

    //接受訊息
    @OnMessage
    public void onMessage(String message,Session session){
        log.info("收到客戶端{}訊息：{}",session.getId(),message);
        try{
            this.sendMessage("收到訊息："+message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //處理錯誤
    @OnError
    public void onError(Throwable error,Session session){
        log.info("發生錯誤{},{}",session.getId(),error.getMessage());
    }

    //處理連線關閉
    @OnClose
    public void onClose(){
        webSocketMap.remove(this.session.getId());
        reduceCount();
        log.info("連線關閉:{}",this.session.getId());
        broadcast(session.getId() + "已離開，目前線上人數" + getCount() + "人");
    }

    //群發訊息

    //傳送訊息
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    //廣播訊息
    public static void broadcast(String message){
        MyWebSocket.webSocketMap.forEach((k,v)->{
            try{
                v.sendMessage(message);
            }catch (Exception e){
            }
        });
    }

    //獲取線上連線數目
    public static int getCount(){
        return count;
    }

    //操作count，使用synchronized確保執行緒安全
    public static synchronized void addCount(){
        MyWebSocket.count++;
    }

    public static synchronized void reduceCount(){
        MyWebSocket.count--;
    }
}
