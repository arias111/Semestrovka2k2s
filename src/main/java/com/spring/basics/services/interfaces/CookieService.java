package com.spring.basics.services.interfaces;

import com.spring.basics.models.User;

import javax.servlet.http.Cookie;


public interface CookieService {
    boolean checkCookie(String cookieValue);

    Cookie createCookie(User userDto);
}
