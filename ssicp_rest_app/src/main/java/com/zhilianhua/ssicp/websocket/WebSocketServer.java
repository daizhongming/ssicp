package com.zhilianhua.ssicp.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
@ServerEndpoint("/websocket/3d/{username}")
@Slf4j
public class WebSocketServer {
    private static final Map<String, Session> sessionMap = new ConcurrentHashMap();

    public List<String> getClientList() {
        return sessionMap.keySet().stream().collect(Collectors.toList());
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        log.debug("onOpen,username:{}", username);
        sessionMap.put(username, session);
    }

    @OnMessage
    public void onMessage(Session session, String message, @PathParam("username") String username) {
        log.debug("onMessage,message:{},username:{}", message, username);
    }

    @OnClose
    public void onClose(Session session, @PathParam("username") String username) {
        log.debug("onClose,username:{}", username);
        sessionMap.remove(username);
    }

    @OnError
    public void onError(Session session, Throwable throwable, @PathParam("username") String username) {
        log.error("onError,username:{}", username, throwable);
    }

    public void sendTo(String username, String message) {
        if (sessionMap.containsKey(username)) {
            try {
                sessionMap.get(username).getBasicRemote().sendText(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void sendToAll(String message) {
        sessionMap.forEach((username, session) -> {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
