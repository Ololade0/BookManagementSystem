package com.bookmanagement.bookmanagementsystem.service;

import com.bookmanagement.bookmanagementsystem.dao.request.FindAllNoteRequest;
import com.bookmanagement.bookmanagementsystem.dto.model.Note;
import com.bookmanagement.bookmanagementsystem.exception.NoteCannotBeFoundException;

import java.util.List;

public interface NoteService {
    Note createNoteBook(Note note);

    long totalNoOfNotes();

    void deleteAllNotes();

    Note findNoteById(Long id) throws NoteCannotBeFoundException;

    String deleteNoteById(Long id) throws NoteCannotBeFoundException;

    List<Note> findAllNotes(FindAllNoteRequest findAllNoteRequest);

    List<Note> findAllNote(int page, int limit);

}
