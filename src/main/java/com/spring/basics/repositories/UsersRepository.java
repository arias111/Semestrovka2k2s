package com.spring.basics.repositories;

import com.spring.basics.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UsersRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

    Optional<User> findByCurrentConfirmationCode(String code);

    @Query(nativeQuery = true,value = "UPDATE users SET image = :image WHERE id = :id returning 1")
    void addImage(Long id,String image);
}
