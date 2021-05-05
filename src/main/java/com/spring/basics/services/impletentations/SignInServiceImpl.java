package com.spring.basics.services.impletentations;

import com.spring.basics.dto.TokenDto;
import com.spring.basics.dto.forms.UserAuthForm;
import com.spring.basics.exceptions.LoginProcessErrorException;
import com.spring.basics.models.User;
import org.springframework.security.access.AccessDeniedException;
import com.spring.basics.repositories.UsersRepository;
import com.spring.basics.services.interfaces.SignInService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


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

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public TokenDto signInToken(UserAuthForm userAuthForm) {
        Optional<User> userOptional = usersRepository.findByEmail(userAuthForm.getEmail());
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            if(passwordEncoder.matches(userAuthForm.getPassword(),user.getPassword())) {
                String token = Jwts.builder()
                        .setSubject(user.getId().toString())
                        .claim("name",user.getUsername())
                        .claim("role",user.getRole().name())
                        .signWith(SignatureAlgorithm.HS256,secret)
                        .compact();
                return new TokenDto(token);
            } else throw new AccessDeniedException("wrong data");
        } else throw new AccessDeniedException("User not found");
    }
}
