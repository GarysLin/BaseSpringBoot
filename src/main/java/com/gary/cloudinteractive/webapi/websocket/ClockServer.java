package com.gary.cloudinteractive.webapi.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
@ServerEndpoint("/clock")
public class ClockServer {

    Thread updateThread;
    boolean running = false;

    @OnOpen
    public void startClock(Session session) {
        log.debug("startClock");
        final Session mySession = session;
        this.running = true;
        final SimpleDateFormat sdf = new SimpleDateFormat("h:mm:ss a");
        this.updateThread = new Thread() {

            public void run() {
                while (running) {
                    String dateString = sdf.format(new Date());
                    try {
                        mySession.getBasicRemote().sendText(dateString);
                        sleep(1000);
                    } catch (IOException | InterruptedException ie) {
                        running = false;
                    }
                }
            }
        };
        this.updateThread.start();
    }

    @OnMessage
    public String handleMessage(String incomingMessage) {
        if ("stop".equals(incomingMessage)) {
            this.stopClock();
            return "clock stopped";
        } else {
            return "unknown message: " + incomingMessage;
        }
    }

    @OnError
    public void clockError(Throwable t) {
        this.stopClock();
    }

    @OnClose
    public void stopClock() {
        this.running = false;
        this.updateThread = null;
    }
}
