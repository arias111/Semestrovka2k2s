package com.spring.basics.controllers;

import com.spring.basics.dto.UserDto;
import com.spring.basics.exceptions.ApiRequestException;
import com.spring.basics.models.User;
import com.spring.basics.repositories.CookieRepository;
import com.spring.basics.repositories.UsersRepository;
import com.spring.basics.security.UserDetailsImpl;
import com.spring.basics.services.interfaces.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private UsersService usersService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String getProfile(@AuthenticationPrincipal UserDetailsImpl user, Model model) {
        model.addAttribute("image",usersRepository.findByEmail(user.getUsername()).get().getImage());
        UserDto dto = usersService.getUserDto(user.getId());
        model.addAttribute("userDto",dto);
        model.addAttribute("userInfo",usersRepository.findById(user.getId()));
        return "profile";
    }

    @PutMapping("/profile/{user-id}")
    @ResponseBody
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto dto,@PathVariable("user-id") Long userId) {
        boolean isChanged = usersService.updateUser(userId, dto);
        UserDto newDto = usersService.getUserById(userId);
        return ResponseEntity.ok(newDto);
    }

    private String filename;

    @Value("${upload.path}")
    private String uploadPath;

    @PostMapping("/profile")
    public String add(@AuthenticationPrincipal UserDetailsImpl user,
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
            usersRepository.addImage(user.getId(),resultFilename);
            model.addAttribute("image",resultFilename);
        }
        return "redirect:/profile";
    }
}
