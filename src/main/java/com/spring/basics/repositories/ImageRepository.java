package com.spring.basics.repositories;

import com.spring.basics.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findByAndStorageFileName(String fileName);
}
