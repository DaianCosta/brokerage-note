package com.daiancosta.brokeragenote.domain.entities.exceptions;

public class FileStorageException extends RuntimeException{
    public FileStorageException(final String message){
        super(message);
    }
}
