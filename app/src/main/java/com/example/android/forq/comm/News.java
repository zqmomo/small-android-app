package com.example.android.forq.comm;

/*
 *新闻类
 */

public class News {
    private String title;
    private String content;

    public News() {
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
