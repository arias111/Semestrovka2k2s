package com.spring.basics.controllers;

import com.spring.basics.dto.UserDto;
import com.spring.basics.repositories.UsersRepository;
import com.spring.basics.security.UserDetailsImpl;
import com.spring.basics.services.interfaces.MailService;
import com.spring.basics.services.interfaces.SenderService;
import com.spring.basics.services.interfaces.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserInfoController {
    @Autowired
    private UsersRepository usersRepository;

    private final MailService mailService;
    private final SenderService senderService;
    @Autowired
    private UsersService usersService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/getUsers")
    public String getUsersPage(@AuthenticationPrincipal UserDetailsImpl userDetails,Model model) {
        UserDto dto = usersService.getUserDto(userDetails.getId());
        model.addAttribute("userDto",dto);
        model.addAttribute("userList",usersRepository.findById(userDetails.getId()));
        String message = "Your details" + userDetails.getUsername() + " " +userDetails.getId() + " " + userDetails.getUserName();
        senderService.sendMessage("userDetails",userDetails.getUsername(),message);
        return "users";
    }
}
