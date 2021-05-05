package com.spring.basics.controllers;

import com.spring.basics.dto.TokenDto;
import com.spring.basics.dto.forms.UserAuthForm;
import com.spring.basics.services.interfaces.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignInRestController {
    @Autowired
    private SignInService signInService;

    @PostMapping("/signInToken")
    public ResponseEntity<TokenDto> signInToken(@RequestBody UserAuthForm userAuthForm) {
        return ResponseEntity.ok(signInService.signInToken(userAuthForm));
    }
}
