package com.spring.basics.services.impletentations;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import com.spring.basics.models.Image;
import com.spring.basics.repositories.ImageRepository;
import com.spring.basics.services.interfaces.ImageSaveService;
import com.spring.basics.services.interfaces.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class ImageSaveServiceImpl implements ImageSaveService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UsersService usersService;

    @Value("${upload.path}")
    public String storagePath;

    @Override
    public String saveFile(MultipartFile uploadingFile, Long userId) {
        String storageName = UUID.randomUUID().toString() + "." +
                FilenameUtils.getExtension(uploadingFile.getOriginalFilename());
        Image image = Image.builder()
                .type(uploadingFile.getContentType())
                .originalFileName(uploadingFile.getOriginalFilename())
                .storageFileName(storageName)
                .size(uploadingFile.getSize())
                .url(storagePath + "/" + storageName)
                .build();

        try {
            Files.copy(uploadingFile.getInputStream(), Paths.get(storagePath, storageName));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        imageRepository.save(image);

        return storageName;
    }

    @Override
    public void writeFileToResponse(String fileName, HttpServletResponse response) {
        Image fileInfo = imageRepository.findByAndStorageFileName(fileName);
        response.setContentType(fileInfo.getType());
        try {
            IOUtils.copy(new FileInputStream(fileInfo.getUrl()), response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}

