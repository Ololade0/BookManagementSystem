package com.bookmanagement.bookmanagementsystem.service;

import com.bookmanagement.bookmanagementsystem.dao.request.CreateNotesRequest;
import com.bookmanagement.bookmanagementsystem.dao.request.FindAllNoteRequest;
import com.bookmanagement.bookmanagementsystem.dao.request.UpdateNoteRequest;
import com.bookmanagement.bookmanagementsystem.dto.model.Note;
import com.bookmanagement.bookmanagementsystem.exception.NoteCannotBeFoundException;

import java.util.List;

public interface NoteService {
    Note createNoteBook(CreateNotesRequest note);

    long totalNoOfNotes();

    void deleteAllNotes();

    Note findNoteById(Long id) throws NoteCannotBeFoundException;

    String deleteNoteById(Long id) throws NoteCannotBeFoundException;
        List<Note> findAllNote(int page, int limit);

    Note updateNote(UpdateNoteRequest updateNoteRequest, Long id) throws NoteCannotBeFoundException;
}
