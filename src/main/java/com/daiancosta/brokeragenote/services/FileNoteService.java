package com.daiancosta.brokeragenote.services;

import com.daiancosta.brokeragenote.domain.entities.Note;

import java.io.IOException;
import java.text.ParseException;

public interface FileNoteService {
    Note mapData(final String filePath, final String password) throws IOException, ParseException;
}
