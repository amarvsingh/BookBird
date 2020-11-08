package com.example.bookbird;

public class InboxConstructor {
    private String OtherUsername;

    public InboxConstructor() {
    }

    public InboxConstructor(String otherUsername) {
        OtherUsername = otherUsername;
    }

    public String getOtherUsername() {
        return OtherUsername;
    }

    public void setOtherUsername(String otherUsername) {
        OtherUsername = otherUsername;
    }
}
