package org.mj.audio.service;

import org.mj.audio.filesystem.FileDestination;

import java.nio.file.Path;

public interface FinderService {
    Path getFinderPath(FileDestination fileDestination);
}
