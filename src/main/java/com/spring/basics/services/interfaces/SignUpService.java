package com.spring.basics.services.interfaces;

import com.spring.basics.dto.UserDto;
import com.spring.basics.dto.forms.SignUpForm;

public interface SignUpService {
    UserDto signUp(SignUpForm form);
}
