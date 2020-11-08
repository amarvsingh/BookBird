package com.example.bookbird;

public class ChatConstructor {
    private String Message;
    private String Sender;
    private String Reciever;

    public ChatConstructor() {
    }

    public ChatConstructor(String message, String sender, String reciever) {
        Message = message;
        Sender = sender;
        Reciever = reciever;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public String getReciever() {
        return Reciever;
    }

    public void setReciever(String reciever) {
        Reciever = reciever;
    }
}
