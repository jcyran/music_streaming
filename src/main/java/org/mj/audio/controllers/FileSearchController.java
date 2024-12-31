package org.mj.audio.controllers;

import org.mj.audio.service.DirectoryTraverse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/search")
public class FileSearchController {
    @Autowired
    private DirectoryTraverse directoryTraverse;

    @PostMapping
    public AudioPath[] search(@RequestParam(value = "searchValue") String query) {
        String[][] file_path = directoryTraverse.find(query);
        ArrayList<AudioPath> audioPaths = new ArrayList<>();

        for (String[] path : file_path) {
            String fileName = path[0];
            String[] filePath = path[1].split("/");

            if (filePath.length == 4)
                audioPaths.add(new AudioPath(
                        filePath[1],
                        filePath[2],
                        filePath[3],
                        fileName
                ));
        }

        return audioPaths.toArray(new AudioPath[0]);
    }

    private record AudioPath(String genre,
                             String artist,
                             String album,
                             String song_title) {}
}
