package com.cars24.notekeeping.data.dao;

import com.cars24.notekeeping.data.entity.Note;
import com.cars24.notekeeping.data.repositories.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NoteDAOImplTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteDAOImpl noteDAO;

    private Note testNote;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        testNote = new Note();
        testNote.setTitle("Test Title");
        testNote.setContent("Test Content");
    }

    @Test
    public void testFindAll() {
        // Given
        when(noteRepository.findAll()).thenReturn(List.of(testNote));

        // When
        List<Note> notes = noteDAO.findAll();

        // Then
        assertNotNull(notes);
        assertEquals(1, notes.size());
        assertEquals(testNote.getTitle(), notes.get(0).getTitle());
    }

    @Test
    public void testFindById() {
        // Given
        when(noteRepository.findById(1L)).thenReturn(Optional.of(testNote));

        // When
        Optional<Note> noteOptional = noteDAO.findById(1L);

        // Then
        assertTrue(noteOptional.isPresent());
        assertEquals(testNote.getTitle(), noteOptional.get().getTitle());
        assertEquals(testNote.getContent(), noteOptional.get().getContent());
    }

    @Test
    public void testFindByIdNotFound() {
        // Given
        when(noteRepository.findById(1L)).thenReturn(Optional.empty());

        // When
        Optional<Note> noteOptional = noteDAO.findById(1L);

        // Then
        assertFalse(noteOptional.isPresent());
    }

    @Test
    public void testSave() {
        // Given
        when(noteRepository.save(testNote)).thenReturn(testNote);

        // When
        Note savedNote = noteDAO.save(testNote);

        // Then
        assertNotNull(savedNote);
        assertEquals(testNote.getTitle(), savedNote.getTitle());
        assertEquals(testNote.getContent(), savedNote.getContent());
    }

    @Test
    public void testDeleteById() {
        // Given
        doNothing().when(noteRepository).deleteById(1L);

        // When
        noteDAO.deleteById(1L);

        // Then
        verify(noteRepository, times(1)).deleteById(1L);
    }
}
