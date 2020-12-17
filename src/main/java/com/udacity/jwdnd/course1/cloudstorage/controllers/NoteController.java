package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping()
    public String createOrUpdateNote(Authentication authentication, Note note) {
        int userid = noteService.getUserId(authentication.getName());

        if (noteService.getNote(note.getNoteid()) == null) {
            noteService.addNote(note, userid);
        } else {
            noteService.updateNote(note);
        }
        return "redirect:/result?success";
    }

    @GetMapping("/note/delete/{noteid}")
    public String deleteNote(@PathVariable("noteid") int noteid) {
        if (noteService.getNote(noteid) == null) {
            return "redirect:/result?error";
        }
        noteService.deleteNote(noteid);
        return "redirect:/result?success";
    }
}
