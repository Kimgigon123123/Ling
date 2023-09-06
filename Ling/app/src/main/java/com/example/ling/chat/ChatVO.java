package com.example.ling.chat;

import java.io.Serializable;

public class ChatVO implements Serializable {
    private String nickname, message, id, couple_num, time, key;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCouple_num() {
        return couple_num;
    }

    public void setCouple_num(String couple_num) {
        this.couple_num = couple_num;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
