package com.daiancosta.brokeragenote.domain.entities.exceptions;

public class FileMovementException extends RuntimeException {
    public FileMovementException(final String message) {
        super(message);
    }
}
