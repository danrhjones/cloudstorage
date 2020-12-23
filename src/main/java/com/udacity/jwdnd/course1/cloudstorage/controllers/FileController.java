package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import java.io.IOException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {

    private final UserService userService;
    private final FileService fileService;


    public FileController(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    @PostMapping("/file")
    public String createOrUpdateFile(Authentication authentication, MultipartFile fileUpload) throws IOException {
        int userid = userService.getUserid(authentication.getName());

        if (fileUpload.isEmpty() || fileService.getFile(fileUpload.getOriginalFilename()) != null) {
            return "redirect:/result?error";
        }
        fileService.addFile(fileUpload, userid);
        return "redirect:/result?success";
    }

    @GetMapping("/file/delete/{fileid}")
    public String deleteCredential(@PathVariable("fileid") int fileid) {
        if (fileService.getFile(fileid) == null) {
            return "redirect:/result?error";
        }
        fileService.deleteFile(fileid);
        return "redirect:/result?success";
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity download(@PathVariable("filename") String filename){
        File file = fileService.getFile(filename);

        return ResponseEntity.ok()
            .header(
                HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
            .body(file);

    }
}
