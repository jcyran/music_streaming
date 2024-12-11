package org.mj.audio.service;

import org.mj.audio.filesystem.FileDestination;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileFinderService implements FinderService {
    @Override
    public Path getFinderPath(FileDestination fileDestination) {
        return switch (fileDestination.getDepth()) {
            case home -> null;
            case genre -> Paths.get(fileDestination.getGenre());
            case artist -> Paths.get(fileDestination.getGenre(), fileDestination.getArtist());
            case album -> Paths.get(fileDestination.getGenre(), fileDestination.getArtist(), fileDestination.getAlbum());
        };
    }
}
