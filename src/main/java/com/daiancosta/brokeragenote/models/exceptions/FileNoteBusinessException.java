package com.daiancosta.brokeragenote.models.exceptions;

public class FileNoteBusinessException extends RuntimeException{
    public FileNoteBusinessException(final String message){
        super(message);
    }
}
