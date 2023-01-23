package com.bookmanagement.bookmanagementsystem.service;
import com.bookmanagement.bookmanagementsystem.controller.NoteController;
import com.bookmanagement.bookmanagementsystem.dao.request.*;
import com.bookmanagement.bookmanagementsystem.dao.response.CreateNoteResponse;
import com.bookmanagement.bookmanagementsystem.dao.response.UpdateNoteResponse;
import com.bookmanagement.bookmanagementsystem.dao.response.UserLoginResponse;
import com.bookmanagement.bookmanagementsystem.dao.response.UserRegisterResponse;
import com.bookmanagement.bookmanagementsystem.dto.model.Note;
import com.bookmanagement.bookmanagementsystem.dto.model.User;
import com.bookmanagement.bookmanagementsystem.exception.NoteCannotBeFoundException;
import org.aspectj.weaver.ast.Not;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest

class UserServiceImplTest {
    CreateNoteResponse createdNote;
    UserRegisterResponse savedUser;
    @Autowired
    private UserService userService;

    @Autowired
    private NoteController noteController;




    @BeforeEach
    void setUp() {

        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setEmail("adesuyiololade@gmail.com");
        userRegisterRequest.setName("Adesuyi Ololade");
        userRegisterRequest.setPhonenumber("08109093828");
        userRegisterRequest.setPassword("1234");
        savedUser = userService.registerUser(userRegisterRequest);



        CreateNotesRequest createNotesRequest = CreateNotesRequest.builder()
                .userId(savedUser.getUserId())
                .body("Ololades NOteBody")
                .title("Ololades NoteTitle")
                .content("Ololade Content")
                .createdAt(LocalDateTime.now())
                .build();
        createdNote =  userService.createNote(createNotesRequest);
    }

    @AfterEach
    void tearDown() {

        userService.deleteAllUsers();
        userService.deleteAllNotes();
    }

    @Test
    void testThatUserCanBeRegistered(){

        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setEmail("adesuyiololade@gmail.com");
        userRegisterRequest.setName("Adesuyi Ololade");
        userRegisterRequest.setPhonenumber("08109093828");
        userRegisterRequest.setPassword("1234");

        savedUser = userService.registerUser(userRegisterRequest);
        assertThat(savedUser.getEmail()).isEqualTo("adesuyiololade@gmail.com");
        assertThat(savedUser.getMessage()).isEqualTo("User successfully registered");
        assertThat(savedUser.getUserId()).isNotNull();




    }
    @Test
    void testThatUserCanBeFoundById(){
        User foundUser = userService.findById(savedUser.getUserId());
        assertThat(foundUser.getId()).isEqualTo(savedUser.getUserId());
    }



    @Test
    void testThatAllUserCanBeFound(){
        FindAllUserRequest findAllUserRequest = new FindAllUserRequest();
        findAllUserRequest.setNumberOfPerPages(1);
        findAllUserRequest.setPageNumber(1);
        assertThat(userService.findAllUser(findAllUserRequest).getTotalElements()).isNotNull();
        assertThat(userService.findAllUser(findAllUserRequest).getTotalElements()).isGreaterThan(0);

    }

    @Test
    void testThatAllUserCanBeDeleted(){
        userService.deleteAllUsers();
        assertEquals(0, userService.TotalUsers());
    }
    @Test
    void testThatDeleteUserById(){
        userService.deleteUserById(savedUser.getUserId());

    }
    @Test
    void testThatUserProfileCanBeUpdated(){
        UpdateUserProfileRequest updateUserProfileRequest = UpdateUserProfileRequest.builder()
                .userId(savedUser.getUserId())
                .email("Iseoluwa@gmail.com")
                .phonenumber("09031807593")
                .name("Iseoluwa")
                .build();
        User updatedUser =   userService.updateUserProfile(updateUserProfileRequest);
        assertEquals("Iseoluwa@gmail.com", updatedUser.getEmail());
    }


    @Test
    public void UserCanLogin() {
        UserLoginRequestModel userLoginRequestModel = new UserLoginRequestModel();
        userLoginRequestModel.setPassword(savedUser.getPassword());
        userLoginRequestModel.setEmail(savedUser.getEmail());
        UserLoginResponse response =  userService.loginUser(userLoginRequestModel);
        assertEquals("Login successful", response.getMessage());
//       assertEquals(200, response.getCode());

    }

    @Test
    void testThatUserCanCreateNotes(){
        CreateNotesRequest createNotesRequest = CreateNotesRequest.builder()
                .userId(savedUser.getUserId())
                .body("Ololades NOteBody")
                .title("Ololades NoteTitle")
                .content("Ololade Content")
                .createdAt(LocalDateTime.now())
                .build();
        userService.createNote(createNotesRequest);
        assertEquals(2L, userService.totalNotes());
    }
    @Test
    void testThatUserCanFoundById() throws NoteCannotBeFoundException {
        FindNoteByIdRequest findNoteByIdRequest = FindNoteByIdRequest
                .builder()
                .userId(savedUser.getUserId())
                .noteId(createdNote.getId())
                .build();
        Note foundNote =    userService.findNoteById(findNoteByIdRequest);
        assertThat(foundNote.getId()).isEqualTo(createdNote.getId());
        assertThat(foundNote).isNotNull();
    }

    @Test
    void testThatUserCanFoundByEmail(){
        User foundUser = userService.findUserByEmail(savedUser.getEmail());
        assertEquals("adesuyiololade@gmail.com", foundUser.getEmail());

    }
    @Test
    void testThatAllNoteCanBeFound(){
        FindAllNoteRequest findAllNoteRequest = FindAllNoteRequest
                .builder()
                .limit(1)
                .page(1)
                .build();
        List<Note> noteList=  userService.findAllNotes(findAllNoteRequest.getPage(), findAllNoteRequest.getLimit());
        assertEquals("Ololades NOteBody", noteList.get(0).getBody());

    }

    @Test
    void testThatUserCanDeletedById() throws NoteCannotBeFoundException {
        DeleteNoteByIdRequest deleteNoteByIdRequest = DeleteNoteByIdRequest
                .builder()
                .userId(savedUser.getUserId())
                .noteId(createdNote.getId())
                .build();
        String deletedNote =   userService.deleteNoteById(deleteNoteByIdRequest);
        assertEquals(0, userService.totalNotes());
        assertThat(deletedNote).isNotNull();
    }

    @Test
    void testThatUserCanDeleteAllNotes(){
        userService.deleteAllNotes();
        assertEquals(0, userService.totalNotes());
    }

    @Test
    void testThatUserCanUpdateNoteProfile() throws NoteCannotBeFoundException {
        UpdateNoteRequest updateNoteRequest = UpdateNoteRequest.builder()
                .updatedAt(LocalDateTime.now())
                .userId(savedUser.getUserId())
                .body("Ololades NoteBody")
                .title("Ololades NoteTitle")
                .content("Ololade Content")
                .build();
        UpdateNoteResponse updateNoteResponse = userService.updateNote(updateNoteRequest, createdNote.getId());
        assertEquals("Note Successfully Updated", updateNoteResponse.getMessage());

    }



}