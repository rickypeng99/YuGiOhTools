package com.example.rickypeng99.yugiohui;

/**
 * Created by Ricky on 2017/6/12.
 */

public class Data {
    private String content;
    private String time;
    private int player;

    public Data(String content, String time, int player) {
        this.content = content;
        this.time = time;
        this.player = player;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }
}
