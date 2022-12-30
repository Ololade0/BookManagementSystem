package com.bookmanagement.bookmanagementsystem.service;
import com.bookmanagement.bookmanagementsystem.dao.request.CreateNotesRequest;
import com.bookmanagement.bookmanagementsystem.dao.request.UpdateNoteRequest;
import com.bookmanagement.bookmanagementsystem.dto.model.Note;
import com.bookmanagement.bookmanagementsystem.dto.repository.NoteRepository;
import com.bookmanagement.bookmanagementsystem.exception.NoteCannotBeFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService{
    private final NoteRepository noteRepository;
    @Override
    public Note createNoteBook(CreateNotesRequest note) {
        Note savedNotes = Note.builder()
                .body(note.getBody())
                .content(note.getContent())
                .title(note.getTitle())
                .createdAt(LocalDateTime.now())
                .build();
        return noteRepository.save(savedNotes);
    }

    @Override
    public long totalNoOfNotes() {
        return noteRepository.count();
    }

    @Override
    public void deleteAllNotes() {
        noteRepository.deleteAll();

    }

    @Override
    public Note findNoteById(Long id) throws NoteCannotBeFoundException {
        Optional<Note>foundNote = noteRepository.findNoteById(id);
        if(foundNote.isEmpty()){
            throw new NoteCannotBeFoundException(NoteCannotBeFoundException.NoteCannotBeFoundException(id));
        }

        else {
            return foundNote.get();
        }
            }

    @Override
    public String deleteNoteById(Long id) throws NoteCannotBeFoundException {
    Optional<Note> foundNote =    noteRepository.findNoteById(id);
        if(foundNote.isPresent()){
            noteRepository.deleteById(id);
            return "Note successfully deleted";
        }
        else {
            throw new NoteCannotBeFoundException(NoteCannotBeFoundException.NoteCannotBeFoundException(id));
        }
    }



    @Override
    public List<Note> findAllNote(int page, int limit) {
        List<Note> notes = new ArrayList<>();
        if(page  > 0)   page -=1;
        Pageable pageable = PageRequest.of(page,limit);
        Page<Note> notes1 = noteRepository.findAll(pageable);
        List<Note> notes2 = notes1.getContent();
        for(Note note : notes2){
            Note note1 = new Note();
            BeanUtils.copyProperties(note, note1);
            notes.add(note1);
        }
        return notes;
    }

    @Override
    public Note updateNote(UpdateNoteRequest updateNoteRequest, Long id) throws NoteCannotBeFoundException {
        Optional<Note> foundNote = noteRepository.findNoteById(id);

        if (foundNote.isPresent()) {
            if (updateNoteRequest.getBody() != null) {
                foundNote.get().setBody(updateNoteRequest.getBody());
            }
            if (updateNoteRequest.getTitle() != null) {
                foundNote.get().setTitle(updateNoteRequest.getTitle());
            }
            if (updateNoteRequest.getContent() != null) {
                foundNote.get().setContent(updateNoteRequest.getContent());
            }
          return   noteRepository.save(foundNote.get());


        } else {
            throw new NoteCannotBeFoundException(NoteCannotBeFoundException.NoteCannotBeFoundException(id));
        }
    }
}
