package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Communication {
    private final List<Message> messages;

    public Communication() {
        messages = new ArrayList<>();
    }

    public void sendMessage(String sender, String recipient, String content) {
        Message message = new Message(sender, recipient, content);
        messages.add(message);
    }

    public List<Message> getMessages() {
        return messages;
    }
}
