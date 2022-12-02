package com.bookmanagement.bookmanagementsystem.service;

import com.bookmanagement.bookmanagementsystem.dao.request.FindAllUserRequest;
import com.bookmanagement.bookmanagementsystem.dao.request.UpdateUserProfileRequest;
import com.bookmanagement.bookmanagementsystem.dao.request.UserRegisterRequest;
import com.bookmanagement.bookmanagementsystem.dao.response.UserRegisterResponse;
import com.bookmanagement.bookmanagementsystem.dto.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest

class UserServiceImplTest {
    UserRegisterResponse savedUser;
    @Autowired
    private UserService userService;


    @BeforeEach
    void setUp() {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setEmail("adesuyiololade@gmail.com");
        userRegisterRequest.setName("Adesuyi Ololade");
        userRegisterRequest.setPhonenumber("08109093828");
        savedUser = userService.registerUser(userRegisterRequest);
    }

    @AfterEach
    void tearDown() {
        userService.deleteAllUsers();
    }

    @Test
    void testThatUserCanBeRegistered(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setEmail("adesuyiololade@gmail.com");
        userRegisterRequest.setName("Adesuyi Ololade");
        userRegisterRequest.setPhonenumber("08109093828");
        UserRegisterResponse response = userService.registerUser(userRegisterRequest);
        assertThat(response.getEmail()).isEqualTo("adesuyiololade@gmail.com");
        assertThat(response.getMessage()).isEqualTo("User successfully registered");
        assertEquals(2L, userService.TotalUsers());


    }
    @Test
    void testThatUserCanBeFoundById(){
        User foundUser = userService.findById(savedUser.getUserId());
        assertThat(foundUser.getId()).isEqualTo(savedUser.getUserId());
    }

    @Test
    void testThatAllUserCanBeFound(){
        FindAllUserRequest findAllUserRequest = new FindAllUserRequest();
        findAllUserRequest.setNumberOfPserPages(1);
        findAllUserRequest.setPageNumber(1);
        assertEquals(1L,userService.findAllUser(findAllUserRequest).getTotalElements());
    }

    @Test
    void testThatAllUserCanBeDeleted(){
        userService.deleteAllUsers();
        assertEquals(0, userService.TotalUsers());
    }
    @Test
    void testThatDeleteUserById(){
        userService.deleteUserById(savedUser.getUserId());
        assertEquals(0, userService.TotalUsers());
    }
    @Test
    void testThatUserProfileCanBeUpdated(){
        UpdateUserProfileRequest updateUserProfileRequest = UpdateUserProfileRequest.builder()
                .email("Iseoluwa@gmail.com")
                .phonenumber("09031807593")
                .name("Iseoluwa")
                .build();
        userService.updateUserProfile(updateUserProfileRequest);
    }

}