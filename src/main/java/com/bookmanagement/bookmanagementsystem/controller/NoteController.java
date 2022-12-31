package com.bookmanagement.bookmanagementsystem.controller;

import com.bookmanagement.bookmanagementsystem.dao.request.*;
import com.bookmanagement.bookmanagementsystem.dao.response.CreateNoteResponse;
import com.bookmanagement.bookmanagementsystem.dao.response.UpdateNoteResponse;
import com.bookmanagement.bookmanagementsystem.dao.response.UserRegisterResponse;
import com.bookmanagement.bookmanagementsystem.dto.model.AuthToken;
import com.bookmanagement.bookmanagementsystem.dto.model.Note;
import com.bookmanagement.bookmanagementsystem.dto.model.User;
import com.bookmanagement.bookmanagementsystem.exception.NoteCannotBeFoundException;
import com.bookmanagement.bookmanagementsystem.exception.UserCannotBeFoundException;

import com.bookmanagement.bookmanagementsystem.security.jwt.TokenProvider;
import com.bookmanagement.bookmanagementsystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class NoteController {
    @Autowired
    private UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        try {
            UserRegisterResponse user = userService.registerUser(userRegisterRequest);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (UserCannotBeFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("{userId}")
    public ResponseEntity<?> findUserById(@PathVariable Long userId) {
        try {
            User foundUser = userService.findById(userId);
            return new ResponseEntity<>(foundUser, HttpStatus.CREATED);
        } catch (UserCannotBeFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("{email}")
    public ResponseEntity<?> findUserByEmail(@PathVariable String email) {
        try {
            User foundUser = userService.findUserByEmail(email);
            return new ResponseEntity<>(foundUser, HttpStatus.CREATED);
        } catch (UserCannotBeFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/findAllUsers")
    public ResponseEntity<?> findUserAllUser(@RequestBody FindAllUserRequest findAllUserRequest) {
        try {
            Page<User> foundUsers = userService.findAllUser(findAllUserRequest);
            return new ResponseEntity<>(foundUsers, HttpStatus.CREATED);
        } catch (UserCannotBeFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllUsers() {
        try {
            String deletedUsers = userService.deleteAllUsers();
            return new ResponseEntity<>(deletedUsers, HttpStatus.CREATED);
        } catch (UserCannotBeFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long userId) {
        try {
            String deletedUser = userService.deleteUserById(userId);
            return new ResponseEntity<>(deletedUser, HttpStatus.CREATED);
        } catch (UserCannotBeFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping()
    public ResponseEntity<?> updateUserProfile(@RequestBody UpdateUserProfileRequest updateUserProfileRequest) {
        try {
            User updateUserProfile = userService.updateUserProfile(updateUserProfileRequest);
            return new ResponseEntity<>(updateUserProfile, HttpStatus.CREATED);
        } catch (UserCannotBeFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequestModel loginRequest) throws UserCannotBeFoundException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                        loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenProvider.generateJWTToken(authentication);
        User user = userService.findUserByEmail(loginRequest.getEmail());
        return new ResponseEntity<>(new AuthToken(token, user.getId()), HttpStatus.OK);
    }

    @PostMapping("/note")
    public ResponseEntity<?> createNote(@RequestBody CreateNotesRequest note) {
        try {
            CreateNoteResponse createNoteResponse = userService.createNote(note);
            return new ResponseEntity<>(createNoteResponse, HttpStatus.CREATED);
        } catch (UserCannotBeFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/notes")
    public ResponseEntity<?> findNoteById(@RequestBody FindNoteByIdRequest findNoteByIdRequest) throws NoteCannotBeFoundException {
        Note savedNote = userService.findNoteById(findNoteByIdRequest);
        return new ResponseEntity<>(savedNote, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> findAllNote(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<Note> savedNote = userService.findAllNotes(page, limit);
        return new ResponseEntity<>(savedNote, HttpStatus.CREATED);
    }

    @DeleteMapping("/deletenote")
    public ResponseEntity<?> deleteNoteById(@RequestBody DeleteNoteByIdRequest deleteNoteByIdRequest) {
        try {
            String deletedUser = userService.deleteNoteById(deleteNoteByIdRequest);
            return new ResponseEntity<>(deletedUser, HttpStatus.CREATED);
        }
        catch (NoteCannotBeFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deletallenote")
    public ResponseEntity<?> deleteAllNote() {
        String deletedUser = userService.deleteAllNotes();
        return new ResponseEntity<>(deletedUser, HttpStatus.CREATED);
    }


    @PutMapping("{id}")
    public ResponseEntity<?> updateNoteProfile(@RequestBody UpdateNoteRequest updateNoteRequest, @PathVariable Long id) {
        try {
            UpdateNoteResponse updateUserProfile = userService.updateNote(updateNoteRequest, id);
            return new ResponseEntity<>(updateUserProfile, HttpStatus.CREATED);
        }
        catch (NoteCannotBeFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        }

    }



