package com.daiancosta.brokeragenote.services;

import java.io.IOException;
import java.text.ParseException;

public interface FileNoteService {
    void save(final String filePath, final String password) throws IOException, ParseException;
}
