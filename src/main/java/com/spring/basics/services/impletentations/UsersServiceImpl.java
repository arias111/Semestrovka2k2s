package com.spring.basics.services.impletentations;

import com.spring.basics.dto.UserDto;
import com.spring.basics.models.User;
import com.spring.basics.repositories.UsersRepository;
import com.spring.basics.services.interfaces.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.spring.basics.dto.UserDto.*;

@Component
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Boolean updateUser(Long id, UserDto newDto) {
        boolean isChanged = false;
        Optional<User> optionalUser = usersRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (!newDto.getEmail().equals("")) {
                user.setEmail(newDto.getEmail());
                isChanged = true;
            }
            if (!newDto.getUsername().equals("")) {
                user.setUsername(newDto.getUsername());
                isChanged = true;
            }
            if (isChanged) {
                usersRepository.save(user);
            }
        }
        return isChanged;
    }

    @Override
    public UserDto getUserById(Long userId) {
        Optional<User> user = usersRepository.findById(userId);
        return UserDto.fromUser(user.orElse(User.builder().build()));
    }

    @Override
    public UserDto getUserDto(String email) {
        Optional<User> user = usersRepository.findByEmail(email);
        UserDto dto = UserDto.builder().build();

        if (user.isPresent()) {
            dto = builder()
                    .id(user.get().getId())
                    .email(user.get().getEmail())
                    .username(user.get().getUsername())
                    .build();
        }
        return dto;
    }

    public UserDto getUserDto(Long id) {
        Optional<User> user = usersRepository.findById(id);
        UserDto dto = UserDto.builder().build();

        if (user.isPresent()) {
            dto = builder()
                    .id(user.get().getId())
                    .email(user.get().getEmail())
                    .username(user.get().getUsername())
                    .build();
        }
        return dto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return UserDto.fromUser(usersRepository.findAll());
    }

    @Override
    public void deleteUser(Long userId) {
        usersRepository.deleteById(userId);
    }

}