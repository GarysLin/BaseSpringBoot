package com.gary.cloudinteractive.webapi.config;

import com.gary.cloudinteractive.webapi.ws.ChetRoomHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.io.IOException;
import java.util.Map;

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
        registry.addHandler(chetRoomHandler, "/websocket/chetRoom/*")
                .addInterceptors(auctionInterceptor())
                .setAllowedOrigins("*");
    }

    /**
     * 權限檢核
     * @return
     */
    @Bean
    public HandshakeInterceptor auctionInterceptor() {
        return new HandshakeInterceptor() {
            @Override
            public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                           WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

                // Get the URI segment corresponding to the auction id during handshake
                String path = request.getURI().getPath();
                String auctionId = path.substring(path.lastIndexOf('/') + 1);

                // This will be added to the websocket session
                attributes.put("auctionId", auctionId);
                return true;
            }

            @Override
            public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                       WebSocketHandler wsHandler, Exception exception) {
                // Nothing to do after handshake
            }
        };
    }

    @Bean
    public WebSocketHandler auctionHandler() {
        return new TextWebSocketHandler() {
            public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
                // Retrieve the auction id from the websocket session (copied during the handshake)
                String auctionId = (String) session.getAttributes().get("auctionId");

                // Your business logic...
            }
        };
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
