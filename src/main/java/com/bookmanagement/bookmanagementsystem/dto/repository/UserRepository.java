package com.bookmanagement.bookmanagementsystem.dto.repository;

import com.bookmanagement.bookmanagementsystem.dto.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserById(long userId);
}
