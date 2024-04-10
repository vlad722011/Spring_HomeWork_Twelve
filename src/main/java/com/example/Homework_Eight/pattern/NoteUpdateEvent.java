package com.example.Homework_Eight.pattern;

import com.example.Homework_Eight.model.PersonalNote;
import com.example.Homework_Eight.service.NoteService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NoteUpdateEvent implements Publisher {

    private PersonalNote note;

    public NoteUpdateEvent(PersonalNote note) {
        this.note = note;
    }

    private NoteService noteService;
    private List<NotesReader> notesReaders = new ArrayList<>();
    private List<PersonalNote> notes = new ArrayList<>();

    @Override
    public void addReader(NotesReader reader) {
        if(notesReaders.size() != 1){
            notesReaders.add(reader);
        }
    }

    @Override
    public void removeReader(NotesReader reader) {
        notesReaders.remove(reader);
    }

    @Override
    public void notifyReaders(String text) {
        notesReaders.forEach(reader -> reader.showNotification(text));
    }

    public void addNote(PersonalNote note) {
        notes.add(note);
        notifyReaders("В списке заметок появилась новая заметка!");
    }

    public void updateNoteById(Long id, PersonalNote newNote) {
        notes.add(newNote);
        notifyReaders("Заметка обновлена");
    }
    public void deleteNoteById(Long id) {
        notifyReaders("Заметка удалена");
    }

}
