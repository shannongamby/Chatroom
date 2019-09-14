package edu.udacity.java.nano.chat;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static edu.udacity.java.nano.chat.Message.*;

@Component
@ServerEndpoint("/chat")
public class WebSocketChatServer {

    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

    private static void sendMessageToAll(String msg) {
        onlineSessions.forEach((id, session) -> {
            try {
                session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @OnOpen
    public void onOpen(Session session) {
        if(onlineSessions.containsKey(session.getId())) { return; }

        onlineSessions.put(session.getId(), session);
        sendMessageToAll(Message.jsonConverter(ENTER, "", "", onlineSessions.size()));
    }

    @OnMessage
    public void onMessage(Session session, String jsonStr) {
        Message msg = JSON.parseObject(jsonStr, Message.class);
        sendMessageToAll(Message.jsonConverter(SPEAK, msg.getUsername(), msg.getMessage(), onlineSessions.size()));
    }

    @OnClose
    public void onClose(Session session) {
        onlineSessions.remove(session.getId());
        sendMessageToAll(Message.jsonConverter(QUIT, "", "", onlineSessions.size()));
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}
