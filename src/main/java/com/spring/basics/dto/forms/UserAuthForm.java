package com.spring.basics.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserAuthForm {
    @Email(message = "email is wrong")
    private String email;
    @NotBlank(message = "password is mandatory")
    private String password;
}
