package com.daiancosta.brokeragenote.controllers;

import com.daiancosta.brokeragenote.domain.entities.FileInfo;
import com.daiancosta.brokeragenote.domain.entities.Movement;
import com.daiancosta.brokeragenote.domain.entities.enums.TypeFileEnum;
import com.daiancosta.brokeragenote.domain.entities.exceptions.FileNoteBusinessException;
import com.daiancosta.brokeragenote.domain.entities.messages.ResponseMessage;
import com.daiancosta.brokeragenote.services.FileMovementService;
import com.daiancosta.brokeragenote.services.MovementService;
import com.daiancosta.brokeragenote.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/import-file")
public class ImportMovementFileController {

    @Autowired
    StorageService storageService;

    @Autowired
    FileMovementService fileMovementService;

    @Autowired
    MovementService movementService;

    @PostMapping("/movement-upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            //storageService.save(file);
            //final Resource fileResource = storageService.load(file.getOriginalFilename());

            final List<Movement> movements = fileMovementService.mapData(file.getInputStream());
            final Boolean movementSaved = movementService.save(movements);
            final FileInfo fileInfo = new FileInfo(file.getOriginalFilename(),
                    TypeFileEnum.MOVEMENT,
                  "",
                    null);

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
