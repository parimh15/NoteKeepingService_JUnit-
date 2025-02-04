package com.cars24.notekeeping.data.dao;

import com.cars24.notekeeping.data.entity.Note;
import com.cars24.notekeeping.data.repositories.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class NoteDAOImpl implements NoteDAO {
    private final NoteRepository noteRepository;

    @Override
    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    @Override
    public Optional<Note> findById(Long id) {
        return noteRepository.findById(id);
    }

    @Override
    public Note save(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }
}
