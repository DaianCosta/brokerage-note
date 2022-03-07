package com.daiancosta.brokeragenote.services;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

public interface StorageService {
    void init();
    Resource load(String filename);
    void save(MultipartFile file);
    void deleteAll();
}
