package com.gary.cloudinteractive.webapi.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@Component
public class ChetRoomHandler extends TextWebSocketHandler {
    private WebSocketSession session;

    /**
     * socket 建立成功事件
     * like @OnOpen
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info(session.toString());
        this.session = session;
        ChetRoomSessionManager.add(session.getId(), session);
        log.info("新的連線加入：{}",session.getId());
        ChetRoomSessionManager.broadcast("新的連線加入：" + session.getId());
    }

    /**
     * 接收消息事件
     * like @OnMessage
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info(message.getPayload());
//        // 获得客户端传来的消息
//        String payload = message.getPayload();
//        Object token = session.getAttributes().get("token");
//        System.out.println("server 接收到 " + token + " 发送的 " + payload);
//        session.sendMessage(new TextMessage("server 发送给 " + token + " 消息 " + payload + " " + LocalDateTime.now().toString()));
    }

    /**
     * socket 斷開連結時
     * like @OnClose
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("連線關閉:{}",this.session.getId());
        ChetRoomSessionManager.remove(session.getId());
        ChetRoomSessionManager.broadcast(session.getId() + "已離開，目前線上人數" + ChetRoomSessionManager.getCount() + "人");
    }

    //傳送訊息
    public void sendMessage(String message) throws IOException {
        this.session.sendMessage(new TextMessage(message));
    }


}
