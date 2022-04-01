package com.daiancosta.brokeragenote.services.note;

import com.daiancosta.brokeragenote.domain.entities.Note;

import java.util.List;

public interface NoteService {

    Note save(final Note note);

    List<Note> saveAll(final List<Note> notes);
}
