package com.daiancosta.brokeragenote.services.note;

import com.daiancosta.brokeragenote.domain.entities.Note;
import com.daiancosta.brokeragenote.domain.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;

    @Autowired
    NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Note save(final Note note) {
        deleteByNumber(note.getNumber());
        return noteRepository.save(note);
    }

    @Override
    public List<Note> saveAll(final List<Note> notes) {
        deleteByList(notes);
        return noteRepository.saveAll(notes);
    }

    private void deleteByList(final List<Note> notes) {
        final List<Long> ids = noteRepository.findByNumbers(convertNumbers(notes));
        if (!ids.isEmpty()) {
            noteRepository.deleteAllById(ids);
        }
    }

    private List<String> convertNumbers(final List<Note> notes) {
        return notes.stream().map(Note::getNumber).collect(Collectors.toList());
    }

    private void deleteByNumber(final String number) {
        final Long id = noteRepository.findByNumber(number);
        if (id != null) {
            noteRepository.deleteById(id);
        }
    }
}
