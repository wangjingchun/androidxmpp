package com.wjc.message.dao;

public class MessageText {
    private int type;
    private CharSequence text;
    private String image;

    public MessageText(int type, CharSequence text) {
        this.type = type;
        this.text = text;
    }

    public MessageText(int type, CharSequence text, String image) {
        this.type = type;
        this.text = text;
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public CharSequence getText() {
        return text;
    }

    public String getImage() {
        return image;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setText(CharSequence text) {
        this.text = text;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
