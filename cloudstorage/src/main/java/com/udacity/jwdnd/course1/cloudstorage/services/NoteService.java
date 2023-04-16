package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NoteService {
    private final NoteMapper mapper;


    public NoteService(NoteMapper mapper){
        this.mapper=mapper;
    }
    public ArrayList<Note>getAll(int userId){
        return mapper.getAllNotes(userId);
    }

    public Note selectNote(int noteId){
        return mapper.getNote(noteId);
    }
    public int updateNote(int id,String title, String desc){
        return mapper.update(id,title,desc);
    }
    public int insertNote(Note note){
        return mapper.insert(note);
    }

    public int deleteNote(int noteId){
        return mapper.delete(noteId);

    }

}
