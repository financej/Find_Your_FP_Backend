package com.metlife.team09.domain.chat.persistence;

import org.springframework.web.socket.WebSocketSession;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatSocketSessionHandler {
    private static Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();

    public static Set<WebSocketSession> getSessions() {
        return sessions;
    }
}
