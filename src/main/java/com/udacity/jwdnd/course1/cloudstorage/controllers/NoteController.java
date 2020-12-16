package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class NoteController {

    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    public String getNotes(NoteForm noteForm, Model model) {
        model.addAttribute("notes", this.noteService.getAllNotes());
        return "home";
    }

    @PostMapping
    public String postNote(Authentication authentication, @ModelAttribute NoteForm noteForm, Model model) {
        int userid = noteService.getUserId(authentication.getName());
        Note note = new Note();
        note.setNotetitle(noteForm.getNotetitle());
        note.setNotedescription(noteForm.getNotedescription());
        noteService.addNote(note, userid);

        model.addAttribute("notes", this.noteService.getAllNotes(authentication.getName()));

        return "home";
    }
}
