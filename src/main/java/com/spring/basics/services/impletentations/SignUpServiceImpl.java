package com.spring.basics.services.impletentations;

import com.spring.basics.dto.UserDto;
import com.spring.basics.dto.forms.SignUpForm;
import com.spring.basics.models.User;
import com.spring.basics.repositories.UsersRepository;
import com.spring.basics.services.interfaces.SignUpService;
import com.spring.basics.services.interfaces.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {
    private final BCryptPasswordEncoder encoder;
    private final UsersRepository usersRepository;

    @Autowired
    private SmsService smsService;

    @Override
    public UserDto signUp(SignUpForm form) {
        if (usersRepository.existsByEmail(form.getEmail())) return null;
        User user = User.fromSignUpForm(form);
        user.setCurrentConfirmationCode(UUID.randomUUID().toString());
        user.setPassword(encoder.encode(form.getPassword()));
        usersRepository.save(user);
        smsService.sendSms(form.getPhone(),"Вы зарегистрировались");
        return UserDto.fromUser(user);
    }
}
