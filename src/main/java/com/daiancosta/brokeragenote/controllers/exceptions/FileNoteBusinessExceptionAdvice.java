package com.daiancosta.brokeragenote.controllers.exceptions;

import com.daiancosta.brokeragenote.domain.entities.exceptions.FileNoteBusinessException;
import com.daiancosta.brokeragenote.domain.entities.messages.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FileNoteBusinessExceptionAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(FileNoteBusinessException.class)
    public ResponseEntity<ResponseMessage> handleMaxSizeException(FileNoteBusinessException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseMessage(e.getMessage(), null));
    }
}
