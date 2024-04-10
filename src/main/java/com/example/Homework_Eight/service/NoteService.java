package com.example.Homework_Eight.service;


import com.example.Homework_Eight.aspect.TrackUserAction;
import com.example.Homework_Eight.model.PersonalNote;
import com.example.Homework_Eight.pattern.NoteUpdateEvent;
import com.example.Homework_Eight.pattern.NotesReader;
import com.example.Homework_Eight.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;
import java.util.List;
import java.util.Optional;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;


@RequiredArgsConstructor
@Service

public class NoteService {
    @Autowired
    private NoteUpdateEvent event;


    @Scope(SCOPE_SINGLETON)
    private NotesReader notesReader(NoteUpdateEvent event) {
       return new NotesReader();
    }

    private Logger logger = Logger.getLogger(NoteService.class.getName());

    private final NoteRepository noteRepository;




    @TrackUserAction
    public PersonalNote addNote(PersonalNote note) {
        event.addReader(notesReader(event));
        event.addNote(note);
        return noteRepository.save(note);
    }

    @TrackUserAction
    public List<PersonalNote> getAllNotes() {
        return noteRepository.findAll();
    }

    @TrackUserAction
    public Optional<PersonalNote> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    @TrackUserAction
    public void deleteNote(Long id) {
        event.addReader(notesReader(event));
        event.deleteNoteById(id);
        if (noteRepository.existsById(id)) {
            noteRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @TrackUserAction
    public PersonalNote updateNote(Long id, PersonalNote note) {
        event.addReader(notesReader(event));
        event.updateNoteById(id, note);
        PersonalNote updateNote = getNoteById(id).orElse(null);

        if (updateNote != null) {
            if (note.getTitle() != null) {
                updateNote.setTitle(note.getTitle());
            }
            if (note.getDescription() != null) {
                updateNote.setDescription(note.getDescription());
            }
            return noteRepository.save(updateNote);
        } else {
            throw new IllegalArgumentException();
        }
    }


}
