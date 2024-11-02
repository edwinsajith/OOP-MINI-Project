package com.example.demo;

import java.time.LocalDateTime;

public class Message {
    private String sender;
    private String recipient;
    private String content;
    private LocalDateTime timestamp;

    public Message(String sender, String recipient, String content) {
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "[" + timestamp + "] " + sender + " to " + recipient + ": " + content;
    }
}
