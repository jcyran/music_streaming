package org.mj.audio;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
//        String pass = new BCryptPasswordEncoder().encode("hash");
//
//        System.out.println(pass);

        String path = "filesystem_root";
        Path rootPath = Paths.get(path);
        MultipartFile file;

//        Path destinationFile = rootPath.resolve(
//                        Paths.get(file.getOriginalFilename()))
//                .normalize().toAbsolutePath();
    }
}
