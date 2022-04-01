package com.daiancosta.brokeragenote.controllers;

import com.daiancosta.brokeragenote.domain.entities.FileInfo;
import com.daiancosta.brokeragenote.domain.entities.Negotiation;
import com.daiancosta.brokeragenote.domain.entities.enums.TypeFileEnum;
import com.daiancosta.brokeragenote.domain.entities.exceptions.FileNoteBusinessException;
import com.daiancosta.brokeragenote.domain.entities.messages.ResponseMessage;
import com.daiancosta.brokeragenote.services.negotiation.FileNegotiationService;
import com.daiancosta.brokeragenote.services.negotiation.NegotiationService;
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
public class ImportNegotiationFileController {

    @Autowired
    FileNegotiationService fileNegotiationService;

    @Autowired
    NegotiationService negotiationService;

    @PostMapping("/negotiation-upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            //storageService.save(file);
            //final Resource fileResource = storageService.load(file.getOriginalFilename());

            final List<Negotiation> negotiations = fileNegotiationService.mapData(file.getInputStream());
            final Boolean negotiationSaved = negotiationService.save(negotiations);
            final FileInfo fileInfo = new FileInfo(file.getOriginalFilename(),
                    TypeFileEnum.NEGOTIATION,
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
