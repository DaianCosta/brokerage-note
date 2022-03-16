package com.daiancosta.brokeragenote.services;

import com.daiancosta.brokeragenote.domain.entities.Movement;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface FileMovementService {
    boolean hasExcelFormat(MultipartFile file);
    List<Movement> mapData(InputStream is);
}
