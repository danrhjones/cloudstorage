package com.udacity.jwdnd.course1.cloudstorage.controllers;


import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final NoteService noteService;
    private final CredentialService credentialService;
    private final FileService fileService;

    public HomeController(NoteService noteService, CredentialService credentialService,
        FileService fileService) {
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.fileService = fileService;
    }

    @GetMapping
    public String getHomePage(Authentication authentication, Model model) {
        String username = authentication.getName();

        model.addAttribute("notes", this.noteService.getAllNotes(username));
        model.addAttribute("credentials", this.credentialService.getAllCredentials(username));
        model.addAttribute("files", this.fileService.getAllfiles(username));
        return "home";
    }

}
