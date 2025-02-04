package com.cars24.notekeeping.data.dao;

import com.cars24.notekeeping.data.entity.Note;

import java.util.List;
import java.util.Optional;

public interface NoteDAO {
    List<Note> findAll();
    Optional<Note> findById(Long id);
    Note save(Note note);
    void deleteById(Long id);
}
