package com.spring.basics.services.interfaces;

import com.spring.basics.dto.TokenDto;
import com.spring.basics.dto.forms.UserAuthForm;
import com.spring.basics.exceptions.LoginProcessErrorException;
import com.spring.basics.models.User;

public interface SignInService {
    User signIn(UserAuthForm signInForm) throws LoginProcessErrorException;
    TokenDto signInToken(UserAuthForm userAuthForm);
}
