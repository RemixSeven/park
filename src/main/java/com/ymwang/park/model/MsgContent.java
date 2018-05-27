package com.ymwang.park.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;


public class MsgContent {
    private String id;
    private String message;
    private String title;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
