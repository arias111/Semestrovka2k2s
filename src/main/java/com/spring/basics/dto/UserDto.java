package com.spring.basics.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.basics.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto {
    private Long id;
    private String username;
    private String code;
    private String email;
    public static UserDto fromUser(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .id(user.getId())
                .code(user.getCurrentConfirmationCode())
                .email(user.getEmail())
                .build();
    }

    public static List<UserDto> fromUser(List<User> users) {
        return users.stream()
                .map(UserDto::fromUser)
                .collect(Collectors.toList());
    }
}
