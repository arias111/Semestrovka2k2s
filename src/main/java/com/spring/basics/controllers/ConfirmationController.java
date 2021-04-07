package com.spring.basics.controllers;

import com.spring.basics.services.interfaces.ConfirmationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/confirm")
@RequiredArgsConstructor
public class ConfirmationController {
    private final ConfirmationService confirmationService;

    @GetMapping("/{key}")
    public String confirmAccount(@PathVariable(name = "key") String code,
                                 RedirectAttributes redirectAttributes) {
        boolean confirmationResult = confirmationService.confirmByCode(code);
        String redirectUrl = confirmationResult ? "redirect:/profile" : "redirect:/signIn";
        String infoMessage = confirmationResult ? "Successfully confirmed" : "Code not found";
        redirectAttributes.addAttribute("info", infoMessage);
        return redirectUrl;
    }
}
