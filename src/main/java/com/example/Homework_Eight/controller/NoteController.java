package com.example.Homework_Eight.controller;

import com.example.Homework_Eight.model.PersonalNote;
import com.example.Homework_Eight.service.FileGateway;
import com.example.Homework_Eight.service.NoteService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;
    private final FileGateway fileGateway;
    private final String request_file_name = "request.txt";

    private static String getRequestInfo(HttpServletRequest request) {
        StringBuilder requestInfo = new StringBuilder();
        requestInfo.append("Time: ").append(LocalDateTime.now()).append("\n");
        requestInfo.append("Method: ").append(request.getMethod()).append("\n");
        requestInfo.append("URI: ").append(request.getRequestURI()).append("\n\n");
        return requestInfo.toString();
    }



    @PostMapping("/add")
    public ResponseEntity<PersonalNote> addNote(@RequestBody PersonalNote note) {
        note.setCreatedDate(LocalDateTime.now());
        fileGateway.writeToFile(note.getTitle() + ".txt", note.toString());
        return ResponseEntity.ok(noteService.addNote(note));
    }


    @GetMapping
    public ResponseEntity<List<PersonalNote>> getAll(HttpServletRequest request) {
        fileGateway.writeToFile(request_file_name, getRequestInfo(request));
        return new ResponseEntity<>(noteService.getAllNotes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Optional<PersonalNote>>> getNoteById(HttpServletRequest request, @PathVariable("id") Long id) {
        fileGateway.writeToFile(request_file_name, getRequestInfo(request));
        Optional<PersonalNote> noteById = noteService.getNoteById(id);

        if (noteById.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Optional.of(Optional.of(new PersonalNote())));
        }
        Optional<PersonalNote> note = noteService.getNoteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(Optional.of(note));
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<PersonalNote> updateNote(HttpServletRequest request, @PathVariable Long id, @RequestBody PersonalNote note) {

        note.setCreatedDate(LocalDateTime.now());
        note.setId(id);
        note.setTitle(note.getTitle());
        note.setDescription(note.getDescription());
        fileGateway.writeToFile(note.getTitle() + ".txt", note.toString());
        fileGateway.writeToFile(request_file_name, getRequestInfo(request));
        try {
            return ResponseEntity.ok(noteService.updateNote(id, note));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoteById(HttpServletRequest request, @PathVariable Long id) {
        fileGateway.writeToFile(request_file_name, getRequestInfo(request));
        try {
            noteService.deleteNote(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
