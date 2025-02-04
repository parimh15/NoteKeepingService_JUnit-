package com.cars24.notekeeping.service;

import com.cars24.notekeeping.data.dao.NoteDAO;
import com.cars24.notekeeping.data.dto.NoteDTO;
import com.cars24.notekeeping.data.entity.Note;
import com.cars24.notekeeping.exception.NoteNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {
    private final NoteDAO noteDAO;

    @Override
    public List<NoteDTO> getAllNotes() {
        return noteDAO.findAll().stream()
                .map(note -> new NoteDTO(note.getId(), note.getTitle(), note.getContent()))
                .collect(Collectors.toList());
    }

    @Override
    public NoteDTO getNoteById(Long id) {
        Note note = noteDAO.findById(id).orElseThrow(() -> new NoteNotFoundException("Note not found"));
        return new NoteDTO(note.getId(), note.getTitle(), note.getContent());
    }

    @Override
    public NoteDTO createNote(NoteDTO noteDTO) {
        Note note = new Note();
        note.setTitle(noteDTO.getTitle());
        note.setContent(noteDTO.getContent());
        Note savedNote = noteDAO.save(note);
        return new NoteDTO(savedNote.getId(), savedNote.getTitle(), savedNote.getContent());
    }

    @Override
    public void deleteNoteById(Long id) {
        noteDAO.findById(id).orElseThrow(() -> new NoteNotFoundException("Note not found"));
        noteDAO.deleteById(id);
    }
}
