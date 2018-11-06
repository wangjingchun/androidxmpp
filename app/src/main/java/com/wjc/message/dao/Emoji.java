package com.wjc.message.dao;

public class Emoji {
    private String name;
    private String filename;

    public Emoji(String name, String filename) {
        this.name = name;
        this.filename = filename;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getName() {
        return name;
    }

    public String getFilename() {
        return filename;
    }
}
