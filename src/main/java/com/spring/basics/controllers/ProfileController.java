package com.spring.basics.controllers;

import com.spring.basics.exceptions.ApiRequestException;
import com.spring.basics.models.User;
import com.spring.basics.repositories.CookieRepository;
import com.spring.basics.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class ProfileController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CookieRepository cookieRepository;


    @GetMapping("/profile")
    public String getProfile(@CookieValue(value = "AuthCookie",required = false) String cookie,
                            Model model) {

        model.addAttribute("image",cookieRepository.findByUuid(cookie).get().getUser().getImage());
        return "profile";
    }

    private String filename;

    @Value("${upload.path}")
    private String uploadPath;

    @PostMapping("/profile")
    public String add(@CookieValue(value = "AuthCookie",required = false) String cookie,
                    @RequestParam("file") MultipartFile file,
                    Model model) throws IOException {
        if(file != null){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));

            usersRepository.addImage(cookieRepository.findByUuid(cookie).get().getUser().getId(),resultFilename);
            model.addAttribute("image",resultFilename);
        }
        return "redirect:/profile";
    }
}
