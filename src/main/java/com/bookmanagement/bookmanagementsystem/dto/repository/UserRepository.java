package com.bookmanagement.bookmanagementsystem.dto.repository;

import com.bookmanagement.bookmanagementsystem.dto.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserById(long userId);


    Optional<User> findUserByEmail(String email);

    @Query("SELECT u FROM user u JOIN u.noteList n ON u.id = n.user.id WHERE u.name = :name and n.content = :content")
    List<User> findUserByNameAndNoteList(@Param("name") String username, @Param("content") String content);
}

