package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    //    @RequestMapping(value = "notes/delete{noteid}", method = RequestMethod.GET)
//    public String deleteNote(@PathVariable int noteid) {
//        if (noteid > 0) {
//            noteService.deleteNote(noteid);
//            return "home";
//        }
//        return "home";
//    }
//    @RequestMapping(value = "/notes/delete{noteid}")
//    public String handleDeleteUser(@PathVariable(value="noteid") String noteid) {
//        System.out.println(noteid);
//        System.out.println("test");
//        return "redirect:/external";
//    }

    @GetMapping("/note/{noteid}")
    public String deleteNote(@PathVariable("noteid") String noteid) {

        return "donkey";
    }

//    @DeleteMapping("/note/delete/{noteid}")
//    public String deleteFile(@PathVariable long fileId, Authentication authentication) {
//        return "blah";
//    }
//    public String deleteFile(@PathVariable Integer noteid) {
//        return "blah";
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("result");
//        try {
//            noteService.deleteNote(noteid);
//            modelAndView.addObject("alertClass", "alert-success");
//            modelAndView.addObject("message", "File deleted successfully");
//        } catch (Exception e) {
//            modelAndView.addObject("alertClass", "alert-danger");
//            modelAndView.addObject("message", "Something went wrong");
//        }
//        return modelAndView;
//    }
}
