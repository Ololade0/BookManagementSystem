package com.bookmanagement.bookmanagementsystem.exception;

public class NoteCannotBeFoundException extends Throwable {
    public NoteCannotBeFoundException(String message){
        super(message);
    }

    public static String NoteCannotBeFoundException(Long noteId){
        return "Note with " + noteId + "cannot be found" ;
    }


    public static String NoteAlreadyExistException(Long noteId){
        return "Note with " + noteId + "already exist" ;
    }


    public static String NoteCannotBeFoundException(String content){
        return "Note with " + content + "cannot be found" ;
    }
}
