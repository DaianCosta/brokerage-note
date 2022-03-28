package com.daiancosta.brokeragenote.domain.entities.exceptions;

public class FileNegotiationException extends RuntimeException {
    public FileNegotiationException(final String message) {
        super(message);
    }
}
