package org.mj.audio.controllers;

import org.mj.audio.property.StorageProperties;
import org.mj.audio.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private StorageProperties storageProperties;

    @Autowired
    private StorageService storageService;

    @GetMapping("/addFiles")
    public String addFiles() {
        return "admin/addFiles";
    }

    @PostMapping("/addFiles")
    public String addFile(@RequestParam("genre") String genre,
                          @RequestParam("artist") String artist,
                          @RequestParam("album") String album,
                          @RequestParam("song_name") String songName,
                          @RequestParam("file_input") MultipartFile file) {
        String fileName = songName + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        storageService.store(storageProperties.songPath(genre, artist, album, fileName), file);

        return "redirect:/admin/home";
    }

    @GetMapping
    public String index() {
        return "redirect:admin/home";
    }

    @GetMapping("/home")
    public String home() {
        return "admin/home";
    }
}
