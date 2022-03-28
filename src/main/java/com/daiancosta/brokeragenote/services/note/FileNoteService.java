package com.daiancosta.brokeragenote.services.note;

import com.daiancosta.brokeragenote.domain.entities.Note;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

public interface FileNoteService {
    Note mapData(final InputStream file, final String password);
}
