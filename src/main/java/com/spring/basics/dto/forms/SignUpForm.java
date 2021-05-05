package com.spring.basics.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class SignUpForm extends UserAuthForm{
    @NotBlank(message = "username is mandatory")
    private String username;
    @Email(message = "incorrect email")
    private String email;
    private String phone;
}
