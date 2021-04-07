package com.spring.basics.dto;

import com.spring.basics.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
