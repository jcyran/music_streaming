package org.mj.audio.service;

import jakarta.annotation.Resource;
import org.mj.audio.exception.StorageException;
import org.mj.audio.filesystem.FilesystemDepth;
import org.mj.audio.property.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {
    private final Path rootPath;

    @Autowired
    public FileSystemStorageService(StorageProperties storageProperties) {
        if (storageProperties.getRootPath().trim().isEmpty()) {
            throw new StorageException("Root path is empty");
        }

        this.rootPath = Paths.get(storageProperties.getRootPath());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootPath);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @Override
    public void store(Path songPath, MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("File is empty");
            }

            Files.createDirectories(songPath.getParent());

            Path destinationFile = songPath.normalize().toAbsolutePath();

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file", e);
        }
    }

    @Override
    public Stream<Path> loadDepth(Path songPath) {
        try {
            return Files.walk(songPath, 1)
                    .map(this.rootPath::relativize);
        } catch (IOException e) {
            throw new StorageException("Failed to load file", e);
        }
    }


    @Override
    public Path load(String filename) {
        return rootPath.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        return null;
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootPath.toFile());
    }
}
