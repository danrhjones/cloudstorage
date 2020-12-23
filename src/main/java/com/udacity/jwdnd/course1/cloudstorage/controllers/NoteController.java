package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NoteController {

    private final NoteService noteService;
    private final UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping("/note")
    public String createOrUpdateNote(Authentication authentication, Note note) {
        int userid = userService.getUserid(authentication.getName());

        if (noteService.getNote(note.getNoteid()) == null) {
            noteService.addNote(note, userid);
        } else {
            noteService.updateNote(note);
        }
        return "redirect:/result?success";
    }

    @GetMapping("/note/delete/{noteid}")
    public String deleteNote(@PathVariable("noteid") int noteid, Authentication authentication) {
        int userid = userService.getUserid(authentication.getName());

        if (noteService.getNote(noteid) == null ||
            noteService.getNote(noteid).getUserid() != userid) {
            return "redirect:/result?error";
        }
        noteService.deleteNote(noteid);
        return "redirect:/result?success";
    }
}
