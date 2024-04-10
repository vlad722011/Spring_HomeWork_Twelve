package com.example.Homework_Eight.pattern;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;


@Scope(SCOPE_SINGLETON)
@Component
@Setter
@Getter
@NoArgsConstructor
public class NotesReader implements Subscriber{

    private NoteUpdateEvent event;

    public NotesReader(NoteUpdateEvent event) {
        this.event = event;
    }

    @Override
    public void showNotification(String text) {
        System.out.println("Произошло событие - > " + text);
    }
}
