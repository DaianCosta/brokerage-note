package com.daiancosta.brokeragenote.controllers;

import com.daiancosta.brokeragenote.domain.entities.FileInfo;
import com.daiancosta.brokeragenote.domain.entities.Note;
import com.daiancosta.brokeragenote.domain.entities.enums.TypeFileEnum;
import com.daiancosta.brokeragenote.domain.entities.exceptions.FileNoteBusinessException;
import com.daiancosta.brokeragenote.domain.entities.messages.ResponseMessage;
import com.daiancosta.brokeragenote.services.note.FileNoteService;
import com.daiancosta.brokeragenote.services.note.NoteService;
import com.daiancosta.brokeragenote.services.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/import-file")
public class ImportNoteFileController {

    @Autowired
    StorageService storageService;

    @Autowired
    FileNoteService fileNoteService;

    @Autowired
    NoteService noteService;

    @PostMapping("/note-upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file,
                                                      @RequestParam(value = "password", required = false) String password) {
        String message = "";
        try {
            storageService.save(file);
            final Resource fileResource = storageService.load(file.getOriginalFilename());

            final Note note = fileNoteService.mapData("uploads/" + file.getOriginalFilename(), password);
            final Note noteSaved = noteService.save(note);
            final FileInfo fileInfo = new FileInfo(file.getOriginalFilename(),
                    TypeFileEnum.NOTE,
                    fileResource.getURL().getFile(),
                    password);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message, fileInfo));
        } catch (FileNoteBusinessException e) {
            message = e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message, null));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message, null));
        }

    }
}
