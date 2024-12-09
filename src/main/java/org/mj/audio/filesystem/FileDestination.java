package org.mj.audio.filesystem;

import lombok.Data;

public @Data class FileDestination {
    private FilesystemDepth depth;
    private String genre;
    private String artist;
    private String album;
    private String title;
}
