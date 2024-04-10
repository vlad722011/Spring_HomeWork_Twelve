package com.example.Homework_Eight.repository;

import com.example.Homework_Eight.model.PersonalNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<PersonalNote, Long> {
}