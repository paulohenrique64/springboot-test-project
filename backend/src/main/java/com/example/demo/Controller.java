package com.example.demo;

import java.io.IOException;
import java.nio.file.Paths;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class Controller {
    @CrossOrigin
    @GetMapping("user")
    public User getUser() {
        return new User("Paulo Henrique", "paulo@gmail.com", "31983078225");
    }

    @CrossOrigin
    @PostMapping("upload")
    public ResponseEntity<String> upload(@RequestParam("files") MultipartFile[] files) {
        String userDirectory = Paths.get("").toAbsolutePath().toString();

        if (files.length == 0)
            return ResponseEntity.status(400).body("File vector is empty");

        try {
            System.out.println(files.length + " file uploaded");
            for (MultipartFile file : files) {
                System.out.println("\"" + file.getOriginalFilename() + "\" uploaded");
                file.transferTo(new java.io.File(userDirectory + "/../arquivos-recebidos-do-frontend/" + file.getOriginalFilename()));
            }
                
            return ResponseEntity.status(HttpStatus.OK).body("Files uploaded successfully");
        } catch (IOException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }
}
