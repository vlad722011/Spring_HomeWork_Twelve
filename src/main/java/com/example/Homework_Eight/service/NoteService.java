package com.example.Homework_Eight.service;



import com.example.Homework_Eight.aspect.TrackUserAction;
import com.example.Homework_Eight.model.PersonalNote;
import com.example.Homework_Eight.repository.NoteRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import java.util.logging.Logger;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service

public class NoteService {


    private Logger logger = Logger.getLogger(NoteService.class.getName());

    private final NoteRepository noteRepository;


    @TrackUserAction
    public PersonalNote addNote(PersonalNote note) {
        System.out.println("Добавить заметку");
        return noteRepository.save(note);
    }
    @TrackUserAction
    public List<PersonalNote> getAllNotes(){
        System.out.println("Вывести список всех заметок в консоль.");
        return noteRepository.findAll();
    }
    @TrackUserAction
    public Optional<PersonalNote> getNoteById(Long id){
        System.out.println("Найти заметку по Id.");
        return noteRepository.findById(id);
    }

    @TrackUserAction
    public void deleteNote(Long id) {
        System.out.printf("Удалить заметку с Id %d", id);
        if (noteRepository.existsById(id)) {
            noteRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @TrackUserAction
    public PersonalNote updateNote(Long id, PersonalNote note) {
        System.out.println("Обновить заметку.");
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
