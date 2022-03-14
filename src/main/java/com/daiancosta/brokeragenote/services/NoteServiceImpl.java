package com.daiancosta.brokeragenote.services;

import com.daiancosta.brokeragenote.domain.entities.Note;
import com.daiancosta.brokeragenote.domain.repositories.NoteRepository;
import org.springframework.stereotype.Service;

@Service
class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;

    NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note save(final Note note) {
        deleteByNumber(note.getNumber());
        return noteRepository.save(note);
    }

    private void deleteByNumber(final String number) {
        final Long id = noteRepository.findByNumber(number);
        if (id != null) {
            noteRepository.deleteById(id);
        }
    }
}
