package com.spring.basics.services.interfaces;

import com.spring.basics.dto.UserDto;

public interface MailService {
    void sendMail(UserDto userDto);
}
