package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

    private final UserMapper userMapper;
    private final NoteMapper noteMapper;

    public NoteService(UserMapper userMapper, NoteMapper noteMapper) {
        this.userMapper = userMapper;
        this.noteMapper = noteMapper;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating NoteService bean");
    }

    public void addNote(Note note, int userid) {
        noteMapper.insertNote(note, userid);
    }

    public int getUserId(String username) {
        User user = userMapper.getUser(username);
        return user.getUserId();
    }

    public List<Note> getAllNotes() {
        return noteMapper.findAll();
    }

    public List<Note> getAllNotes(String username) {
        User user = userMapper.getUser(username);
        return noteMapper.findByUserId(user.getUserId());
    }

    public void deleteNote(int noteid) {
        noteMapper.deleteNote(noteid);
    }

    public Note getNote(int noteid) {
        return noteMapper.findOne(noteid);
    }

    public void updateNote(Note note) {
        noteMapper.updateNote(note);
    }
}
