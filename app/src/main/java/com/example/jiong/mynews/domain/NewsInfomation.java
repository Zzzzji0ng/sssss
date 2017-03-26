package com.example.jiong.mynews.domain;

import java.io.Serializable;

/**
 * Created by Jiong on 2017/3/26.
 */
public class NewsInfomation implements Serializable {
    private String title;
    private String url;
    private String from;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String toString() {
        return "NewsInfomation{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", from='" + from + '\'' +
                '}';
    }
}

