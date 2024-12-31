package org.mj.audio.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.mj.audio.property.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/filesystem")
public class FileSystemController {
    @Autowired
    private StorageProperties storageProperties;

    @GetMapping
    public DirectoryNode fileSystem(@RequestParam(value = "genre", required = false) String genre,
                                    @RequestParam(value = "artist", required = false) String artist,
                                    @RequestParam(value = "album", required = false) String album,
                                    @RequestParam(value = "song_title", required = false) String songTitle) {
        Path filePath = Paths.get(storageProperties.getRootPath());

        if (genre != null)
            filePath = filePath.resolve(genre);
        if (artist != null)
            filePath = filePath.resolve(artist);
        if (album != null)
            filePath = filePath.resolve(album);
        if (songTitle != null)
            filePath = filePath.resolve(songTitle);

        File root = new File(String.valueOf(filePath));

        return buildTree(root);
    }

    @GetMapping("/audio_file")
    public void streamAudioFile(@RequestParam(value = "genre") String genre,
                                @RequestParam(value = "artist") String artist,
                                @RequestParam(value = "album") String album,
                                @RequestParam(value = "song_title") String songTitle,
                                HttpServletResponse response) {
        Path filePath = Paths.get(storageProperties.getRootPath(), genre, artist, album, songTitle);

        if (!Files.exists(filePath) || !Files.isReadable(filePath)) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        try (InputStream inputStream = new FileInputStream(String.valueOf(filePath));
             OutputStream outputStream = response.getOutputStream()) {
            String encodedFileName = URLEncoder.encode(songTitle, StandardCharsets.UTF_8.toString());
            response.setHeader("Content-Disposition", "inline; filename=\"" + encodedFileName + "\"");
            response.setHeader("Accept-Ranges", "bytes");

            response.setContentType("audio/mpeg");
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException("Failed to stream audio file from path: " + filePath, e);
        }
    }

    private DirectoryNode buildTree(File root) {
        DirectoryNode directoryNode = new DirectoryNode(root.getName());

        if (root.isDirectory()) {
            File[] files = root.listFiles();

            if (files != null) {
                for (File child : files) {
                    String input = child.getName();

                    if (input.lastIndexOf(".") != -1)
                        input = input.substring(0, input.lastIndexOf('.'));

                    directoryNode.addChild(input, child.isDirectory());
                }
            }
        }

        return directoryNode;
    }

    static @Data class DirectoryNode {
        private String name;
        private List<ChildNode> children;

        record ChildNode(String name, boolean directory) {}

        public DirectoryNode(String name) {
            this.name = name;
        }

        public void addChild(String name, boolean directory) {
            if (children == null) {
                children = new ArrayList<>();
            }

            children.add(new ChildNode(name, directory));
        }
    }
}
