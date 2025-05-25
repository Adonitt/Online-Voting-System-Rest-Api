package org.example.kqz.helpers;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Component
public class FileStorageHelper implements FileHelper {

    private static final String BASE_FOLDER = "uploads";

    @Override
    public String uploadFile(String folder, String originalFilename, byte[] fileContent) {
        try {
            Path uploadDir = Paths.get(BASE_FOLDER, folder);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            String cleanFileName = UUID.randomUUID() + "_" + folder.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
            Path targetPath = uploadDir.resolve(cleanFileName);
            Files.write(targetPath, fileContent);

            return Paths.get(folder, cleanFileName).toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }
}
