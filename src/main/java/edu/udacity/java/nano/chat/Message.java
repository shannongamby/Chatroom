package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.JSON;

public class Message {
    private String type;
    private String username;
    private String msg;
    private int onlineCount;

    public Message(String type, String username, String msg, int onlineCount) {

        this.type = type;
        this.username = username;
        this.msg = msg;
        this.onlineCount = onlineCount;
    }

    public static String jsonConverter(String type, String username, String msg, int onlineCount) {
        return JSON.toJSONString(new Message(type, username, msg, onlineCount));
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setMessage(String msg) {
        this.msg = msg;
    }

    public String getMessage() {
        return this.msg;
    }

    public void setOnlineCount(int onlineCount) {
        this.onlineCount = onlineCount;
    }

    public int getOnlineCount() {
        return onlineCount;
    }
}
