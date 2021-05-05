package com.spring.basics.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface ImageSaveService {
    String saveFile(MultipartFile file, Long userId);

    void writeFileToResponse(String fileName, HttpServletResponse response);
}
