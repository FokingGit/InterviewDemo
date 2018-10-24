package com.example.kobe;

public class CharContentBean {

    private int type;
    private String content;
    private int picResId;

    public CharContentBean(int type, String content, int picResId) {
        this.type = type;
        this.content = content;
        this.picResId = picResId;
    }

    public int getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public int getPicResId() {
        return picResId;
    }
}
