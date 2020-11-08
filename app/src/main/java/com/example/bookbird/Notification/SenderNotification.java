package com.example.bookbird.Notification;

public class SenderNotification {

    public DataNotification data;
    public String to;

    public SenderNotification(DataNotification data, String to) {
        this.data = data;
        this.to = to;
    }
}
