package com.daiancosta.brokeragenote.services.note;

import com.daiancosta.brokeragenote.domain.entities.Note;
import com.daiancosta.brokeragenote.domain.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;

    @Autowired
    NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Transactional
    @Override
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
