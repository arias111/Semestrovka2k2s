package com.spring.basics.repositories;

import com.spring.basics.models.Cookie;
import com.spring.basics.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CookieRepository extends JpaRepository<Cookie, String> {

    Optional<Cookie> findByUuid(String uuid);

}
