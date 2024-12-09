package org.mj.audio.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.file.Path;
import java.nio.file.Paths;

@ConfigurationProperties("storage")
public @Data class StorageProperties {
    private String rootPath = "filesystem_root";

    public Path songPath(String genre, String artist, String album, String title) {
        return Paths.get(rootPath, genre, artist, album, title);
    }
}
