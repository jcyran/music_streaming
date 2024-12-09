package org.mj.audio.controllers;

import org.mj.audio.filesystem.FileDestination;
import org.mj.audio.filesystem.FilesystemDepth;
import org.mj.audio.property.StorageProperties;
import org.mj.audio.service.FileFinderService;
import org.mj.audio.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.nio.file.Path;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private FileFinderService fileFinderService;

    @GetMapping("/")
    public String home(Model model) {
        FileDestination fileDestination = new FileDestination();
        fileDestination.setDepth(FilesystemDepth.genre);
        Path destination = fileFinderService.getFinderPath(fileDestination);

        model.addAttribute("files", storageService.loadDepth(destination).map(
                        path -> MvcUriComponentsBuilder.fromMethodName(StorageService.class,
                                "serveFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList()));

//        return "admin/home";
    }

    @GetMapping("/addFiles")
    public String addFiles() {
        return "admin/addFiles";
    }

    @GetMapping("/navBar")
    public String navBar() {
        return "admin/navBar";
    }

    @Autowired
    private StorageProperties storageProperties;

    @Autowired
    private StorageService storageService;

    @PostMapping("/addFiles")
    public String addFile(@RequestParam("genre") String genre,
                          @RequestParam("artist") String artist,
                          @RequestParam("album") String album,
                          @RequestParam("song_name") String songName,
                          @RequestParam("file_input") MultipartFile file) {
        String fileName = songName + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        storageService.store(storageProperties.songPath(genre, artist, album, fileName), file);

        return "redirect:/admin/";
    }
}
