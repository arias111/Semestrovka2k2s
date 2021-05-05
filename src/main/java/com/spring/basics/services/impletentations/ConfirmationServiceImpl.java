package com.spring.basics.services.impletentations;

import com.spring.basics.models.User;
import com.spring.basics.repositories.UsersRepository;
import com.spring.basics.services.interfaces.ConfirmationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConfirmationServiceImpl implements ConfirmationService {
    private final UsersRepository usersRepository;
    //@Transactional
    @Override
    public boolean confirmByCode(String code) {
        Optional<User> userCandidate = usersRepository.findByCurrentConfirmationCode(code);
        if (userCandidate.isPresent()) {
            User user = userCandidate.get();
            user.setProved(true);
            usersRepository.save(user);
        }
        return userCandidate.isPresent();
    }
}
