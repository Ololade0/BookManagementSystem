package com.bookmanagement.bookmanagementsystem.service;

import com.bookmanagement.bookmanagementsystem.dao.request.FindAllUserRequest;
import com.bookmanagement.bookmanagementsystem.dao.request.UpdateUserProfileRequest;
import com.bookmanagement.bookmanagementsystem.dao.request.UserRegisterRequest;
import com.bookmanagement.bookmanagementsystem.dao.response.UserRegisterResponse;
import com.bookmanagement.bookmanagementsystem.dto.model.User;
import com.bookmanagement.bookmanagementsystem.exception.UserCannotBeFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest) throws UserCannotBeFoundException;

    User findById(Long userId);

    long TotalUsers();

    String deleteAllUsers();
    Page<User> findAllUser(FindAllUserRequest findAllUserRequest);

    String deleteUserById(Long userId);

    User updateUserProfile(UpdateUserProfileRequest updateUserProfileRequest);
}
