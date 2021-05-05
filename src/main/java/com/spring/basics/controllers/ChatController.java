package com.spring.basics.controllers;

import com.spring.basics.security.UserDetailsImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;

@Controller
public class ChatController {

    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/chat_page")
    public String getIndexPage(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        model.addAttribute("userid",userDetails.getId());
        return "chat_page";
    }

}
