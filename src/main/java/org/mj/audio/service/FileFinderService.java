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
            case genre -> null;
            case artist -> Paths.get(fileDestination.getGenre());
            case album -> Paths.get(fileDestination.getGenre(), fileDestination.getArtist());
            case title -> Paths.get(fileDestination.getGenre(), fileDestination.getArtist(), fileDestination.getAlbum());
        };
    }
}
