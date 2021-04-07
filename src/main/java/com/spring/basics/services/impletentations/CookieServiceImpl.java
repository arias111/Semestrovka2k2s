package com.spring.basics.services.impletentations;

import com.spring.basics.models.User;
import com.spring.basics.repositories.CookieRepository;
import com.spring.basics.services.interfaces.CookieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CookieServiceImpl implements CookieService {
    private final CookieRepository cookieRepository;

    // TODO: 26.02.2021 Global exception handler - redirect to cookie creation process
    @Override
    public boolean checkCookie(String cookieValue) {
        return cookieValue != null;
    }

    @Override
    public Cookie createCookie(User user) {
        String value = UUID.randomUUID().toString();
        com.spring.basics.models.Cookie cookie = com.spring.basics.models.Cookie
                .fromValueAndUser(value, user);
        cookieRepository.save(cookie);
        return new Cookie("AuthCookie", value);
    }
}
