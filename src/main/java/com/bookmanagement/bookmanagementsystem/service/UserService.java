package com.bookmanagement.bookmanagementsystem.service;

import com.bookmanagement.bookmanagementsystem.dao.request.*;
import com.bookmanagement.bookmanagementsystem.dao.response.CreateNoteResponse;
import com.bookmanagement.bookmanagementsystem.dao.response.UpdateNoteResponse;
import com.bookmanagement.bookmanagementsystem.dao.response.UserLoginResponse;
import com.bookmanagement.bookmanagementsystem.dao.response.UserRegisterResponse;
import com.bookmanagement.bookmanagementsystem.dto.model.Note;
import com.bookmanagement.bookmanagementsystem.dto.model.User;
import com.bookmanagement.bookmanagementsystem.exception.NoteCannotBeFoundException;
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

    CreateNoteResponse createNote(CreateNotesRequest createNotesRequest);

    long totalNotes();

    String deleteAllNotes();

    Note findNoteById(FindNoteByIdRequest findNoteByIdRequest) throws NoteCannotBeFoundException;

    String deleteNoteById(DeleteNoteByIdRequest deleteNoteByIdRequest) throws NoteCannotBeFoundException;

    UpdateNoteResponse updateNote(UpdateNoteRequest updateNoteRequest, Long noteId) throws NoteCannotBeFoundException;

    List<Note> findAllNotes(int page, int limit);


    User findUserByEmail(String username);


      UserLoginResponse loginUser(UserLoginRequestModel userLoginRequestModel);
}
