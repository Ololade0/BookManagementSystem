package com.bookmanagement.bookmanagementsystem.service;

import com.bookmanagement.bookmanagementsystem.dao.request.FindAllUserRequest;
import com.bookmanagement.bookmanagementsystem.dao.request.UpdateUserProfileRequest;
import com.bookmanagement.bookmanagementsystem.dao.request.UserRegisterRequest;
import com.bookmanagement.bookmanagementsystem.dao.response.UserRegisterResponse;
import com.bookmanagement.bookmanagementsystem.dto.model.Note;
import com.bookmanagement.bookmanagementsystem.dto.model.User;
import com.bookmanagement.bookmanagementsystem.dto.repository.UserRepository;
import com.bookmanagement.bookmanagementsystem.exception.UserCannotBeFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final NoteService noteService;


    @Override
    public UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest) throws UserCannotBeFoundException {
        User user = new User();
        user.setEmail(userRegisterRequest.getEmail());
        user.setName(userRegisterRequest.getName());
        user.setPhonenumber(userRegisterRequest.getPhonenumber());
        User savedUser = userRepository.save(user);
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setMessage("User successfully registered");
        userRegisterResponse.setUserId(savedUser.getId());
        userRegisterResponse.setEmail(savedUser.getEmail());
        return userRegisterResponse;
    }

    @Override
    public User findById(Long userId) {
     Optional<User> foundUser =  userRepository.findUserById(userId);
     if(foundUser.isEmpty()){
         throw new UserCannotBeFoundException(UserCannotBeFoundException.UserCannotBeFoundException(userId));
     }
     else {
         return foundUser.get();

     }

    }

    @Override
    public long TotalUsers() {
        return userRepository.count();
    }

    @Override
    public String deleteAllUsers() {
        userRepository.deleteAll();
        return "All users successfully deleted";

    }



    @Override
    public Page<User> findAllUser(FindAllUserRequest findAllUserRequest) {
        Pageable pageable = PageRequest.of(findAllUserRequest.getPageNumber()-1, findAllUserRequest.getNumberOfPserPages());
        return userRepository.findAll(pageable);
    }

    @Override
    public String deleteUserById(Long userId) {
       Optional<User> foundUser = userRepository.findUserById(userId);
       if(foundUser.isPresent()){
           userRepository.deleteById(userId);
           return "User successfully deleted";
       }

        else {
            throw new UserCannotBeFoundException(UserCannotBeFoundException.UserCannotBeFoundException(userId));

       }
    }

    @Override
    public User updateUserProfile(UpdateUserProfileRequest updateUserProfileRequest) {
        
        return null;
    }


}
