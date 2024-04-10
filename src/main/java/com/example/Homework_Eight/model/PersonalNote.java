package com.example.Homework_Eight.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(name = "created")
    private LocalDateTime createdDate = LocalDateTime.now();


    public PersonalNote(String title, String description) {
        this.title = title;
        this.description = description;
    }


}
