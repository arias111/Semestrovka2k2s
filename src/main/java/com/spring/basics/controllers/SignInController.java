package com.spring.basics.controllers;

import com.spring.basics.dto.forms.UserAuthForm;
import com.spring.basics.exceptions.ApiRequestException;
import com.spring.basics.exceptions.LoginProcessErrorException;
import com.spring.basics.models.User;
import com.spring.basics.services.interfaces.CookieService;
import com.spring.basics.services.interfaces.SignInService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/signIn")
@RequiredArgsConstructor
public class SignInController {
    private final CookieService cookieService;
    private final SignInService signInService;

    @Value("${auth.user.redirect.url}")
    private String authUserRedirectUrl;

    @GetMapping
    public String getSignInPage(@CookieValue(value = "AuthCookie", required = false) String cookieValue,
                                @RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "info", required = false) String info,
                                Model model) {
        if (cookieService.checkCookie(cookieValue)) {
            return authUserRedirectUrl;
        }
        model.addAttribute("error", error);
        model.addAttribute("info", info);
        return "sign_in_page";
    }

    @PostMapping
    @SneakyThrows(LoginProcessErrorException.class)
    public String signInUser(@CookieValue(value = "AuthCookie", required = false) String cookieValue,
                             @Valid UserAuthForm signInForm,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             HttpServletResponse httpServletResponse) {
        if (cookieService.checkCookie(cookieValue)) {
            return authUserRedirectUrl;
        }
        if (bindingResult.hasErrors()) {
            List<String> errorsList = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            redirectAttributes.addAttribute("error", errorsList.toString());
            return "redirect:/signIn";
        }
        User user = null;
        if ((user = signInService.signIn(signInForm)) != null) {
            Cookie cookie = cookieService.createCookie(user);
            httpServletResponse.addCookie(cookie);
            return authUserRedirectUrl;
        }
        redirectAttributes.addAttribute("error", "Login on password incorrect");
        return "redirect:/signIn";
    }
}
