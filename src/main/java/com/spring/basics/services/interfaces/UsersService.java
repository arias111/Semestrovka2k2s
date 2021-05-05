package com.spring.basics.services.interfaces;


import com.spring.basics.dto.UserDto;
import com.spring.basics.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {

    Boolean updateUser(Long id, UserDto newDto);
     UserDto getUserById(Long userId);
    UserDto getUserDto(String email);
    UserDto getUserDto(Long id);
    List<UserDto> getAllUsers();
    void deleteUser(Long userId);

}