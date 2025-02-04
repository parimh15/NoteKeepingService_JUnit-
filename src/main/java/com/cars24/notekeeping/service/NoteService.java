package com.cars24.notekeeping.service;

import com.cars24.notekeeping.data.dto.NoteDTO;

import java.util.List;

public interface NoteService {
    List<NoteDTO> getAllNotes();
    NoteDTO getNoteById(Long id);
    NoteDTO createNote(NoteDTO noteDTO);
    void deleteNoteById(Long id);
}
