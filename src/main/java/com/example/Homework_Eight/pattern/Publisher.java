package com.example.Homework_Eight.pattern;

public interface Publisher {
    public void addReader(NotesReader reader);
    public void removeReader(NotesReader reader);
    public void notifyReaders(String text);
}
