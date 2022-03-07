package com.daiancosta.brokeragenote.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileMovementService {
    void save(MultipartFile file);
}
