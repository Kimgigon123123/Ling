package com.example.ling.chat;

import java.io.Serializable;

public class ChatData implements Serializable {
    private String nickname, message;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
