package com.wjc.message.dao;

public class MessageText {
    private int type;
    private CharSequence text;

    public MessageText(int type, CharSequence text) {
        this.type = type;
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public CharSequence getText() {
        return text;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setText(CharSequence text) {
        this.text = text;
    }
}
