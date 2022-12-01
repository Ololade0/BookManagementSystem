package com.bookmanagement.bookmanagementsystem.dto.repository;

import com.bookmanagement.bookmanagementsystem.dto.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
