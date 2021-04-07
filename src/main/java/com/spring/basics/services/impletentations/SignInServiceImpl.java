package com.spring.basics.services.impletentations;

import com.spring.basics.dto.forms.UserAuthForm;
import com.spring.basics.exceptions.LoginProcessErrorException;
import com.spring.basics.models.User;
import com.spring.basics.repositories.UsersRepository;
import com.spring.basics.services.interfaces.SignInService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SignInServiceImpl implements SignInService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User signIn(UserAuthForm signInForm) throws LoginProcessErrorException {
        User user = usersRepository.findByEmail(signInForm.getEmail())
                .orElseThrow(() -> new LoginProcessErrorException("User not found"));
        boolean passwordResult = passwordEncoder.matches(signInForm.getPassword(), user.getPassword());
        if(passwordResult) return user;
        return null;
    }
}
