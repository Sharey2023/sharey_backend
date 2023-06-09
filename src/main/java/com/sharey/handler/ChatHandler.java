package com.sharey.handler;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ChatHandler extends TextWebSocketHandler {

    public static List<WebSocketSession> list = new ArrayList<>();
    @Override
    // 클라이언트 접속 시 호출
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        list.add(session);
        log.info(session + "클라이언트 접속");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload : " + payload);

        for(WebSocketSession sess:list)
            sess.sendMessage(message);
    }

    @Override
    // 클라이언트 접속 해제 시 호출
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info(session + "클라이언트 탈주");
        list.remove(session);
    }
}
