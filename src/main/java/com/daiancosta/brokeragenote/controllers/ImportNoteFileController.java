package com.daiancosta.brokeragenote.controllers;

import com.daiancosta.brokeragenote.domain.entities.FileInfo;
import com.daiancosta.brokeragenote.domain.entities.enums.TypeFileEnum;
import com.daiancosta.brokeragenote.domain.entities.messages.ResponseMessage;
import com.daiancosta.brokeragenote.domain.entities.messages.producers.NoteProducerMessage;
import com.daiancosta.brokeragenote.messages.producers.NoteProduce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/import-file")
public class ImportNoteFileController {

    private final NoteProduce noteProduce;

    @Autowired
    public ImportNoteFileController(NoteProduce noteProduce) {
        this.noteProduce = noteProduce;
    }

    @PostMapping("/note-upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file,
                                                      @RequestParam(value = "password",
                                                              required = false) String password) throws IOException {

        final FileInfo fileInfo = new FileInfo(file.getOriginalFilename(),
                TypeFileEnum.NOTE,
                file.getOriginalFilename(),
                password);

        final NoteProducerMessage messageProducer = new NoteProducerMessage(
                1L,
                file.getOriginalFilename(),
                file.getBytes(),
                TypeFileEnum.NOTE,
                password
        );

        noteProduce.sendMessage(messageProducer);

        final String messageResponse = "Uploaded the file successfully: " + file.getOriginalFilename();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(messageResponse, fileInfo));

    }
}
