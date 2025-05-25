package org.example.kqz.helpers;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Component
public class FileStorageHelper {

    private static final String UPLOAD_DIR = "uploads/";

    public String savePhoto(String photo) {
        try {
            String fileName = UUID.randomUUID() + "_" + photo.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR);

            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            Files.copy(photo.getInputStream(), path.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store photo", e);
        }
    }
}
