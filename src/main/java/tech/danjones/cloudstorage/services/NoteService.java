package tech.danjones.cloudstorage.services;

import tech.danjones.cloudstorage.mapper.NoteMapper;
import tech.danjones.cloudstorage.mapper.UserMapper;
import tech.danjones.cloudstorage.models.Note;
import tech.danjones.cloudstorage.models.User;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

    private final UserMapper userMapper;
    private final NoteMapper noteMapper;

    public NoteService(UserMapper userMapper, NoteMapper noteMapper) {
        this.userMapper = userMapper;
        this.noteMapper = noteMapper;
    }

    public void addNote(Note note, int userid) {
        noteMapper.insertNote(note, userid);
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
