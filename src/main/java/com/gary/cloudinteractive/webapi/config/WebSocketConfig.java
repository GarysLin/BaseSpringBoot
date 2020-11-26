package com.gary.cloudinteractive.webapi.config;

import com.gary.cloudinteractive.webapi.ws.ChetRoomHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Slf4j
@Configuration
@EnableWebSocket
//@EnableWebSocketMessageBroker
//public class WebSocketConfig {
public class WebSocketConfig implements WebSocketConfigurer {
//public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

//    @Bean
//    public ServerEndpointExporter serverEndpoint() {
//        return new ServerEndpointExporter();
//    }

    @Autowired
    private ChetRoomHandler chetRoomHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        log.info("registerWebSocketHandlers ok");
        registry.addHandler(chetRoomHandler, "chetRoom")
                .setAllowedOrigins("*");
    }

//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        // 設置user嘗試連接位置
//        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
//    }
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        // 設置廣播節點
//        registry.enableSimpleBroker("/topic", "/user");
//        // user向server發送消息須有/app前綴
//        registry.setApplicationDestinationPrefixes("/app");
//        // 指定用戶發送的/user/前綴 (一對一)
//        registry.setUserDestinationPrefix("/user/");
//    }

}
