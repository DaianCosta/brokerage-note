package com.daiancosta.brokeragenote.services.storage;

import com.daiancosta.brokeragenote.domain.entities.exceptions.FileStorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
class StorageServiceImpl implements StorageService {

    @Value("${upload.path}")
    private String path;

    @Override
    public void init() {
        try {
            if (!Files.isDirectory(Paths.get(path))) {
                Files.createDirectory(Paths.get(path));
            }
        } catch (IOException e) {
            throw new FileStorageException("Could not initialize folder for upload!");
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = Paths.get(path).resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileStorageException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new FileStorageException("Error: " + e.getMessage());
        }
    }

    @Override
    public void save(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), Paths.get(path).resolve(Objects.requireNonNull(file.getOriginalFilename())));
        } catch (Exception e) {
            throw new FileStorageException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        try {
        FileSystemUtils.deleteRecursively(Paths.get(path).toFile());
        } catch (Exception e) {
            throw new FileStorageException("Could not delete the file. Error: " + e.getMessage());
        }
    }
}
