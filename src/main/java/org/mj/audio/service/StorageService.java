package org.mj.audio.service;

import jakarta.annotation.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {
    void init();
    void store(Path songPath, MultipartFile file);
    Stream<Path> loadDepth(Path songPath);
    Path load(String filename);
    Resource loadAsResource(String filename);
    void deleteAll();
}
