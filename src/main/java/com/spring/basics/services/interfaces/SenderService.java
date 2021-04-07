package com.spring.basics.services.interfaces;


public interface SenderService {
    void sendMessage(String subject, String email, String html);
}
