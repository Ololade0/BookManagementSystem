package com.bookmanagement.bookmanagementsystem.controller;

import com.bookmanagement.bookmanagementsystem.dto.model.Note;
import com.bookmanagement.bookmanagementsystem.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NoteController {
    @Autowired
    private NoteService noteService;

    @PostMapping("/note")
    public ResponseEntity<?> createNote(@RequestBody Note note) {
       Note savedNote = noteService.createNoteBook(note);
        return new ResponseEntity<>(savedNote, HttpStatus.CREATED);
    }

    @PostMapping("/findall-note")
    public ResponseEntity<?> findAllNote(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "limit", defaultValue = "2") int limit){
        List<Note> savedNote = noteService.findAllNote(page, limit);
        return new ResponseEntity<>(savedNote, HttpStatus.CREATED);
    }
}
