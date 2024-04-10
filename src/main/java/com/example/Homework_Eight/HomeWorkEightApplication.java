package com.example.Homework_Eight;

import com.example.Homework_Eight.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
public class HomeWorkEightApplication {

    @Autowired
    private NoteService service;


    public static void main(String[] args) {
        SpringApplication.run(HomeWorkEightApplication.class, args);
    }

}
