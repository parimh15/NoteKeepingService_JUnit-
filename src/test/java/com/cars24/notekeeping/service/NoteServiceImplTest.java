package com.cars24.notekeeping.service;

import com.cars24.notekeeping.data.dao.NoteDAO;
import com.cars24.notekeeping.data.dto.NoteDTO;
import com.cars24.notekeeping.data.entity.Note;
import com.cars24.notekeeping.exception.NoteNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")  // Specify the test properties
@Transactional
public class NoteServiceImplTest {

    @Autowired
    private NoteService noteService;

    @Autowired
    private NoteDAO noteDAO;

    private NoteDTO testNoteDTO;

    @BeforeEach
    public void setUp() {
        // Set up a test note
        testNoteDTO = new NoteDTO(null, "Test Title", "Test Content");
    }

    @Test
    public void testGetAllNotes() {
        // Given
        NoteDTO createdNote = noteService.createNote(testNoteDTO);

        // When
        List<NoteDTO> notes = noteService.getAllNotes();

        // Then
        assertNotNull(notes);
        assertTrue(notes.size() > 0);
        assertEquals(createdNote.getTitle(), notes.get(0).getTitle());
    }

    @Test
    public void testGetNoteById() {
        // Given
        NoteDTO createdNote = noteService.createNote(testNoteDTO);

        // When
        NoteDTO retrievedNote = noteService.getNoteById(createdNote.getId());

        // Then
        assertNotNull(retrievedNote);
        assertEquals(createdNote.getId(), retrievedNote.getId());
        assertEquals(createdNote.getTitle(), retrievedNote.getTitle());
    }

    @Test
    public void testGetNoteByIdNotFound() {
        // When/Then
        assertThrows(NoteNotFoundException.class, () -> noteService.getNoteById(999L));
    }

    @Test
    public void testCreateNote() {
        // When
        NoteDTO createdNote = noteService.createNote(testNoteDTO);

        // Then
        assertNotNull(createdNote);
        assertNotNull(createdNote.getId());
        assertEquals(testNoteDTO.getTitle(), createdNote.getTitle());
        assertEquals(testNoteDTO.getContent(), createdNote.getContent());
    }

    @Test
    public void testDeleteNoteById() {
        // Given
        NoteDTO createdNote = noteService.createNote(testNoteDTO);

        // When
        noteService.deleteNoteById(createdNote.getId());

        // Then
        assertThrows(NoteNotFoundException.class, () -> noteService.getNoteById(createdNote.getId()));
    }

    @Test
    public void testDeleteNoteByIdNotFound() {
        // When/Then
        assertThrows(NoteNotFoundException.class, () -> noteService.deleteNoteById(999L));
    }
}
