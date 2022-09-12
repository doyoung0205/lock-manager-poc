package me.doyoung.lockmanagerpoc.interfaces.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.security.Principal;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class LockHandler extends TextWebSocketHandler {

    private static final Logger log = LoggerFactory.getLogger(LockHandler.class);

    private static final Set<WebSocketSession> sessions = new LinkedHashSet<>();
    private static final Map<String, Principal> users = new HashMap<>();


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        for (final var sess : sessions) {
            sess.sendMessage(message);
        }
    }

    /* Client가 접속 시 호출되는 메서드 */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("클라이언트 접속 {}", session);

        users.put(session.getId(), session.getPrincipal());
        sessions.add(session);
        handleTextMessage(session, new TextMessage(getUserArray()));
    }

    /* Client가 접속 해제 시 호출되는 메서드 */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("클라이언트 접속 해제 {}", session);
        users.remove(session.getId());
        sessions.remove(session);
        handleTextMessage(session, new TextMessage(getUserArray()));
    }

    private static String getUserArray() {
        System.out.println("users.toString() = " + users);
        return sessions.stream()
                .map(WebSocketSession::getId)
                .map(users::get)
                .filter(Objects::nonNull)
                .map(Principal::getName)
                .collect(Collectors.joining(","));
    }
}
