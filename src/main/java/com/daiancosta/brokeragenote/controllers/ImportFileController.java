package com.daiancosta.brokeragenote.controllers;

import com.daiancosta.brokeragenote.messages.ResponseMessage;
import com.daiancosta.brokeragenote.models.FileInfo;
import com.daiancosta.brokeragenote.models.enums.TypeFileEnum;
import com.daiancosta.brokeragenote.services.FileNoteService;
import com.daiancosta.brokeragenote.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/import-file")
public class ImportFileController {

    @Autowired
    StorageService storageService;

    @Autowired
    FileNoteService fileNoteService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file,
                                                      @RequestParam("type") TypeFileEnum typeFileEnum,
                                                      @RequestParam(value = "password", required = false) String password) {
        String message = "";
        try {
            storageService.save(file);
            final Resource fileResource = storageService.load(file.getOriginalFilename());

            fileNoteService.save("uploads/" + file.getOriginalFilename(), password);
            final FileInfo fileInfo = new FileInfo(file.getOriginalFilename(),
                    typeFileEnum,
                    fileResource.getURL().getFile(),
                    password);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message, fileInfo));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message, null));
        }

    }
}
