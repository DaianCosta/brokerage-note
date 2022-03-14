package com.daiancosta.brokeragenote.domain.entities.exceptions;

public class FileNoteBusinessException extends RuntimeException{
    public FileNoteBusinessException(final String message){
        super(message);
    }
}
