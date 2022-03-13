package com.daiancosta.brokeragenote.models.exceptions;

public class FileStorageException extends RuntimeException{
    public FileStorageException(final String message){
        super(message);
    }
}
