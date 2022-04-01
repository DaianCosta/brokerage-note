package com.daiancosta.brokeragenote.services.note;

import com.daiancosta.brokeragenote.domain.entities.Note;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

public interface FileNoteService {
    List<Note> mapData(final InputStream file, final String password);
}
